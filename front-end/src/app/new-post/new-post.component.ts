import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '../models/post';
import { PostServicesService } from '../services/post-services.service';

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

  descriptionField: string;

  ngOnInit(): void {
    this.currentUser();
  }

  get inputField(){
    return this.descriptionField;
  }

  set inputField(temp: string){
    this.descriptionField = temp;
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
