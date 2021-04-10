package base.controller;

import base.dao.PostDaoImpl;
import base.model.Post;
import base.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@Controller
@RequestMapping("/api")
public class PostController {

    private PostDaoImpl postDao;

    /**Checks HttpSession for null value.  Returns all posts from the
     * Posts table if session attribute "currentUser" is not null.
     *
     * Temporary URL: http://localhost:9005/social/api/getAllPosts
     *
     * @param session
     * @return
     */

    @GetMapping(value="/getAllPosts")
    public @ResponseBody
    List<Post> getAllPosts(HttpSession session){

        if(session.getAttribute("currentUser") != null){
            return postDao.getAllPosts();
        }
        return null;
    }


    /**If session attribute "currentUser" is not null createNewPost creates a
     * new Post entry in the Posts table.
     *
     * Temporary URL: http://localhost:9005/social/api/createPost
     *
     * @param newPost
     * @param session
     */

    @PostMapping(value="/createPost")
    public void createNewPost(@RequestBody Post newPost, HttpSession session){
        if(session.getAttribute("currentUser") != null){
            postDao.createPost(newPost);
        }

    }

    /**First checks that session attribute "currentUser" is not null.
     * Then returns all posts corresponding to a given int userId.
     *
     * Temporary URL: http://localhost:9005/social/api/getPostsByUserId
     *
     * @param id
     * @param session
     * @return
     */


    @PutMapping(value="getPostsByUserId", params={"id"}, produces="application/json")
    public List<Post> getPostsById(int id, HttpSession session){
        if(session.getAttribute("currentUser") != null){
            return postDao.getPostsByUserID(id);
        }
        return null;
    }

    /**
     *
     * Temporary URL: http://localhost:9005/social/api/updatePost
     *
     * @param updatedPost
     * @param session
     */

    @PutMapping(value="/updatePost")
    public void updatePost(@RequestBody Post updatedPost, HttpSession session){
        if(session.getAttribute("currentUser") != null){
            postDao.updatePost(updatedPost);
        }
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
