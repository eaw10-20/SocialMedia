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
//        List<Post> postList = postDao.getAllPosts();
        return postDao.getAllPosts();
    }

    //http://localhost:9005/social/api/post/create

    /**
     * Create a new post
     * @param newPost
     */
    @PostMapping(value="/post/create")
    public void createNewPost(@RequestBody Post newPost){
        System.out.println("In the create new post method");
        postDao.createPost(newPost);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        System.out.println("Returning HTTP 400 bad requst: " + e);
    }

    /**
     * Takes in a user id and returns a user object that contains a list of posts
     * @param id
     * @return
     */
//    http://localhost:9005/social/api/getPostsByUserId
    @GetMapping(value="getPostsByUserId", params={"id"}, produces="application/json")
    public User getPostsById(int id){
        List<Post> allUserPosts = new ArrayList<>();
        System.out.println("Get post by user id method");
        User user = userDao.getUserById(id);
        allUserPosts = postDao.getPostsByUserID(id);
        Collections.copy(user.getPostList(),allUserPosts);

        return user;

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
