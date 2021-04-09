package base.dao;

import base.model.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {


    private SessionFactory sesFact;

    /**
     * Inserts one user into the "user" table
     * @param user
     */
    @Override
    public void createUser(User user) {
        sesFact.getCurrentSession().save(user);
    }

    /**
     *
     * @param user
     * @return
     */

    @Override
    public boolean updateUser(User user) {

        String id = String.valueOf(user.getUserId());
        sesFact.getCurrentSession().update(id, user);

        return true;
    }

    @Override
    public List<User> getAllUsersLoggedIn() {

        List loggedIn = sesFact.getCurrentSession().createQuery("from User WHERE loggedIn = '" + true + "' ").list();

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

        User user = sesFact.getCurrentSession().createQuery("from User WHERE email = '" + email + "' AND password = '" + password +"'", User.class).uniqueResult();

        return user;
    }

    @Override
    public User getUserByFullName(String firstName, String lastName) {

        User user = sesFact.getCurrentSession().createQuery("from User WHERE user_fname = '" + firstName + "' AND user_lname = '" + lastName +"'", User.class).uniqueResult();

        return user;

    }

    public User getUserById(int id){
        User user = sesFact.getCurrentSession().get(User.class, id);
        return user;
    }

    ////Constructors

    public UserDaoImpl(){

    }

    public UserDaoImpl(SessionFactory sesFact) {
        this.sesFact = sesFact;
    }

    public SessionFactory getSesFact() {
        return sesFact;
    }

    @Autowired
    public void setSesFact(SessionFactory sesFact) {
        this.sesFact = sesFact;
    }
}
