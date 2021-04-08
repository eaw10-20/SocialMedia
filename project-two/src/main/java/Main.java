import dao.LikesDaoImpl;
import dao.PostDaoImpl;
import dao.UserDaoImpl;
import model.Likes;
import model.Post;
import model.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        UserDaoImpl n = new UserDaoImpl();
        LikesDaoImpl l = new LikesDaoImpl();
        PostDaoImpl p = new PostDaoImpl();


        User dan = new User("Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "WOW.jpeg");
        User dan2 = new User("Ben", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
        User dan3 = new User("John", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");

        n.createUser(dan);
        n.createUser(dan2);
        n.createUser(dan3);

        List<User> usersList = new ArrayList<>();

        usersList.add(dan);
        usersList.add(dan2);
        usersList.add(dan3);

        Post post1 = new Post(1, "post", dan);
        Post post2 = new Post(1, "post here too", dan2);
        Post post3 = new Post(1,"post here too", dan3);

        System.out.println(post1.getPostId());
        System.out.println(post2.getPostId());
        System.out.println(post3.getPostId());

        p.createPost(post1);
        p.createPost(post2);
        p.createPost(post3);

        int postId = post1.getPostId();
        int danId = dan.getUserId();
        int dan2Id = dan2.getUserId();
        int dan3Id = dan3.getUserId();

        Likes like = new Likes(postId, danId);
        Likes like2 = new Likes(postId, dan2Id);
        Likes like3 = new Likes(2, postId, dan3Id);

//        l.addLike(like);
//        l.addLike(like2);
//        l.addLike(like3);

        l.unLike(like3);
        System.out.println("This is the total number of Likes: " + l.getAllLikesOnPost(postId));

//        System.out.println(n.getAllUsersLoggedIn());
        //n.updateUser(dan3);
        //n.login("frank@email.com", "12356");

       // User dan4 = n.login("frank@email.com", "12356");
        //System.out.println("Got the account for "+dan4.getFname()+" "+dan4.getLname());
    }
}
