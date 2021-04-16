import { Component, OnInit } from '@angular/core';
import { UserServicesService } from '../../services/user-services.service';

@Component({
  selector: 'app-email-password',
  templateUrl: './email-password.component.html',
  styleUrls: ['./email-password.component.css']
})
export class EmailPasswordComponent implements OnInit {

  private email: string;

  constructor(private userService: UserServicesService) { }

  ngOnInit(): void {
  }

  get emailField() {
    return this.email
  }

  set emailField(str:string) {
    this.email = str;
  }

  sendEmail() {
    console.log("sending email")
    this.userService.sendPasswordEmail(this.email);
  }

}
