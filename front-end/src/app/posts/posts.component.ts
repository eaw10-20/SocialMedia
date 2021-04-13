import { Component, OnInit } from '@angular/core';
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

  post: Post = {
    postId: 0,
    description: '',
    photos: [],
    userId: 0
  }

<<<<<<< HEAD
  allPosts= [];


=======
  allPosts: Post[];
>>>>>>> f5c2e445b966745fbcd8c3af717fb9352d4056c1

  user: User = {userId: 0,
    fname: "",
    lname: '',
    email: '',
    password: '',
    username: '',
    photo: '',
    posts: []
  }

  constructor(private userService: UserServicesService) {

<<<<<<< HEAD
   }
=======
  constructor(private userService: UserServicesService, private postService: PostService) { }
>>>>>>> f5c2e445b966745fbcd8c3af717fb9352d4056c1

  ngOnInit(): void {
    //this.add();

    //let allPosts = [{postId:1, description:"hey", photos:[], userId:1},{postId:2, description:"hey", photos:[], userId:2}];

    //this.currentUser();
    this.allPost();



  }

  add(){
    let pops = [{postId:1, description:"hey", photos:[], userId:1},{postId:2, description:"2", photos:[], userId:2}]
  }

  currentUser() {
    console.log("Grabbing current user session")
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;

      }
    )
  }



  allPost(){
    console.log("Grab All post method")
<<<<<<< HEAD

    this.userService.getAllPosts().subscribe (
      postData => {
        this.setValues(postData);
=======
    this.postService.getAllPosts().subscribe(
      postData => {
        this.allPosts = postData;
        console.log(this.allPosts)
>>>>>>> f5c2e445b966745fbcd8c3af717fb9352d4056c1
      }
    )
  }

  setValues(posts){
    console.log(posts)
    for(let i=0; i<posts.length;i++){
      this.allPosts.push(posts[i])
    }
console.log(this.allPosts)
  }

}
