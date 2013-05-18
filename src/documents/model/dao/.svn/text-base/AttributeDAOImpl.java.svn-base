package documents.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

import documents.classes.DBConnection;
import documents.classes.GeneralFunction;
import documents.classes.MyLogger;
import documents.model.data.Attribute;
import documents.model.mapper.AttributeMapper;

public class AttributeDAOImpl implements AttributeDAO {
	
	private AttributeMapper mapper = new AttributeMapper();
	private Connection connection = null;
	GeneralFunction function = new GeneralFunction();

	public AttributeDAOImpl(Connection connection){
		try{
			this.connection = connection;
		} catch (Exception e) {
			MyLogger.Log("AttributeDAOImpl", e.toString());
		}
	}
	@Override
	public Attribute[] getByDocTypeId(int id) {
		
		Statement statement = null;
		ResultSet attributesSet = null;
		Attribute[] attributes = null;
		
		String sql = String.format("SELECT DAT.doc_attribute_type, DAT.type_name, " +
				"DTA.required,DAT.default_selection_id_fk AS valiku_id, DAT.data_type_fk, " +
				"ATSV.atr_type_selection_value, ATSV.value_text, DAT.default_value_text, DTA.orderby " +
				"FROM doc_attribute_type DAT " +
				"INNER JOIN doc_type_attribute DTA ON DAT.doc_attribute_type=DTA.doc_attribute_type_fk " +
				"LEFT JOIN atr_type_selection_value ATSV ON DAT.doc_attribute_type = ATSV.doc_attribute_type_fk " +
				"WHERE DTA.doc_type_fk=%d ORDER BY DTA.orderby ASC, ATSV.orderby ASC;", id);	
		
		try {
			statement = connection.createStatement();
			attributesSet = statement.executeQuery(sql);
			attributes = mapper.mapSet(attributesSet);
			
		} catch (SQLException e) {
			MyLogger.Log("AttributeDAOImpl.getAll()", e.getMessage());
		} finally {
			DBConnection.closeResultSet(attributesSet);
			DBConnection.closeStatement(statement);
		}
		
		return attributes;
	}
	
	@Override
	public int insertDocAttributes(Attribute[] attrs, int docId) {
		
		Stack<String> queryStack = new Stack<String>();
		String sql = "";
		String[] queries;
		int i = 0;
		CallableStatement cs = null;
		
		for(Attribute attr : attrs){
			sql = "INSERT INTO doc_attribute(atr_type_selection_value_fk, doc_attribute_type_fk, document_fk, type_name, value_text, " +
					"value_number, value_date, data_type, orderby, required) VALUES ";
			switch (attr.getData_type()) {
			case 1:
				sql += String.format("(%s, %d, %d, '%s', '%s', %s, %s, %d ,%d, '%s')", null, attr.getAttribute_type(), docId, attr.getName(), 
						attr.getDefaultValue(), null, null, 1, attr.getOrderby(), (attr.isReq()) ? "Y" : "N");
				break;
			case 2:
				sql += String.format("(%s, %d, %d, '%s', '%s', %s, %s, %d ,%d, '%s')", null, attr.getAttribute_type(), docId, attr.getName(), 
						null, attr.getDefaultValue(), null, 2, attr.getOrderby(), (attr.isReq()) ? "Y" : "N");
				break;
			case 3:
				sql += String.format("(%s, %d, %d, '%s', '%s', %s, '%s', %d ,%d, '%s')", null, attr.getAttribute_type(), docId, attr.getName(), 
						null, null, (attr.getDefaultValue().equals("") ? attr.getDefaultValue() : function.dateToSQLFormat(attr.getDefaultValue())), 
						3, attr.getOrderby(), (attr.isReq()) ? "Y" : "N");
				break;
			case 4:
				sql += String.format("(%s, %d, %d, '%s', %s, %s, %s, %d ,%d, '%s')", attr.getDefaultValue(), attr.getAttribute_type(), docId, attr.getName(), 
						null, null, null, 4, 0, (attr.isReq()) ? "Y" : "N");
				break;
			}
			sql += ";";
			queryStack.add(sql);

		}	
		
		queries = new String[queryStack.size()];
		
		while(!queryStack.isEmpty()) {
			queries[i] = queryStack.pop();		
			i++;
		}
		
		try {
			cs = connection.prepareCall("{call add_attrs_into_db(?) }");
			cs.setArray(1, connection.createArrayOf("text", queries));
			cs.execute();
		} catch (SQLException e) {
			MyLogger.Log("AttributeDAOImpl.insert()", e.getMessage());
		} finally {
			try { if(!cs.isClosed()) cs.close(); } catch (Exception e) {};
		}
		return 0;
	}
	
	@Override
	public int update(Attribute[] attrs, int docID) {
		
		Stack<String> queryStack = new Stack<String>();
		String[] queries;
		String sql;
		int i = 0;
		CallableStatement cs = null;
		
		for(Attribute attr : attrs){
			
			sql = "UPDATE doc_attribute SET ";
			
			switch (attr.getData_type()) {
				case 1:
					sql += String.format("value_text='%s'", attr.getDefaultValue());
					break;
				case 2:
					sql += String.format("value_number='%s'", attr.getDefaultValue());
					break;
				case 3:
					sql += String.format("value_date='%s'", (attr.getDefaultValue().equals("") ? attr.getDefaultValue() :
						function.dateToSQLFormat(attr.getDefaultValue())));
					break;
				case 4:
					sql += String.format("atr_type_selection_value_fk=%s", attr.getDefaultValue());
					break;
			}
			
			sql += String.format("WHERE doc_attribute_type_fk=%d AND document_fk=%d;", attr.getAttribute_type(), docID);	
			MyLogger.LogMessage(sql);
			queryStack.add(sql);
		}
		
		queries = new String[queryStack.size()];
		
		while(!queryStack.isEmpty()) {
			queries[i] = queryStack.pop();		
			i++;
		}
		
		try {
			cs = connection.prepareCall("{call add_attrs_into_db(?) }");
			cs.setArray(1, connection.createArrayOf("text", queries));
			cs.execute();
		} catch (SQLException e) {
			MyLogger.Log("AttributeDAOImpl.update()", e.getMessage());
		} finally {
			try { if(!cs.isClosed()) cs.close(); } catch (Exception e) {};
		}
			
		return 0;
	}

}
