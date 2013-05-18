package documents.model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import documents.classes.MyLogger;

public class HibernateUtils {
	
    private static final SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
        	Configuration configuration = new Configuration();
        	MyLogger.LogMessage("esimene");
        	configuration.configure();
        	MyLogger.LogMessage("esimene2");
        	serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        	MyLogger.LogMessage("esimene3");
        	sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	MyLogger.LogMessage("esimene4");
        } catch (Throwable ex) {
            MyLogger.Log("HibernateUtils", ex.toString());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
