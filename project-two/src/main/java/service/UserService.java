package service;

import dao.UserDaoImpl;
import model.User;

import java.util.List;

public class UserService {

    private UserDaoImpl userDao;


    ////Constructors

    public UserService(){

    }

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }


    ////Getters and Setters for UserDaoImpl userDao

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }


    ////Business logic

    /**
     * createUser(User user) takes one User object as a parameter and inserts it into
     * the database. It does not return the new user object, as all relevant data is already
     * available to the front-end.
     * @param user
     */
    public void createUser(User user){
        userDao.createUser(user);
    }

    public void updateUser(User user){

        ///updateUser returns a boolean, insert logic here to work with that
        userDao.updateUser(user);
    }

    /**
     * Returns a List of all User objects currently logged into the web app
     * @return List<User>
     */

    public List<User> getAllLoggedInUsers(){
        return userDao.getAllUsersLoggedIn();
    }

    public User login(String email, String password){
        return userDao.login(email, password);
    }

    public User getUserByFullName(String firstName, String lastName){
        return userDao.getUserByFullName(firstName, lastName);
    }
}
