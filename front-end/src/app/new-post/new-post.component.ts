import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '../models/post';
import { User } from '../models/user';
import { PostServicesService } from '../services/post-services.service';
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

  user;

  constructor(private postService: PostServicesService, private userService: UserServicesService,private router: Router) { }

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

  currentUser() {
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
      }
    )
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
