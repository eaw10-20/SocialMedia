package base.dao;

import base.model.Likes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.HibernateUtil;

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
        sesFact.getCurrentSession().save(like);
    }

    @Override
    public void unLike(Likes like) {
        sesFact.getCurrentSession().delete(like);
    }

    ////Constructors

    public LikesDaoImpl(){

    }

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
