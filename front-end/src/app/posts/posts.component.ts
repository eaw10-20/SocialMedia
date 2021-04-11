import { Component, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { User } from '../models/user';
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  post: Post = {
    postId: 0,
    description: '',
    photos: [],
    userId: 0
  }

  allPosts: Post[];

  user: User = {userId: 0,
    fname: "",
    lname: '',
    email: '',
    password: '',
    username: '',
    photo: '',
    posts: []
  }

  

  constructor(private userService: UserServicesService) { }

  ngOnInit(): void {
    this.currentUser();
    this.allPost();

  }

  currentUser() {
    console.log("Grabbing current user session in post component")
    const promise = this.userService.getUserSession().subscribe(
      userData=> {
        this.userConstruct(userData);
      }
    )
  }

  userConstruct(data): User {
    return this.user = {
      userId : parseInt(data.userId),
      fname: data.fname,
      lname: data.lname,
      email: data.email,
      password: data.password,
      username: data.username,
      photo: data.avatar,
      posts: data.posts
    }
  }

  allPost(){
    console.log("Grab All post method")
    this.userService.getAllPosts().subscribe(
      postData => {
        this.allPosts = postData;
      }
    )
  }

}
