package base.controller;


import base.dao.UserDao;
import base.dao.UserDaoImpl;

import base.model.User;
import base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    private UserDaoImpl userDao;
    private UserService uServ;


    /**
     * http://localhost:9005/social/api/getAllFriends
     * HTTP request for a list of All users in the database
     * Returns a list of all users in the database
     * @return
     */
    @GetMapping(value = "/getAllFriends")
    @CrossOrigin(allowCredentials = "true")
    public @ResponseBody List<User> getAllFriends () {
        List<User> friendList = userDao.getAllUsers();
        return friendList;
    }

    /**
     * http://localhost:9005/social/api/createUser
     * HTTP request to add a new user to the database
     * Takes inputted password and encrypts it before adding user
     * @param user
     */
    @PostMapping(value="/createUser" , produces="application/json")
    @CrossOrigin(allowCredentials = "true")
    public void createNewUser(@RequestBody User user){
        String encryptPass = uServ.encryptPass(user.getPassword());
        user.setPassword(encryptPass);
        userDao.createUser(user);
    }


    /**
     * http://localhost:9005/social/api/updateUser
     * HTTP request to update existing user information
     * Takes password and encrypts before inputting data
     * @param newUser
     */
    @PostMapping(value="/updateUser")
    @CrossOrigin(allowCredentials = "true")
    public void updateUser(@RequestBody User newUser){
        String encrpytPass = uServ.encryptPass(newUser.getPassword());
        newUser.setPassword(encrpytPass);
        System.out.println(newUser.toString());
        userDao.updateUser(newUser);
    }

    /**
     * http://localhost:9005/social/api/emailPassword
     * HTTP request that sends an email to is given in the email param
     * If the user exists in the database send an email with decrypted password
     * @param email
     */
    @GetMapping(value="/emailPassword", params = {"email"})
    public void emailPassword (String email) {
        User user = userDao.getUserByEmail(email);
        String decrpytPass = uServ.decryptPass(user.getPassword());
        user.setPassword(decrpytPass);
        uServ.passwordReset(user);
    }


    ////Constructors

    public UserController(){
    }

    ///Autowired constructor to inject the repo directly

    @Autowired
    public UserController(UserDaoImpl userDao, UserService uServ){

        this.userDao = userDao;
        this.uServ = uServ;
    }

    ////Getters and Setters

    public UserDao getUserDao() {
        return userDao;
    }

    public UserService getuServ() {return uServ;}


    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void setuServ(UserService uServ) {this.uServ=uServ;}


}
