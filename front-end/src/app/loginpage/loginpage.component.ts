import { Component, OnInit } from '@angular/core';
import { user } from '../models/user'

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {

  currentUser: user = {userId: 0,
    fname: "",
    lname: '',
    email: '',
    password: '',
    username: '',
    photo: '',
    posts: []
  }

  constructor() { }

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
    console.log(this.currentUser.email + " " + this.currentUser.password)
  }

}
