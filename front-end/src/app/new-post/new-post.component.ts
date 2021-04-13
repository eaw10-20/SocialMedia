import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '../models/post';
import { User } from '../models/user';
import { Photo } from '../models/photo';
import { PostService } from '../services/post.service';
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  post: Post = {
    postId: 0,
    description: '',
    photos: [],
    userId: null
  }

  user: User;
  photos: Photo[];
  selectedFile: File;


  constructor(private postService: PostService, private userService: UserServicesService,private router: Router) { }


  ngOnInit(): void {
    this.currentUser();
  }

  get descriptionField(){
    return this.post.description;
  }

  set descriptionField(str: string){
    this.post.description = str;
  }

  currentUser() {
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
      }
    )
  }

  //Should store the uploaded image
  // onFileSelected(event){
  //   let photo: Photo;
  //   photo.imageData = event.target.files[0];

  //   //add photo to post
  //   this.post.photos.push(photo.imageData);


  // }


  sendPost() {
    this.post.userId = this.user;
    console.log(this.post)
    this.postService.createNewPost(this.post);
    //for now making a separate call for photos. Should probably streamline this later
    for(let pic of this.photos){
      // this.photoService.uploadPhoto(pic);
    }

    this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
      this.router.navigate(['main']);
  }); 

  }

  handleFileInput(files: FileList) {
    for(let i =0; i<files.item.length; i++) {
        this.post.photos.push(files.item(i))
    }
  }

}
