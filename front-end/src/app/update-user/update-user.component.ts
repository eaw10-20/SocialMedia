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
    photo: '',
    posts: []
  }

  constructor(private userService: UserServicesService) { }

  ngOnInit(): void {
  }

  updateForm = new FormGroup({
    email: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
  });



   onSubmit() {
    
    this.user = {
      userId: 0,
    fname: this.updateForm.value.firstName,
    lname: this.updateForm.value.firstName,
    email: this.updateForm.value.email,
    password: this.updateForm.value.password,
    username: this.updateForm.value.username,
    photo: '',
    posts: []
    }
    console.log(this.user)
    this.userService.createNewUser(this.user)
    event.preventDefault();

  }

}
