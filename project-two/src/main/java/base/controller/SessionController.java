package base.controller;

import base.model.User;
import base.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
public class SessionController {

    private UserDaoImpl userDao;



    //http://localhost:9005/social/api/getUser/
    @GetMapping(value="/api/getUser")
    @CrossOrigin(allowCredentials = "true")
    public User getCurrentUser(HttpSession session) {
//        System.out.println("In the get current user session");

        User user = (User) session.getAttribute("currentUser");
        return user;
    }


    /**
     * Checks user email and password
     * If user not null sets a session with key 'currentUser' and changes logged in status to true
     * @param user
     * @param session
     * @return
     */
    //    http://localhost:9005/social/login
    @PostMapping(value="/login")
    @CrossOrigin(allowCredentials = "true")
    public @ResponseBody
    User login(@RequestBody User user, HttpSession session){
        System.out.println("in the check credentials method");
        String email = user.getEmail();
        String password = user.getPassword();
        User loggedInUser = userDao.login(email, password);
        if(loggedInUser != null){
            loggedInUser.setLoginStatus(true);
            session.setAttribute("currentUser", loggedInUser);
            userDao.updateUser(loggedInUser);
            return loggedInUser;
        }
        return null;
    }

    /**
     * Sets user's loginStatus to false, updates the postgres table with loginstatus,
     * then invalidates the HttpSession.
     * @param user
     * @param req
     * @return
     */

    @GetMapping(value="/logout")
    public void logout(@RequestBody User user, HttpServletRequest req){
        //we COULD just make our method parameter "HttpSession" but we could ALSO
        // get the HttpServletRequest object which happens to contain the HttpSession

        user.setLoginStatus(false);
        userDao.updateUser(user);
        HttpSession session = req.getSession();
        session.invalidate();

    }

    public SessionController(){

    }

    @Autowired
    public SessionController(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

}
