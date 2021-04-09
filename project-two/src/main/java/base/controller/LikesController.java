package base.controller;

import base.dao.LikesDaoImpl;

public class LikesController {

    private LikesDaoImpl likesDao;





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
