package documents.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import documents.classes.DBConnection;
import documents.classes.MyLogger;
import documents.model.data.DocumentType;
import documents.model.mapper.DocumentTypeMapper;

public class DocumentTypeDAOImpl implements GeneralDAO<DocumentType>{
	
	private Connection connection = null;
	private Statement statement = null;
	private DocumentTypeMapper mapper = new DocumentTypeMapper();
	
	public DocumentTypeDAOImpl(Connection connection){
		try{
			this.connection = connection;
		} catch (Exception e) {
			MyLogger.Log("DocumentTypeDAOImpl", e.getMessage());
		}
	}

	@Override
	public DocumentType[] getAll() {
		
		ResultSet typesSet = null;
		DocumentType[] types = null;
		
		String sql = "SELECT " +
				"t1.doc_type as parent_id, " +
				"t1.type_name as parent_name, " +
				"t2.doc_type as child_id, " +
				"t2.type_name as child_name " +
				"FROM doc_type as t1 " +
				"LEFT JOIN doc_type as t2 " +
				"ON t1.doc_type = t2.super_type_fk " +
				"WHERE t1.super_type_fk = 0 " +
				"ORDER BY t1.doc_type ASC;";
		
		try {
			
			statement = connection.createStatement();
			typesSet = statement.executeQuery(sql);	
			types = mapper.mapSet(typesSet);
			
		} catch (SQLException e) {
			MyLogger.Log("DocumentTypeDAOImpl.getAll()", e.getMessage());
		} finally {
			DBConnection.closeResultSet(typesSet);
			DBConnection.closeStatement(statement);
		}

		return types;
	}

}
