package model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Date;     <---- Commenting this out to fix temporal error. Delete if no problems
import java.util.Date;

//Post model
@Entity
@Table(name="User_Post")
public class Post {

    //Auto generated serial number and primary key of User_Post
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    //Column post description
    @Column(name = "post_description")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "post_submitted")
    private Date submitted;

    //Each post can have many photos
    //Photos are stored in the photoList List
    @OneToMany(mappedBy = "myPost", fetch = FetchType.EAGER)
    private List<Photos> photoList = new ArrayList<>();

    //Many posts have a single creator (aka user)
    //Connected to User in the @JoinColumn portion with a new column named user_id
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User myUser;

    //Many to many connection that is mapped by posts
    //posts is found in the User class and is the ArrayList that contains all posts that a user could have
    //Connecting two Arrays??? Not to sure how this works
    @ManyToMany(mappedBy = "posts")
    private List<User> users = new ArrayList<>();

    //Constructors
    public Post() {
    }

    public Post(String description, User user) {
        this.description = description;
        this.myUser = user;
    }

    public Post(int postId, String description, User user) {
        this.postId = postId;
        this.description = description;
        this.myUser = user;
    }


    public Post(int postId, String description, User user, List<User> users) {
        this.postId = postId;
        this.description = description;
        this.myUser = user;
        this.users = users;
    }

    public Post(int postId, String description, Date submitted, List<Photos> photoList, User myUser, List<User> users) {
        this.postId = postId;
        this.description = description;
        this.submitted = submitted;
        this.photoList = photoList;
        this.myUser = myUser;
        this.users = users;
    }

    //Getters and Setters
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return myUser;
    }

    public void setUser(User user) {
        this.myUser = user;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    //toString() method

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", description='" + description + '\'' +
                ", submitted=" + submitted +
                ", photoList=" + photoList +
                ", myUser=" + myUser +
                ", users=" + users +
                '}';
    }
}
