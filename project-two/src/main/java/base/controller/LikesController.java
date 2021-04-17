package base.controller;

import base.dao.LikesDaoImpl;
import base.model.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class LikesController {

    private LikesDaoImpl likesDao;


    /**
     * http://localhost:9005/social/api/likePost
     * HTTP request to add a user_id and post_id to a postLikes junction table
     * @param like
     */
    @PostMapping(value="/likePost")
    public void likePost(@RequestBody Likes like){
        likesDao.addLike(like);
    }


    /**
     * http://localhost:9005/social/api/unlikePost
     * HTTP request to remove a user_id and post_id from a postLikes junction table
     * @param like
     */
    @PutMapping(value="/unlikePost")
    public void unlikePost(@RequestBody Likes like){
        likesDao.unLike(like);
    }



    ////Constructors

    public LikesController(){

    }

    @Autowired
    public LikesController(LikesDaoImpl likesDao) {

        this.likesDao = likesDao;


    }



    ////Getters and Setters

    public LikesDaoImpl getLikesDao() {
        return likesDao;
    }

    @Autowired
    public void setLikesDao(LikesDaoImpl likesDao) {
        this.likesDao = likesDao;
    }


}

