package base.dao;

import base.model.Likes;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import javax.transaction.Transactional;

@Repository("likesDao")
@Transactional
public class LikesDaoImpl implements LikesDao {


    SessionFactory sesFact;

    /**
     * Takes a user_id and post_id and adds to a postLikes junction table
     * @param like
     */
    public void addLike(Likes like){
        sesFact.getCurrentSession().persist(like);
    }

    /**
     * Takes a user_id and post_id and removes entity from postLikes junction table
     * @param like
     */
    @Override
    public void unLike(Likes like) {

        int postId = like.getPostId();
        int userId = like.getUserId();
        Query query = sesFact.getCurrentSession().createQuery("DELETE from Likes WHERE post_id ='" + postId + "' AND user_id = '" + userId +"'");

        query.executeUpdate();
    }

    ////Constructors

    public LikesDaoImpl(){

    }

    @Autowired
    public LikesDaoImpl(SessionFactory sesFact) {
        this.sesFact = sesFact;
    }

    ////Getters and Setters


    public SessionFactory getSesFact() {
        return sesFact;
    }

    @Autowired
    public void setSesFact(SessionFactory sesFact) {
        this.sesFact = sesFact;
    }
}
