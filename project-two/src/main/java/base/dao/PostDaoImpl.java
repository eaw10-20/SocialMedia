package base.dao;

import base.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.HibernateUtil;

import javax.transaction.Transactional;
import java.util.List;

@Repository("postDao")
@Transactional
public class PostDaoImpl implements PostDao {


    private SessionFactory sesFact;


    ////Business Logic

    @Override
    public void createPost(Post post) {
        sesFact.getCurrentSession().save(post);
    }

    @Override
    public List<Post> getPostsByUserID(int userId) {

        String hql = "FROM Post WHERE user_id =" + userId;
        List<Post> userPosts = sesFact.getCurrentSession().createQuery(hql).list();

        return userPosts;
    }

    @Override
    public void updatePost(Post post) {
        sesFact.getCurrentSession().update(post);
    }

    public List<Post> getAllPosts(){
        List<Post> userPosts = sesFact.getCurrentSession().createQuery("FROM Post ORDER BY post_submitted DESC", Post.class).list();
        for(Post p : userPosts){
            System.out.println(p.getUserId());
        }
        System.out.println(userPosts.toString());
        return userPosts;
    }

    ////Constructors

    public PostDaoImpl(){

    }

    public PostDaoImpl(SessionFactory sesFact) {
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
