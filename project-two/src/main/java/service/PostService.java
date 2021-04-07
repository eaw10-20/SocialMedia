package service;

import dao.PostDaoImpl;
import model.Post;

import java.util.List;

public class PostService {

    PostDaoImpl postDao;


    ////Constructors

    public PostService(){

    }

    public PostService(PostDaoImpl postDao) {
        this.postDao = postDao;
    }


    ////Getters and Setters

    public PostDaoImpl getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDaoImpl postDao) {
        this.postDao = postDao;
    }


    ////Business Logic

    public void createPost(Post post){
        postDao.createPost(post);
    }

    public List<Post> getPostsByUserId(int userId){
        return postDao.getPostsByUserID(userId);
    }

    public void updatePost(Post post){
        postDao.updatePost(post);
    }

}
