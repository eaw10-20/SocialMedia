package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {


        private static SessionFactory sf= new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        private static Session session = null;


        public static Session getSession(){

            //open a session if it doesn't exist yet

            if(session == null){
                session = sf.openSession();
            }
            return session;
        }

        public static void closeSession(){
            session.close();
            session = null;
            sf.close();
        }
}
