import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-left-container',
  templateUrl: './left-container.component.html',
  styleUrls: ['./left-container.component.css']
})
export class LeftContainerComponent implements OnInit {

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
  }

  currentUser() {
    console.log("Grabbing current user session")
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
        console.log(data);
        console.log(this.user);
      }
    )
  }
  
  //

  // userConstruct(data): User {
  //   return this.user = {
  //     userId : parseInt(data.userId),
  //     fname: data.fname,
  //     lname: data.lname,
  //     email: data.email,
  //     password: data.password,
  //     username: data.username,
  //     photo: data.avatar,
  //     posts: data.posts
  //   }
  // }

}
