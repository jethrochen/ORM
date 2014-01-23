/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */

package hibernate;

import org.contact.entity.Contact;
import org.contact.entity.Department;
import org.contact.entity.Incumbency;
import org.contact.entity.Person;
import org.contact.entity.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Administrator
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            Configuration config = getConfiguration();
            //serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).build();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            config.setSessionFactoryObserver(new SessionFactoryObserver(){
                private static final long serialVersionUID = 1L;

                @Override
                public void sessionFactoryCreated(SessionFactory sf) {
                }

                @Override
                public void sessionFactoryClosed(SessionFactory sf) {
                    StandardServiceRegistryBuilder.destroy(serviceRegistry);
                }
            });
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session openSession(){
        return sessionFactory.openSession();
    }
    private static Configuration getConfiguration(){
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Contact.class);
        cfg.addAnnotatedClass(Department.class);
        cfg.addAnnotatedClass(Person.class);
        cfg.addAnnotatedClass(Incumbency.class);
        cfg.addAnnotatedClass(Project.class);
        
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/hrs_db");
        cfg.setProperty("hibernate.connection.username", "root");
        cfg.setProperty("hibernate.connection.password", "youli");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "update");
        cfg.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
        cfg.setProperty("hibernate.current_session_context_class", "thread");
        return cfg;
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
