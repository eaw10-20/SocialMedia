import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.FileUtils;
import util.HibernateUtil;

import java.io.File;
import java.util.Map;

public class Tester {

    public static Map<String, String> env = System.getenv();
    public static String FILE_PATH = "src/main/resources";
    public static String BUCKET_NAME = "rev-p2-socialmedia-2102";
    public static String PHOTO_NAME = "IMG_2603.jpg";
    public static String ACCESS_KEY_ID = System.getenv("S3_ACCESS_KEY");
    public static String ACCESS_SEC_KEY = System.getenv("S3_SEC_KEY");

    public static void main(String[] args) {
        System.out.println("The access key is: "+ ACCESS_KEY_ID);
        System.out.println("The secret key is: "+ ACCESS_SEC_KEY);
        //create a credentials object to identify server for authentification
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID, ACCESS_SEC_KEY);

        //create client connection based on credentials
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();

        //putPhoto(s3client);
        //getPhoto(s3client);
        //deletePhoto(s3client);
    }

    //to upload a photo
    public static void putPhoto(AmazonS3 s3client){
        String filename = "MVCdesignpattern.jpg"; //In project every file name should be unique
        String filepath = "src/main/resources/ToUploadFromTemp/"+filename;

        try{
            s3client.putObject(new PutObjectRequest(BUCKET_NAME, filename, new File(filepath)));

            System.out.println("Successfully uploaded photo!");
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //to download a photo
    public static void getPhoto(AmazonS3 s3client){
        
        try{
            //get connection to aws s3
            S3Object obj = s3client.getObject(BUCKET_NAME, PHOTO_NAME);
            S3ObjectInputStream inputStream = obj.getObjectContent();

            //store image at a given path for now
            FileUtils.copyInputStreamToFile(inputStream, new File(FILE_PATH+"/"+PHOTO_NAME));

            System.out.println("Successfully downloaded photo!");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void deletePhoto(AmazonS3 s3client){
        String filename = "MVCdesignpattern.jpg"; //In project every file name should be unique

        try{
            s3client.deleteObject(new DeleteObjectRequest(BUCKET_NAME, filename));

            System.out.println("Successfully deleted photo");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
