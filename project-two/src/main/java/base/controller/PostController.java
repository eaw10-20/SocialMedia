package base.controller;

import base.dao.PostDaoImpl;
import base.model.Post;
import base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/api")
public class PostController {

    private PostDaoImpl postDao;


    @GetMapping(value="/getAllPosts")
    public @ResponseBody
    List<Post> getAllFoods(){
        return postDao.getAllPosts();
    }



    ////Constructors

    public PostController(){

    }
    @Autowired
    public PostController(PostDaoImpl postDao) {
        this.postDao = postDao;
        insertInitialValues();
    }


    ////Getters and Setters

    public PostDaoImpl getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDaoImpl postDao) {
        this.postDao = postDao;
    }

    public void insertInitialValues(){
//        User dan = new User("Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "WOW.jpeg");
//        User dan2 = new User("Ben", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
//        User dan3 = new User("John", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
//
//        Post post1 = new Post(1, "post", dan);
//        Post post2 = new Post(1, "post here too", dan2);
//        Post post3 = new Post(1,"post here too", dan3);
//
//        postDao.createPost(post1);
//        postDao.createPost(post2);
//        postDao.createPost(post3);
    }
}