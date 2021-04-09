package base.controller;

import base.dao.PostDaoImpl;
import base.model.Post;
import base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api")
public class PostController {

    private PostDaoImpl postDao;


    //http://localhost:9005/social/api/getAllPosts
    @GetMapping(value="/getAllPosts")
    public @ResponseBody
    List<Post> getAllPosts(){
        return postDao.getAllPosts();
    }

    //http://localhost:9005/social/api/createPost
    @PostMapping(value="/createPost")
    public void createNewPost(@RequestBody Post newPost){
        
        postDao.createPost(newPost);
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

        User dan = new User("Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "WOW.jpeg");
        User dan2 = new User("Ben", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
        User dan3 = new User("John", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");

        Post post1 = new Post(1, "post", dan);
        Post post2 = new Post(1, "post here too", dan2);
        Post post3 = new Post(1,"post here too", dan3);

        postDao.createPost(post1);
        postDao.createPost(post2);
        postDao.createPost(post3);
    }
}
