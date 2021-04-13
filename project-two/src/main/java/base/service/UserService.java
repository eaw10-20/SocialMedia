package base.service;

import base.dao.UserDaoImpl;
import base.model.User;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import java.util.List;

public class UserService {

    private UserDaoImpl userDao;


    ////Constructors

    public UserService(){

    }

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }


    ////Getters and Setters for UserDaoImpl userDao

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }


    ////Business logic

    /**
     * createUser(User user) takes one User object as a parameter and inserts it into
     * the database. It does not return the new user object, as all relevant data is already
     * available to the front-end.
     * @param user
     */
    public void createUser(User user){
        userDao.createUser(user);
    }

    public void updateUser(User user){

        ///updateUser returns a boolean, insert logic here to work with that
        userDao.updateUser(user);
    }

    /**
     * Returns a List of all User objects currently logged into the web app
     * @return List<User>
     */

    public List<User> getAllLoggedInUsers(){
        return userDao.getAllUsersLoggedIn();
    }

    public User login(String email, String password){
        return userDao.login(email, password);
    }

    public User getUserByFullName(String firstName, String lastName){
        return userDao.getUserByFullName(firstName, lastName);
    }

    /**
     * Takes in a password and encrypts it so that the password can be safely stored  in the database
     * Takes in a string password and returns an encrypted string.
     * @param pass
     * @return
     */
    private String encryptPass(String pass){
        try {
            //Creating a Signature object
            Signature sign = Signature.getInstance("SHA256withRSA");

            //Creating KeyPair generator object
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

            //Initializing the KeyPairGenerator
            keyPairGen.initialize(2048);

            //Generate the pair of keys
            KeyPair pair = keyPairGen.generateKeyPair();

            //Getting the public key from the key pair
            PublicKey publicKey = pair.getPublic();

            //Creating a Cipher object

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            System.out.println("step");
            //Initializing a Cipher object
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            //Adding data to the cipher
            byte[] input = pass.getBytes();
            cipher.update(input);

            //encrypting the data
            byte[] cipherText = cipher.doFinal();
            return new String(cipherText, "UTF8");
        }catch (Exception e){
            System.out.println("FAILED TO ENCRYPT");
            return null;
        }
    }
}
