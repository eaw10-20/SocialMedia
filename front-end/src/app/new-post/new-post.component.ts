import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '../models/post';
import { User } from '../models/user';
import { PostService } from '../services/post.service';
import { UserServicesService } from '../services/user-services.service';
import { PhotoServicesService } from '../services/photo-services.service';
import { Photo } from '../models/photo';

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
  //photos: Photo[];
  photo: Photo; 
  selectedFile: File;


  constructor(private postService: PostService, private userService: UserServicesService, 
    private photoService: PhotoServicesService, private router: Router) { }


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
  onFileSelected(event){
    this.photo.imageData = event.target.files[0];

    //add photo to post
    this.post.photos.push(this.photo.imageData);


  }


  sendPost() {
    this.post.userId = this.user;
    console.log(this.post)
    this.postService.createNewPost(this.post);

    //for now uploading a single photo. Change if implementing batch upload later
    if(this.photo) this.photoService.uploadPhoto(this.photo);


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
