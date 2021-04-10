package base.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//User model
@Entity
@Table(name="Users")
public class User {

    //Auto generated serial number and primary key of Hibernate_Users
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    //Column Firstname
    @Column(name="user_fname", nullable = false)
    private String fname;

    //Column LastName
    @Column(name="user_lname", nullable = false)
    private String lname;

    //Column Email
    @Column(name="user_email", nullable = false)
    private String email;

    //Column Password
    @Column(name="user_password", nullable = false)
    private String password;

    //Column username
    @Column(name="user_username")
    private String username;

    //Column photo string
    @Column(name="user_avatar", nullable = false)
    private String avatar;

    //Column for logged in
    @Column(name="loggedin")
    private boolean loginStatus;

//    Each User can have many post
//    User posts are stored in List<Post>????
    @OneToMany(mappedBy = "myUser", fetch = FetchType.EAGER)
    private List<Post> postList = new ArrayList<>();

    //Many to many relation with Post class
    //Create a new table name Likes and have two columns named user_id and post_id
    //In the User class able to call post that have User id and stored it in posts List
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="Likes",
            joinColumns = {@JoinColumn (name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")}
    )
    private List<Post> postLikes = new ArrayList<>();


    //Constructors
    public User() {
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String fname, String lname, String email, String password, String username, String avatar) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.avatar = avatar;
    }


    public User(int userId, String fname, String lname, String email, String password, String username, String avatar, boolean loginStatus, List<Post> postList) {
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.avatar = avatar;
        this.loginStatus = loginStatus;
        this.postList = postList;
    }

    public User(int userId, String fname, String lname, String email, String password, String username, String avatar, boolean loginStatus, List<Post> postList, List<Post> postLikes) {
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.avatar = avatar;
        this.loginStatus = loginStatus;
        this.postList = postList;
        this.postLikes = postLikes;
    }




    //Getters and Setters
    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }


//    public User(List<Post> posts) {
//        this.posts = posts;
//    }
//
//    public List<Post> getPostList() {
//        return postList;
//    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    //toString() method

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", loginStatus=" + loginStatus +
                ", postList=" + postList +
                ", posts=" + postLikes +
                '}';
    }
}
