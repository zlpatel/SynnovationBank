package edu.asu.secure.SynnovationBank.DBUtilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import edu.asu.secure.SynnovationBank.DTO.Account;
import edu.asu.secure.SynnovationBank.DTO.Notifications;
import edu.asu.secure.SynnovationBank.DTO.NotificationsType;
import edu.asu.secure.SynnovationBank.DTO.Person;
import edu.asu.secure.SynnovationBank.DTO.ReportedIssues;
import edu.asu.secure.SynnovationBank.DTO.TransactionDetails;
import edu.asu.secure.SynnovationBank.DTO.TransactionType;
import edu.asu.secure.SynnovationBank.DTO.Transactions;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
    //private static ServiceRegistry serviceRegistry;
    
    private static Configuration configuration = null;
    
    public static void setConfiguration() {
    	configuration = new Configuration();
		configuration.addAnnotatedClass(TransactionType.class);
    	configuration.addAnnotatedClass(Person.class);
		configuration.addAnnotatedClass(Account.class);
		configuration.addAnnotatedClass(Transactions.class);
		configuration.addAnnotatedClass(TransactionDetails.class);
		configuration.addAnnotatedClass(NotificationsType.class);
		configuration.addAnnotatedClass(Notifications.class);
		configuration.addAnnotatedClass(ReportedIssues.class);
		configuration.configure();
    }
    
	public static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            /*Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
            
        	if(sessionFactory!=null)
        		return sessionFactory;
        	else {
        		/*Configuration configuration = new Configuration();
        		configuration.addAnnotatedClass(LinkitUsers.class);
        		configuration.configure();*/

        		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	
        		return sessionFactory;
        	}
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	public static void createTables(){
		
		new SchemaExport(configuration).create(true, true);
	}
  
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
  
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
