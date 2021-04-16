package base.controller;


import base.dao.UserDao;
import base.dao.UserDaoImpl;

import base.model.User;
import base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Context;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class    UserController {

    private UserDaoImpl userDao;
    private UserService uServ;

    //http://localhost:9005/social/api/getUserById/
    //changed to GET for testing purposes
    @CrossOrigin(allowCredentials = "true")
    @GetMapping(value="/getUserById", params={"id"}, produces="application/json")
    public @ResponseBody
    ResponseEntity<User> getUserById(int id, HttpSession session){

        //For testing logging in user
        User loggedInUser;
        System.out.println("in the get user by id method");
        System.out.println(id);

        loggedInUser = userDao.getUserById(id);
        if(loggedInUser != null){
            loggedInUser.setLoginStatus(true);
            session.setAttribute("currentUser", loggedInUser);
            userDao.updateUser(loggedInUser);
        }
        return new ResponseEntity<User>(loggedInUser,
                HttpStatus.OK);
    }


    @GetMapping(value = "/getAllFriends")
    @CrossOrigin(allowCredentials = "true")
    public @ResponseBody List<User> getAllFriends (HttpSession session) {
//        User user = (User) session.getAttribute("currentUser");
        List<User> friendList = userDao.getAllUsers();

        return friendList;
    }


    //http://localhost:9005/social/api/createUser
    @PostMapping(value="/createUser" , produces="application/json")
    @CrossOrigin(allowCredentials = "true")
    public void createNewUser(@RequestBody User user){
        String encryptPass = uServ.encryptPass(user.getPassword());
        user.setPassword(encryptPass);
        userDao.createUser(user);
    }


    //http://localhost:9005/social/api/getUserByFullName
    @PutMapping(value="/getUserByFullName", params={"firstName", "lastName"}, produces="application/json")
    public @ResponseBody
    ResponseEntity<User> getUserByFullName(String firstName, String lastName){
        return new ResponseEntity<User>(userDao.getUserByFullName(firstName, lastName),
                HttpStatus.MULTI_STATUS.I_AM_A_TEAPOT);
    }

    @GetMapping(value="/getAllUsersLoggedIn")
    public @ResponseBody
    List<User> getsAllUsersLoggedIn(){
        return userDao.getAllUsersLoggedIn();
    }

    //http://localhost:9005/social/api/updateUser
    @PostMapping(value="/updateUser")
    @CrossOrigin(allowCredentials = "true")
    public void updateUser(@RequestBody User newUser){
        String encrpytPass = uServ.encryptPass(newUser.getPassword());
        newUser.setPassword(encrpytPass);
        System.out.println(newUser.toString());
        userDao.updateUser(newUser);
    }

    //http://localhost:9005/social/api/emailPassword
    @GetMapping(value="/emailPassword", params = {"email"})
    public void emailPassword (String email) {
        System.out.println("in the email password method in user controller");
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
