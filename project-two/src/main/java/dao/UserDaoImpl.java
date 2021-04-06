package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class UserDaoImpl implements UserDao {


    public static void main(String[] args){

        UserDaoImpl n = new UserDaoImpl();
        User dan = new User("Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "WOW.jpeg");
        n.createUser(dan);
    }

    @Override
    public void createUser(User user) {

        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.save(user);

        tx.commit();
    }
}
