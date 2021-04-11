package base.controller;

import base.model.Photos;
import base.model.Post;
import base.service.PhotoService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PhotoController {
    private PhotoService ps;


    //http://localhost:9005/social/api/uploadPhoto
    @PostMapping(value="/uploadPhoto")
    public void createNewPost(@RequestBody Photos photo){
        ps.uploadPhoto(photo);
    }
}
