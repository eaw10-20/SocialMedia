package base.controller;


import base.dao.UserDao;
import base.dao.UserDaoImpl;

import base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    private UserDaoImpl userDao;

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



    //http://localhost:9005/social/api/createUser
    @PostMapping(value="/createUser")
    public void createNewUser(@RequestBody User newUser){
        userDao.createUser(newUser);
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

    //http://localhost:9005/social/api/udpateUser
    @PostMapping(value="/udpateUser")
    public void updateUser(@RequestBody User newUser){
        userDao.updateUser(newUser);
    }


    ////Constructors

    public UserController(){
        insertInitialValues();
    }

    ///Autowired constructor to inject the repo directly

    @Autowired
    public UserController(UserDaoImpl userDao){

        this.userDao = userDao;
        insertInitialValues();
    }

    ////Getters and Setters

    public UserDao getUserDao() {
        insertInitialValues();
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        insertInitialValues();
        this.userDao = userDao;
    }

    /**
     * Insert initial values for testing purposes
     */

    public void insertInitialValues(){

//        User dan = new User("Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "WOW.jpeg");
//        User dan2 = new User("Ben", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
//        User dan3 = new User("John", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
//
//        userDao.createUser(dan);
//        userDao.createUser(dan2);
//        userDao.createUser(dan3);
    }
}
