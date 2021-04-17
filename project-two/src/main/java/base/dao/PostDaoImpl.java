package base.dao;

import base.model.Post;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("postDao")
@Transactional
public class PostDaoImpl implements PostDao {


    private SessionFactory sesFact;


    ////Business Logic

    /**
     * add a new post and then return that post for image saving
     * @param post
     * @return
     */
    @Override

    public Post createPost(Post post) {
        sesFact.getCurrentSession().saveOrUpdate(post);
        return post;
    }



    /**
     * return an array of all posts in the database in descending order based on time submitted
     * @return
     */
    public List<Post> getAllPosts(){
        List<Post> userPosts = sesFact.getCurrentSession().createQuery("FROM Post ORDER BY post_submitted DESC", Post.class).list();
        for(Post p : userPosts){
            Hibernate.initialize(p.getUsers());

        }
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
