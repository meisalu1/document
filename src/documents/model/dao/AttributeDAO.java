package documents.model.dao;

import documents.model.data.Attribute;

public interface AttributeDAO {
	
	public Attribute[] getByDocTypeId(int id);
	
	public int insertDocAttributes(Attribute[] attrs,int docId);
	
	public int update(Attribute[] attrs, int docID);

}
