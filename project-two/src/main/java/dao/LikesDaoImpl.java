package dao;

import model.Likes;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class LikesDaoImpl implements LikesDao {




    @Override
    public int getAllLikesOnPost(int postId) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        Integer totalLikes = (Integer)session.createQuery("SELECT count(like_id) from likes WHERE post_id ='" + postId + "'").getSingleResult();

        tx.commit();
        return totalLikes;
    }

    public void addLike(int userId, int postId){
        Session session = HibernateUtil.getSession();

        Likes like =  new Likes();
        session.save(like);
    }
}
