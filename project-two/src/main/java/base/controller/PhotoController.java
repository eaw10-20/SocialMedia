package base.controller;

import base.dao.PhotoDao;
import base.dao.PhotoDaoImpl;
import base.dao.UserDao;
import base.dao.UserDaoImpl;
import base.model.Photos;
import base.model.Post;
import base.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PhotoController {
    private PhotoDaoImpl photoDao;
    private PhotoService photoService;


    //http://localhost:9005/social/api/uploadPhoto
    @PostMapping(value="/uploadPhoto")
    @CrossOrigin(allowCredentials = "true")
    public Photos uploadPhoto(@RequestBody Photos photo){
        String photoname = photo.getImageData().getName();
        String a = photo.getImageData().toString();
        System.out.println("The photo name is "+photoname);
        System.out.println("The photo tostring is "+a);
        //isolate extension
        int dotIndex = photo.getPhotoString().lastIndexOf('.');
        String imgExt = photoname.substring(dotIndex);
        //rename photo so that it includes extension
        photo.setPhotoString(photoname+imgExt);
        //pring out new photoname to check if valid
        System.out.println(photo.getPhotoString());
        photoService.uploadPhoto(photo);

        //clear out photo data prior to return
        photo.clearData();
        //return so front end can handle
        return photo;
    }

    //http://localhost:9005/social/api/downloadPhoto
//    @PostMapping(value="/downloadPhoto", params={"id"}, produces="application/json)
//    public void getPhotoById(){
//
//    }


    @GetMapping(value= "/test")
    public void addAvatarPhoto() {
        System.out.println("in the get test method");
        photoDao.uploadAvatarPhoto(1,"IMG_2603.jpg");}

    ////Constructors

    public PhotoController(){

    }

    ///Autowired constructor to inject the repo directly

    @Autowired
    public PhotoController(PhotoDaoImpl photoDao, PhotoService photoService){

        this.photoDao = photoDao;

        this.photoService = photoService;
    }


    ////Getters and Setters

    public PhotoDao getUserDao() {
        return photoDao;
    }

    @Autowired
    public void setPhotoDao(PhotoDaoImpl photoDao) {
        this.photoDao = photoDao;
    }

    public PhotoService getPhotoService() {
        return photoService;
    }

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }
}
