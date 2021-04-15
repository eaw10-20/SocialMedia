import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserServicesService } from '../services/user-services.service';
import { Pipe, PipeTransform } from '@angular/core';
import { templateJitUrl } from '@angular/compiler';

//@Pipe({ name: 'appFilter' })

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit  {

  constructor(private userService: UserServicesService) { }
  user: User;   


  searchText = '';
  userFriends = []  
  filteredItems = [];


  ngOnInit(): void {
    this.loadFriendList();
    this.currentUser();


  }

  currentUser() {
    console.log("Grabbing current user session form nav bar")
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
      }
    )
  }



  loadFriendList() {
    this.userService.getFriendList().subscribe(
      data=> {
        this.userFriends = data;
        console.log(this.userFriends)
      }
    )
  }


 filterItem(value){
   console.log(value)
    if(value == ''){
        this.filteredItems = null;
    }else{ // when nothing has typed
    this.filteredItems = this.userFriends.filter(word => word.username.indexOf(value) > -1);

    console.log(this.filteredItems)
    }
 }
 



}
