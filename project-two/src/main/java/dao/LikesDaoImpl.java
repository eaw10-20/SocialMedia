package dao;

import model.Likes;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class LikesDaoImpl implements LikesDao {




    @Override
    public Long getAllLikesOnPost(int postId) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        Long totalLikes = (Long)session.createQuery("SELECT count(user_id) from Likes WHERE post_id ='" + postId + "'").getSingleResult();

        tx.commit();
        return totalLikes;
    }

    public void addLike(Likes like){
        Session session = HibernateUtil.getSession();

        session.save(like);
    }

    @Override
    public void unLike(Likes like) {
        Session session = HibernateUtil.getSession();

        session.delete(like);
    }
}
