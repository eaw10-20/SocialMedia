package base.controller;


import base.dao.UserDao;
import base.dao.UserDaoImpl;

import base.model.Post;
import base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@Controller
@RequestMapping("/api")
public class UserController {

    private UserDaoImpl userDao;

    //http://localhost:9005/social/api/getUserById
    @PutMapping(value="/getUserById", params={"id"}, produces="application/json")
    public @ResponseBody
    ResponseEntity<User> getUserById(int id){
        return new ResponseEntity<User>(userDao.getUserById(id),
                HttpStatus.MULTI_STATUS.I_AM_A_TEAPOT);
    }

    //http://localhost:9005/social/api/createUser
    @PostMapping(value="/createUser")
    public void createNewUser(@RequestBody User newUser){
        userDao.createUser(newUser);
    }

//    http://localhost:9005/social/api/login
    @PostMapping(value="/login")
    public @ResponseBody
    User login(@RequestBody User user, HttpSession session){
        String email = user.getEmail();
        String password = user.getPassword();
        User loggedInUser = userDao.login(email, password);
        if(loggedInUser != null){
            session.setAttribute("currentUser", loggedInUser);
            return loggedInUser;
        }
        return null;
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
//
//        User dan = new User("Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "WOW.jpeg");
//        User dan2 = new User("Ben", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
//        User dan3 = new User("John", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
//
//        userDao.createUser(dan);
//        userDao.createUser(dan2);
//        userDao.createUser(dan3);
    }
}
