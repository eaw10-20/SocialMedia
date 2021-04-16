import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user'
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {

  currentUser: User = {userId: 0,
    fname: "",
    lname: '',
    email: '',
    password: '',
    username: '',
    avatar: '',
    userDescription:'',
    posts: []
  }

  constructor(private router: Router, private userService: UserServicesService) { }

  ngOnInit(): void {
    
  }


  get emailField () {
    return this.currentUser.email;
  }

  set emailField (str: string) {
    this.currentUser.email = str;
  }

  get passwordField () {
    return this.currentUser.password;
  }

  set passwordField(str: string) {
    this.currentUser.password = str;
  }


   loginButton () {
    console.log("clicked the login button");
    let id: number=1;
    let json: string;
    console.log("in the async login method");
    this.userService.checkLogin(this.currentUser);

  };

}
