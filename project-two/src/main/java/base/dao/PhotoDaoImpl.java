package base.dao;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import base.model.Photos;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.io.File;

public class PhotoDaoImpl implements PhotoDao {

    //create session factory obj
    private SessionFactory sesFact;

    //the s3 bucket that stores photos
    public static String BUCKET_NAME = "rev-p2-socialmedia-2102";

    //TODO: Not sure about above but static vars below should eventually be replaced/removed
    public static String FILE_PATH = "src/main/resources";

    //CONSTRUCTORS
    public PhotoDaoImpl() {
    }

    public PhotoDaoImpl(SessionFactory sesFact) {
        this.sesFact = sesFact;
    }

    //DAO Methods
    @Override
    public void uploadPhoto(Photos photo, AmazonS3 client) {

        //Logic for storage
        //-----------------
        String filename = photo.getPhotoString();
        String filepath = "src/main/resources/ToUploadFromTemp/"+filename;

        //TODO: file should be pulled from client, not filepath eventually
        try{
            client.putObject(new PutObjectRequest(BUCKET_NAME, filename, new File(filepath)));

            System.out.println("Successfully uploaded photo!");
        }catch (Exception e){
            e.printStackTrace();
        }

        //Database Logic
        //--------------
        sesFact.getCurrentSession().save(photo);
    }

    @Override
    public Photos getPhotobyId(int id, AmazonS3 client) {

        //Database Logic
        //--------------
        Photos photo = sesFact.getCurrentSession()
                .createQuery("from User WHERE photo_id = '" + id + "'", Photos.class)
                .uniqueResult();

        //Logic for storage
        //-----------------
        String photoName = photo.getPhotoString();
        try{
            //get connection to aws s3
            S3Object obj = client.getObject(BUCKET_NAME, photoName);
            S3ObjectInputStream inputStream = obj.getObjectContent();

            //store image at a given path for now
            FileUtils.copyInputStreamToFile(inputStream, new File(FILE_PATH+"/"+photoName));

            System.out.println("Successfully downloaded photo!");
        }catch(Exception e){
            e.printStackTrace();
        }

        return photo;
    }

    @Override
    public void deletePhoto(Photos photo, AmazonS3 client) {

        //logic for storage
        //-----------------
        try{
            client.deleteObject(new DeleteObjectRequest(BUCKET_NAME, photo.getPhotoString()));

            System.out.println("Successfully deleted photo");
        }catch(Exception e){
            e.printStackTrace();
        }

        //logic for database
        //--------------
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();


        session.delete(photo);

        tx.commit();
    }
}
