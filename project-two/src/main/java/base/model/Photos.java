package base.model;

import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

//Photo model
@Entity
@Table(name = "Photos")
public class Photos {

    //Auto generated serial number and primary key of Hibernate_Photos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private int photoId;

    //Column photo string
    @Column(name = "photobyte")
    private String photoString;

    //Many photos can be attached to one post
    //Connected to Post in the @JoinColumn portion with a new column named post_id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="post_id")
    private Post myPost;

    //Stores file data
    private File image;


    public Photos() {
    }

    public Photos(int photoId, String photoString, Post myPost) {
        this.photoId = photoId;
        this.photoString = photoString;
        this.myPost = myPost;
    }

    public Photos(String photoString, Post myPost) {
        this.photoString = photoString;
        this.myPost = myPost;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhotoString() {
        return photoString;
    }

    public void setPhotoString(String photoString) {
        this.photoString = photoString;
    }

    public Post getMyPost() {
        return myPost;
    }

    public void setMyPost(Post myPost) {
        this.myPost = myPost;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        //check to make sure the image type is ok

        //first create a set
        Set<String> ext = new HashSet<>();
        ext.add("jpg");
        ext.add("jpeg");
        ext.add("png");

        //get complete image name
        String filename = image.getName();

        //isolate extension
        int dotIndex = filename.lastIndexOf('.');
        String imgExt = image.getName().substring(dotIndex+1);
        if(ext.contains(imgExt)){
            this.image = image;
        }else{
            System.out.println(imgExt+ "is not a valid picture file extension.");
        }
    }

    @Override
    public String toString() {
        return "Photos{" +
                "photoId=" + photoId +
                ", photoString='" + photoString + '\'' +
//                ", myPost=" + myPost +
                '}';
    }
}
