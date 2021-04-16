package base.dao;

import base.model.Likes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.HibernateUtil;
import org.hibernate.query.Query;

import javax.transaction.Transactional;

@Repository("likesDao")
@Transactional
public class LikesDaoImpl implements LikesDao {


    SessionFactory sesFact;

    @Override
    public Long getAllLikesOnPost(int postId) {

        Long totalLikes = (Long)sesFact.getCurrentSession().createQuery("SELECT count(user_id) from Likes WHERE post_id ='" + postId + "'").getSingleResult();
        return totalLikes;
    }

    public void addLike(Likes like){
        System.out.println("adding like");
        sesFact.getCurrentSession().persist(like);
    }

    @Override
    public void unLike(Likes like) {

        int postId = like.getPostId();
        System.out.println("postid " + postId);
        int userId = like.getUserId();
        System.out.println(("userId " + userId));
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
