import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Post } from '../models/post';
import { User } from '../models/user';
import { PostService } from '../services/post.service';
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  postSubscription: Subscription;

  post: Post = {
    postId: 0,
    description: '',
    photos: [],
    userId: null,
    users: []
  }

  allPosts: Post[] = [];

  user: User = {userId: 0,
    fname: "",
    lname: '',
    email: '',
    password: '',
    username: '',
    avatar: '',
    posts: []
  }

  constructor(private userService: UserServicesService, private postService: PostService) {

   }

  ngOnInit(): void {
   
    this.currentUser();
    this.allPost();

  }

  ngOnDestroy(): void {
    this.postSubscription.unsubscribe();
  }


  currentUser() {
    console.log("Grabbing current user session")
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
      }
    )
  }

  likePost(){
    this.postService
  }



  async allPost(){
    console.log("Grab All post method")
    this.postSubscription = await this.postService.getAllPosts().subscribe(
      postData => {
        console.log(postData)
        this.allPosts = [...postData]
      }
    )

  
  }



}
