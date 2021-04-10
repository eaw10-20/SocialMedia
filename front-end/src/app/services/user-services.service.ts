import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { promise } from 'selenium-webdriver';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserServicesService {
  // user: User = {userId: 0,
  //   fname: "",
  //   lname: '',
  //   email: '',
  //   password: '',
  //   username: '',
  //   photo: '',
  //   posts: []
  // }

  
  // userConstruct(userJson): User {
  //   return this.user = {
  //     userId : parseInt(userJson.userId),
  //     fname: userJson.fname,
  //     lname: userJson.lname,
  //     email: userJson.email,
  //     password: userJson.password,
  //     username: userJson.username,
  //     photo: userJson.avatar,
  //     posts: userJson.posts
  //   }
  // }

  constructor(private HttpCli: HttpClient, private router: Router) { }

  checkLogin(userId: number) {
    const promise = this.HttpCli.get<User>(`http://localhost:9005/social/api/getUserById/?id=${userId}`, 
    {withCredentials: true}).toPromise()
    promise.then((data) => {
      // this.user = this.userConstruct(data);
      if(data != null) {
        this.router.navigate (['/main'])
      } else {
        this.router.navigate(['/fail'])
      }

      
    })
  } 



  getUserSession(): Observable<HttpResponse<User>>{
    return this.HttpCli.get<HttpResponse<User>>(`http://localhost:9005/social/api/getUser`,
    {withCredentials: true})
  }
}
