package base.controller;

import base.dao.LikesDaoImpl;
import base.model.Likes;
import base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
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

    @Autowired
    public void setLikesDao(LikesDaoImpl likesDao) {
        this.likesDao = likesDao;
    }

    @PostMapping(value="/addValues")
    public void addInitialValues(){
        Likes like1 = new Likes(1,1);
        Likes like2 = new Likes(2,1);
        Likes like3 = new Likes(1,2);

        likesDao.addLike(like1);
        likesDao.addLike(like2);
        likesDao.addLike(like3);
    }
}
