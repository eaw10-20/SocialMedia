package base.dao;

import base.model.Likes;

public interface LikesDao {

    public Long getAllLikesOnPost(int id);

    public void addLike(Likes like);

    public void unLike(Likes like);
}
