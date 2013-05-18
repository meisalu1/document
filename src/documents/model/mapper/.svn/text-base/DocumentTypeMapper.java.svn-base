package documents.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import documents.classes.StructureMapper;
import documents.model.data.DocumentType;

public class DocumentTypeMapper implements StructureMapper<DocumentType[]> {

	@Override
	public DocumentType[] mapSet(ResultSet resultSet) throws SQLException {
		
		
		DocumentType[] docTypes = null;		
		DocumentType[] childsArray = null;	
		Queue<DocumentType> childs = new LinkedList<DocumentType>();
		Queue<DocumentType> docTypesList = new LinkedList<DocumentType>();
		DocumentType docType = null;
		DocumentType child;
		int counter = 0;;
		
		while(resultSet.next()){
			if(docType == null || docType.getId() != resultSet.getInt("parent_id")){
				if(!childs.isEmpty()){
					childsArray = new DocumentType[childs.size()];
					while(!childs.isEmpty()){
						childsArray[counter] = childs.poll();
						counter++;
					}
					counter = 0;
					docType.setTypes(childsArray);
					docTypesList.add(docType);
				}
				docType = new DocumentType();
				docType.setId(resultSet.getInt("parent_id"));
				docType.setName(resultSet.getString("parent_name"));
				if(resultSet.getInt("child_id") == 0){
					docTypesList.add(docType);
				}
			}
			if(resultSet.getInt("child_id") != 0){
				child = new DocumentType();
				child.setId(resultSet.getInt("child_id"));
				child.setName(resultSet.getString("child_name"));
				childs.add(child);
			}
			
		}
				
		docTypes = new DocumentType[docTypesList.size()];
		while(!docTypesList.isEmpty()){
			docTypes[counter] = docTypesList.poll();
			counter++;
		}
			
		return docTypes;
	}

}
