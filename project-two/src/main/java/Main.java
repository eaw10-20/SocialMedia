import dao.UserDaoImpl;
import model.User;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        UserDaoImpl n = new UserDaoImpl();
        User dan = new User("Frank", "LeHioya", "frank@email.com", "12356", "Mikey", "WOW.jpeg");
        User dan2 = new User("Ben", "Big", "Big@email.com", "12356", "Destroyer", "face.jpeg");
        n.createUser(dan);
        n.createUser(dan2);
    }
}
