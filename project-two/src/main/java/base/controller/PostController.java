package base.controller;

import base.dao.PostDaoImpl;
import base.model.Post;
import base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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

//    http://localhost:9005/social/api/getPostsByUserId
    @GetMapping(value="getPostsByUserId", params={"id"}, produces="application/json")
    public List<Post> getPostsById(int id){
        System.out.println("Get post by user id method");
        return postDao.getPostsByUserID(id);
    }


    @PutMapping(value="/updatePost")
    public void updatePost(@RequestBody Post updatedPost){
         postDao.updatePost(updatedPost);
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

        Post post1 = new Post(1, "post", 1);
        Post post2 = new Post(1, "post here too", 2);
        Post post3 = new Post(1,"post here too", 3);

        postDao.createPost(post1);
        postDao.createPost(post2);
        postDao.createPost(post3);
    }
}
