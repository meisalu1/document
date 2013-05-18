
package documents.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import documents.classes.DBConnection;
import documents.classes.GeneralFunction;
import documents.classes.MyLogger;
import documents.form.SearchForm;
import documents.model.data.Document;
import documents.model.mapper.DocumentListElemMapper;
import documents.model.mapper.DocumentMapper;

public class DocumentDAOImpl implements DocumentDAO {
	
	private DocumentMapper mapper = new DocumentMapper();
	private DocumentListElemMapper listMapper = new DocumentListElemMapper();
	private Connection connection = null;
	private GeneralFunction function = new GeneralFunction();

	public DocumentDAOImpl(Connection connection){
		try{
			this.connection = connection;
		} catch (Exception e) {
			MyLogger.Log("DocumentDAOImpl", e.getMessage());
		}
	}
	
	@Override
	public Document[] getAll(SearchForm form) {
		
		Statement statement = null;
		Document[] docs = null;
		Document doc;
		ResultSet docsSet = null;
		int docsCount = 0;
		
		Queue<Document> docsQueue = new LinkedList<Document>();
		String sql = "SELECT DISTINCT d.document, d.name, d.description FROM document d " +
				"LEFT JOIN person p ON p.person = d.updated_by " +
				"JOIN document_doc_catalog ddc ON ddc.document_fk = d.document " +
				"JOIN doc_catalog dc ON dc.doc_catalog = ddc.doc_catalog_fk " +
				"JOIN document_doc_type ddt ON ddt.document_fk = d.document " +
				"LEFT JOIN doc_attribute da ON da.document_fk = d.document " +
				"LEFT JOIN doc_subject ds ON d.document = ds.document_fk" +
				"LEFT JOIN person p ON p.person = ds.updated_by " +
				"LEFT JOIN enterprise e ON  ";
		
		if(form != null){		
			Stack<String> wheres = new Stack<String>();
			if(!function.emptyString(form.getName())) {
				wheres.add(String.format(" LOWER(d.name) LIKE '%s%%'", form.getName().toLowerCase()));
			}
			if(!function.emptyString(form.getId())) {
				wheres.add(String.format(" d.document = '%s'", form.getId()));
			}
			if(!function.emptyString(form.getDesc())) {
				wheres.add(String.format(" LOWER(d.description) LIKE '%s%%'", form.getDesc().toLowerCase()));
			}
			if(!function.emptyString(form.getLast_modifier())) {
				wheres.add(String.format(" LOWER(p.last_name) LIKE '%s%%'", form.getLast_modifier().toLowerCase()));
			}			
			if(!function.emptyString(form.getCat_name())) {
				wheres.add(String.format(" LOWER(dc.name) LIKE '%s%%'", form.getCat_name().toLowerCase()));
			}
			if(!function.emptyString(form.getSubject())) {
				wheres.add(String.format(" (LOWER(p.last_name) LIKE '%s%%' OR LOWER(e.name) LIKE '%s%%'", 
						form.getSubject().toLowerCase(), form.getSubject().toLowerCase()));
			}
			if(form.getCurrentStatusID() > 0) {
				wheres.add(String.format(" d.doc_status_type_fk = %d", form.getCurrentStatusID()));
			}
			if(form.getAttributes().size() > 0) {
				Map<String, String> attrs = form.getAttributes();
				for(String key : attrs.keySet()) {
					if(!attrs.get(key).equals("")){
						wheres.add(String.format(" (doc_attribute_type_fk = %s AND LOWER(da.value_text) LIKE '%s%%')", 
								key, attrs.get(key).toLowerCase()));
					}
				}
			} else if(!function.emptyString(form.getAttribute())) {
				wheres.add(String.format(" (LOWER(da.value_text) LIKE '%s%%')", 
						form.getAttribute().toLowerCase()));
			}
			if(form.getCurrentTypeID() > 0) {
				wheres.add(String.format(" ddt.doc_type_fk = %d", form.getCurrentTypeID()));
			}
			
			if(!wheres.isEmpty())
				sql += " WHERE";
			
			while(!wheres.isEmpty()){
				sql += wheres.pop();
				if(wheres.size() > 0){
					sql += " AND";
				}
			}
					
		}
		
		sql += ";";
		
		MyLogger.LogMessage(sql);
		
		try {
			
			statement = connection.createStatement();
			docsSet = statement.executeQuery(sql);	

			while(docsSet.next()){
				doc = listMapper.mapRow(docsSet);
				docsQueue.add(doc);
			}
			
		} catch (SQLException e) {
			MyLogger.Log("DocumentDAOImpl.getAll()", e.getMessage());
		} finally {
			DBConnection.closeResultSet(docsSet);
			DBConnection.closeStatement(statement);
		}
		
		docs = new Document[docsQueue.size()];

		while(!docsQueue.isEmpty()){
			docs[docsCount] = docsQueue.poll();
			docsCount++;
		}
			
		return docs;
		
	}
	

	@Override
	public Document[] getByCatalog(int catalog) {
		
		Statement statement = null;
		ResultSet docsSet = null;
		Document[] docs = null;
		Document doc;
		int i = 0;
		Queue<Document> docsQueue = new LinkedList<Document>();
		String sql = String.format("SELECT d.document, d.name, d.description FROM document d " +
				"INNER JOIN document_doc_catalog ddc ON ddc.document_fk = d.document " +
				"WHERE ddc.doc_catalog_fk = %d", catalog);
		
		try {
			statement = connection.createStatement();
			docsSet = statement.executeQuery(sql);	
			
			while(docsSet.next()){
				doc = listMapper.mapRow(docsSet);
				docsQueue.add(doc);
			}
		} catch (SQLException e) {
			MyLogger.Log(String.format("DocumentDAO.getByCatalog(%d)", catalog), e.toString());
		} finally {
			DBConnection.closeResultSet(docsSet);
			DBConnection.closeStatement(statement);
		}	
		
		docs = new Document[docsQueue.size()];

		while(!docsQueue.isEmpty()){
			docs[i] = docsQueue.poll();
			i++;
		}
		
		return docs;
	}

	@Override
	public Document getById(int id) {
		
		Statement statement = null;
		ResultSet docsSet = null;
		String sql = String.format("SELECT DISTINCT d.document, d.name, d.description, d.doc_status_type_fk, " +
				"ddt.doc_type_fk, da.doc_attribute, da.doc_attribute_type_fk, da.value_text, da.value_number, " +
				"da.value_date, da.required, da.atr_type_selection_value_fk, da.data_type " +
				"FROM document d " +
				"INNER JOIN document_doc_type ddt ON d.document = ddt.document_fk " +
				"LEFT JOIN doc_attribute da ON d.document = da.document_fk " +
				"WHERE d.document = %d;", id);
		Document doc = null;
		
		try {

			statement = connection.createStatement();
			docsSet = statement.executeQuery(sql);	
			
			doc = mapper.mapRow(docsSet);
			
		} catch (SQLException e) {
			MyLogger.Log(String.format("DocumentDAO.getById(%d)", id), e.getMessage());
		} finally {
			DBConnection.closeResultSet(docsSet);
			DBConnection.closeStatement(statement);
		}	
		return doc;
	}

	@Override
	public int delete(int id) {
		
		CallableStatement statement = null;
		
		try {
			
			statement = connection.prepareCall("{call remove_document(?) }");
			statement.setInt(1, id);
			statement.execute();
			
		} catch (SQLException e) {
			MyLogger.Log("DocumentDAO.delete", e.toString());
		} finally {
			 try { if(!statement.isClosed()) statement.close(); } catch (Exception e) {};
		}
		return 0;
	}

	@Override
	public int insert(Document document) {
			
		CallableStatement statement = null;
		int id = 0;
		String sql = String.format("INSERT INTO document(name, description, created_by, doc_status_type_fk)" +
				"VALUES('%s', '%s', %d, %d)", document.getName(), document.getDesc(), document.getCreated_by(), document.getStatus());
		
		try {
			statement = connection.prepareCall("{call add_document_into_db(?, ?, ?, ?, ?, ?) }");
			statement.setString(1, sql);
			statement.setInt(2, document.getCat_id());
			statement.setInt(3, document.getType());
			statement.setInt(4, document.getCreated_by());
			statement.setInt(5, document.getStatus());
			statement.registerOutParameter(6, Types.INTEGER);
			statement.execute();
			id = statement.getInt(6);
		} catch (SQLException e) {
			MyLogger.Log("DocumentDAO.insert", e.toString());
		} finally {
			 try { if(!statement.isClosed()) statement.close(); } catch (Exception e) {};
		}
		return id;
	}

	@Override
	public Document update(Document document) {
		CallableStatement cs = null;
		
		try {
			cs = connection.prepareCall("{call update_document(?, ?, ?, ?, ?) }");
			cs.setInt(1, document.getId());
			cs.setString(2, document.getName());
			cs.setString(3, document.getDesc());
			cs.setInt(4, document.getStatus());
			cs.setInt(5, document.getCreated_by());
			cs.execute();
		} catch (SQLException e) {
			MyLogger.Log("DocumentDAO.update", e.toString());
		} finally {
			 try { if(!cs.isClosed()) cs.close(); } catch (Exception e) {};
		}
		
		
		return null;
	}
}
