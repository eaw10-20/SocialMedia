package base.dao;

import base.model.Post;

import java.util.List;

public interface PostDao {

    public Post createPost(Post post);

    public List<Post> getPostsByUserID(int userId);

    public void updatePost(Post post);

    //saving this for now.. maybe by id would be better?
    //public void deletePost(Post post);
}
