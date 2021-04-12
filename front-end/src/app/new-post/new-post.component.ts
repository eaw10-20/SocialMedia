import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '../models/post';
import { User } from '../models/user';
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


  constructor(private postService: PostService, private userService: UserServicesService,private router: Router) { }


  ngOnInit(): void {
    this.currentUser();
  }

  get descriptionField () {
    return this.post.description;
  }

  set descriptionField (str: string) {
    this.post.description = str;
  }

  currentUser() {
    console.log("Grabbing current user session")
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
      }
    )
  }

  onFileSelect(files:FileList) {
    for (let i = 0; i < files.length; i++) {
      this.post.photos.push(files.item(i))
      
    }
  }

  sendPost() {
    this.post.userId = this.user;
    console.log(this.post)
    this.postService.createNewPost(this.post);

    this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
      this.router.navigate(['main']);
  }); 

  }

}
