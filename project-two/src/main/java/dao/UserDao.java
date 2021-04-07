package dao;

import model.User;

import java.util.List;

public interface UserDao {

        public void createUser(User user);

        public User login(String email, String password);

        public boolean updateUser(User user);

        public List<User> getAllUsersLoggedIn();

}
