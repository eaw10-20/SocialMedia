import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserServicesService } from '../services/user-services.service';
import { Pipe, PipeTransform } from '@angular/core';

//@Pipe({ name: 'appFilter' })

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit  {

  constructor(private userService: UserServicesService) { }

  searchText = '';
  userFriends = []  
  usernames = [];
  filteredItems = [];
  items;

  ngOnInit(): void {
    this.loadFriendList();
  }

  getUsernames(){
    console.log(this.userFriends )

 for(let i=0;i<this.userFriends.length;i++){
   this.usernames.push(this.userFriends[i].username)
 }

  }

  loadFriendList() {
    this.userService.getFriendList().subscribe(
      data=> {
        this.userFriends = data;
        console.log(this.userFriends)
        this.getUsernames();
      }
    )
  }
  
  assignCopy(){
    this.filteredItems = Object.assign([], this.items);
 }
 filterItem(value){
   console.log(value)
    if(value == ''){
      console.log('gere')
        this.filteredItems = null;
    }else{ // when nothing has typed
    this.filteredItems = this.usernames.filter(word => word.indexOf(value) > -1);
    console.log(this.userFriends)
    console.log(this. usernames)

    console.log(this.filteredItems)
    }
 }
 



}
