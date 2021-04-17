package base.controller;

import base.dao.PhotoDao;
import base.dao.PhotoDaoImpl;
import base.model.Photos;
import base.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PhotoController {
    private PhotoDaoImpl photoDao;
    private PhotoService photoService;



    /**
     * http://localhost:9005/social/api/uploadPhoto
     * HTTP request to upload a photo to s3 storage and then database
     * @param file
     * @param name
     * @param modelMap
     * @return
     */
    @PostMapping(value="/uploadPhoto")
    @CrossOrigin(allowCredentials = "true")
    public Photos uploadPhoto(@RequestParam("imageData") MultipartFile file,
                              @RequestParam("photoString") String name, ModelMap modelMap){
        System.out.println("In upload photo method");

        modelMap.addAttribute("imageData", file);

        //get extension
        System.out.println("The original file name is "+file.getOriginalFilename());
        int dotIndex = file.getOriginalFilename().lastIndexOf('.');
        String imgExt = file.getOriginalFilename().substring(dotIndex);
        //check for file type here?
        //name += imgExt;

        Photos photo = new Photos();

        File store = new File("src/main/resources/tmpfiles/"+name+".tmp");

        try {
            file.transferTo(store);
        }catch (Exception e){
            System.out.println("Failed to transfer file");
        }
        photo.setImageData(store);

        photo.setPhotoString(name);

        //print out new photoname to check if valid
        photoService.uploadPhoto(photo);

        //clear out photo data and delete store prior to return
        photo.clearData();
        store.delete();

        //return so front end can handle
        return photo;
    }


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
