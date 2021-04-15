package base.controller;

import base.dao.LikesDaoImpl;
import base.model.Likes;
import base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class LikesController {

    private LikesDaoImpl likesDao;

    //http://localhost:9005/social/api/likePost
    @PostMapping(value="/likePost")
    public void likePost(@RequestBody Likes like){
        likesDao.addLike(like);
    }

    //http://localhost:9005/social/api/unlikePost
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

    @Autowired
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

};
