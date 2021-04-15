import { AfterViewInit, Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  user: User;
  
  constructor(private userService: UserServicesService) { }

  ngOnInit(): void {
    this.currentUser();
    
  }

  currentUser() {
    console.log("Grabbing current user session form nav bar")
    this.userService.getUserSession().subscribe(
      data=> {
        this.user = data;
      }
    )

    // this.user = this.userService.getCurrentUserSession()
  }
}
