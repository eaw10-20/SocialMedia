package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class UserDaoImpl implements UserDao {


    /**
     * Inserts one user into the "user" table
     * @param user
     */
    @Override
    public void createUser(User user) {

        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.save(user);

        tx.commit();

    }

    /**
     *
     * @param user
     * @return
     */

    @Override
    public boolean updateUser(User user) {

        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        String id = String.valueOf(user.getUserId());
        session.update(id, user);

        tx.commit();

        return true;
    }

    @Override
    public List<User> getAllUsersLoggedIn() {
        Session session = HibernateUtil.getSession();


        List loggedIn = session.createQuery("from User WHERE loggedIn = '" + true + "' ").list();

        //System.out.println(loggedIn);
        return loggedIn;
    }

    /**
     * Utilizes an HQL query to return a single unique result from the user table.
     * Returns user with corresponding email and password authorization.
     * @param email
     * @param password
     * @return
     */

    @Override
    public User login(String email, String password) {
        Session session = HibernateUtil.getSession();

        User user = session.createQuery("from User WHERE email = '" + email + "' AND password = '" + password +"'", User.class).uniqueResult();

        return user;
    }
}
