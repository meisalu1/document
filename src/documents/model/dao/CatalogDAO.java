package documents.model.dao;

import java.util.List;

import documents.model.data.Catalog;

public interface CatalogDAO {
	
	public List<Catalog> getAllCatalogs();
	
	public Catalog getById(int id);

}
