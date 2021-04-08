package dao;

import model.Likes;

public interface LikesDao {

    public Long getAllLikesOnPost(int id);

    public void addLike(Likes like);
}
