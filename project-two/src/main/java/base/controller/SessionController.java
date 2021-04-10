package base.controller;

import base.dao.UserDaoImpl;
import base.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    private UserDaoImpl userDao;

    //    http://localhost:9005/social/login
    @PostMapping(value="/login")
    public @ResponseBody
    User login(@RequestBody User user, HttpSession session){
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
