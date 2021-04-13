import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { User } from '../models/user';
import { UserServicesService } from '../services/user-services.service';


@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  user: User = {userId: 0,
    fname: "",
    lname: '',
    email: '',
    password: '',
    username: '',
    avatar: '',
    posts: []
  }

  constructor(private userService: UserServicesService) { }

  ngOnInit(): void {
    this.currentUser();
  }

  get emailField () {
    return this.user.email;
  }

  set emailField (str: string) {
    this.user.email = str;
  }

  get passwordField () {
    return this.user.password;
  }

  set passwordField(str: string) {
    this.user.password = str;
  }
  get usernameField () {
    return this.user.username;
  }

  set usernameField (str: string) {
    this.user.username = str;
  }

  get fnameField () {
    return this.user.fname;
  }

  set fnameField(str: string) {
    this.user.fname = str;
  }
  
  get lnameField () {
    return this.user.lname;
  }

  set lnameField(str: string) {
    this.user.lname = str;
  }


  currentUser() {
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
        console.log(this.user)
      }
    )
  }



   onSubmit() {
    
    console.log(this.user)
    this.userService.updateNewUser(this.user)
    event.preventDefault();

  }

}
