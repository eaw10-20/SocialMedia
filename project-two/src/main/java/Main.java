import dao.UserDaoImpl;
import model.User;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        UserDaoImpl n = new UserDaoImpl();
        User dan = new User("Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "WOW.jpeg");
        User dan2 = new User("Ben", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
        User dan3 = new User("John", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
        n.createUser(dan);
        n.createUser(dan2);
        n.createUser(dan3);

        System.out.println(n.getAllUsersLoggedIn());
        //n.updateUser(dan3);
        //n.login("frank@email.com", "12356");

       // User dan4 = n.login("frank@email.com", "12356");
        //System.out.println("Got the account for "+dan4.getFname()+" "+dan4.getLname());
    }
}
