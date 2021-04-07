package dao;

public interface LikesDao {

    public int getAllLikesOnPost(int id);

    public void addLike(int userId, int postId);
}
