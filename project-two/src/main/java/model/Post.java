package model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User_Post")
public class Post {

    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "post_description")
    private String description;

    @OneToMany(mappedBy = "myPost", fetch = FetchType.EAGER)
    private List<Photos> photoList = new ArrayList<>();

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User myUser;

    @ManyToMany(mappedBy = "post")
    private List<User> users = new ArrayList<>();

    public Post() {
    }

    public Post(int userId, String description, User user) {
        this.userId = userId;
        this.description = description;
        this.myUser = user;
    }

    public Post(int postId, int userId, String description, User user) {
        this.postId = postId;
        this.userId = userId;
        this.description = description;
        this.myUser = user;
    }

    public Post(int postId, int userId, String description, User user, List<User> users) {
        this.postId = postId;
        this.userId = userId;
        this.description = description;
        this.myUser = user;
        this.users = users;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", user=" + myUser +
                ", users=" + users +
                '}';
    }
}
