package base.controller;

import base.dao.PhotoDao;
import base.dao.PhotoDaoImpl;
import base.dao.PostDaoImpl;
import base.dao.UserDaoImpl;
import base.model.Post;
import base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class PostController {

    private PostDaoImpl postDao;
    private UserDaoImpl userDao;


    //http://localhost:9005/social/api/getAllPosts

    @GetMapping(value="/getAllPosts")
    public @ResponseBody
    List<Post> getAllPosts(){
        List<Post> postList = postDao.getAllPosts();

        return postList;
    }

    //http://localhost:9005/social/api/post/create

    /**
     * Create a new post
     * @param newPost
     */
    @PostMapping(value="/post/create")
    public void createNewPost(@RequestBody Post newPost){
        System.out.println("In the create new post method");
        System.out.println(newPost);
        postDao.createPost(newPost);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        System.out.println("Returning HTTP 400 bad requst: " + e);
    }



    //    http://localhost:9005/social/api/updatePost
    @PutMapping(value="/updatePost")
    public void updatePost(@RequestBody Post updatedPost){
        System.out.println("in the update post");
//        postDao.updatePost(updatedPost);
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



//        User dan = new User("Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "https://merriam-webster.com/assets/mw/images/article/art-global-footer-recirc/character-dragon-with-sunglasses-image-6332-f5704f661135d682466ec9f8b327e014@1x.jpg");
        User dan = new User("Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_1.jpg");
//        User dan2 = new User("Lia", "Summer", "summer@email.com", "12356", "Ms.Summer", "http://www.themashabrand.com/templates/bootsnipp/post/assets/img/1.jpg");
        User dan2 = new User("Lia", "Summer", "summer@email.com", "12356", "Ms.Summer", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_2");
//        User dan3 = new User("John", "Big", "Big@email.com", "12356", "Destroyer", "https://rudyvrodriguez.files.wordpress.com/2010/10/cute-destroyer.jpg?w=327");
        User dan3 = new User("John", "Big", "Big@email.com", "12356", "Destroyer", "https://rev-p2-socialmedia-2102.s3.us-east-2.amazonaws.com/avatar_3");


        Post post1 = new Post(1, "post", dan);
        Post post2 = new Post(1, "post here too", dan2);
        Post post3 = new Post(1,"post here too", dan3);

        postDao.createPost(post1);
        postDao.createPost(post2);
        postDao.createPost(post3);


    }
}
