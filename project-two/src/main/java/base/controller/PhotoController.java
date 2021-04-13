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


//    //http://localhost:9005/social/api/uploadPhoto
//    @PostMapping(value="/uploadPhoto")
//    public void createNewPost(@RequestBody Photos photo){
//        ps.uploadPhoto(photo);
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
    public PhotoController(PhotoDaoImpl photoDao){

        this.photoDao = photoDao;
    }

    ////Getters and Setters

    public PhotoDao getUserDao() {
        return photoDao;
    }

    @Autowired
    public void setPhotoDao(PhotoDaoImpl photoDao) {
        this.photoDao = photoDao;
    }

}
