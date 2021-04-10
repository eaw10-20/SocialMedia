package service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import dao.PhotoDaoImpl;
import model.Photos;

import java.util.Map;


public class PhotoService {

    PhotoDaoImpl photoDao;

    ////S3 static variables
    public static Map<String, String> env = System.getenv();
    public static String ACCESS_KEY_ID = System.getenv("S3_ACCESS_KEY");
    public static String ACCESS_SEC_KEY = System.getenv("S3_SEC_KEY");


    ////Constructors

    public PhotoService(){

    }

    public PhotoService(PhotoDaoImpl photoDao) {
        this.photoDao = photoDao;
    }


    ////Getters and Setters

    public PhotoDaoImpl getPhotoDao() {
        return photoDao;
    }

    public void setPhotoDao(PhotoDaoImpl photoDao) {
        this.photoDao = photoDao;
    }


    ////Business Logic
    //dao calls
    public void createPhoto(Photos photo){
        photoDao.uploadPhoto(photo, getS3Client());
    }

    public Photos getPhotobyId(int id){
        return photoDao.getPhotobyId(id, getS3Client());
    }

    public void deletePhoto(Photos photo){
        photoDao.deletePhoto(photo, getS3Client());
    }

    //misc logic
    public AmazonS3 getS3Client(){
        //create a credentials object to identify server for authentification
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID, ACCESS_SEC_KEY);

        //create client connection based on credentials
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();

        return s3client;
    }
}
