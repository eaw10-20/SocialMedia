package base.controller;

import base.dao.LikesDaoImpl;
import base.model.Likes;
import base.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Controller
@RequestMapping("/api")
public class LikesController {

    private LikesDaoImpl likesDao;

    /**
     * Utilizes the Likes(int postId, int userId) constructor to add a
     * new Like to the Likes junction table
     *
     * Temporary URL: http://localhost:9005/social/api/likePost
     *
     * @param like
     */

    @PostMapping(value="/likePost")
    public void likePost(@RequestBody Likes like){
        likesDao.addLike(like);
    }

    /**
     * Utilizes the Likes(int postId, intUserId) constructor to delete
     * a like from the Likes junction table.
     *
     * Temporary URL: http://localhost:9005/social/api/unlikePost
     *
     * @param like
     */

    @PutMapping(value="/unlikePost")
    public void unlikePost(@RequestBody Likes like){
        likesDao.unLike(like);
    }

    //http://localhost:9005/social/api/getAllLikes
    @GetMapping(value="/allLikes")
    public Long getAllLikesOnPost(@RequestBody Likes like){
        int id = like.getPostId();
        return likesDao.getAllLikesOnPost(id);
    }

    ////Constructors

    public LikesController(){

    }

    public LikesController(LikesDaoImpl likesDao) {
        this.likesDao = likesDao;
    }



    ////Getters and Setters

    public LikesDaoImpl getLikesDao() {
        return likesDao;
    }

    public void setLikesDao(LikesDaoImpl likesDao) {
        this.likesDao = likesDao;
    }
}
