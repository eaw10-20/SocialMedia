package base.controller;

import base.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class SessionController {


    public void setUserSession (HttpSession session, User user) {
        System.out.println("In the set session method");
        session.setAttribute("loggedin", user);
    }


    //http://localhost:9005/social/api/getUser/
    @GetMapping(value="/getUser")
    public User getCurrentUser(HttpSession session) {
        System.out.println("In the get current user session");

        System.out.println(session.getAttribute("loggedin"));
        User user = (User) session.getAttribute("loggedin");
//        System.out.println(user.toString());
        return user;
    }


}
