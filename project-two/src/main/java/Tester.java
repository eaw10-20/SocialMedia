import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.FileUtils;
import util.HibernateUtil;

import java.io.File;

public class Tester {

    public static String FILE_PATH = "src/main/resources";
    public static String BUCKET_NAME = "rev-p2-socialmedia-2102";
    public static String PHOTO_NAME = "IMG_2603.jpg";
    //get the below from aws IAM
    //https://youtu.be/Cnf0wXowWDM?t=428
    public static String ACCESS_KEY_ID = "ACCESS_KEY_ID";
    public static String ACCESS_SEC_KEY = "ACCESS_SEC_KEY";

    public static void main(String[] args) {
        //create a credentials object to identify server for authentification
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID, ACCESS_SEC_KEY);

        //create client connection based on credentials
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();

        putPhoto(s3client);
        getPhoto(s3client);
    }

    //to upload a photo
    public static void putPhoto(AmazonS3 s3client){
        String filename = "pic.jpeg"; //In project every file name should be unique
        String filepath = "A/File/Path/toupload/from";

        s3client.putObject(new PutObjectRequest(BUCKET_NAME, filename, new File(filepath))
                .withCannedAcl(CannedAccessControlList.PublicRead));

    }

    //to download a photo
    public static void getPhoto(AmazonS3 s3client){
        
        try{
            //get connection to aws s3
            S3Object obj = s3client.getObject(BUCKET_NAME, PHOTO_NAME);
            S3ObjectInputStream inputStream = obj.getObjectContent();

            //store image at a given path for now
            FileUtils.copyInputStreamToFile(inputStream, new File(FILE_PATH));


        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
