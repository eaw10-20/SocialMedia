package dao;

import model.Post;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class PostDaoImpl implements PostDao{
    @Override
    public void createPost(Post post) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.save(post);

        tx.commit();
    }

    @Override
    public List<Post> getPostsByUserID(int userId) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        String hql = "FROM post WHERE user_id =" + userId;
        List<Post> userPosts = session.createQuery(hql).list();

        return userPosts;
    }

    @Override
    public void updatePost(Post post) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.update(post);

        tx.commit();
    }
}
