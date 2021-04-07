import dao.UserDaoImpl;
import model.Post;
import model.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        UserDaoImpl n = new UserDaoImpl();


        User dan = new User(1, "Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "WOW.jpeg", true );
        User dan2 = new User(2, "Ben", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg", true);

        n.createUser(dan);
        n.createUser(dan2);
        User dan3 = new User(2, "Frank", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg", true);

        n.updateUser(dan3);

        User dan4 = n.login("frank@email.com", "12356");
        System.out.println("Got the account for "+dan4.getFname()+" "+dan4.getLname());
    }
}
