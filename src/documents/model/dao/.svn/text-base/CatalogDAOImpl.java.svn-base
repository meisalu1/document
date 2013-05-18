package documents.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import documents.classes.MyLogger;
import documents.model.data.Catalog;

public class CatalogDAOImpl implements CatalogDAO {


	@Override
	public List<Catalog> getAllCatalogs() {
		
		List<Catalog> cats = null;
		Session session = null;
		try {
			session = HibernateUtils.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			cats = session.createQuery("from Catalog").list();
		} catch (Exception e) {
			MyLogger.Log("CatalogDAOImpl.getAllCatalogs", e.toString());
		} finally {
			session.close();
		}

		return cats;
	}

	@Override
	public Catalog getById(int id) {
		
		Catalog cat = null;
		Session session = null;
		
		try {
			session = HibernateUtils.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery("from Catalog where id =:cat_id");
			q.setInteger("cat_id", id);
			cat = (Catalog)q.uniqueResult();
		} catch (Exception e) {
			MyLogger.Log("CatalogDAOImpl.getById", e.toString());
		} finally {
			session.close();
		}
		
		return cat;
	}
	
	

}
