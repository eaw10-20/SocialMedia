import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { promise } from 'selenium-webdriver';
import { Post } from '../models/post';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserServicesService {



  constructor(private HttpCli: HttpClient, private router: Router) { }

  checkLogin(user: User) {
    const promise = this.HttpCli.post<User>(`http://localhost:9005/social/login/`, user,
    {withCredentials: true}).toPromise()
    console.log("in the check login method")
    // const promise = this.HttpCli.get<User>(`http://localhost:9005/social/api/getUserById/?id=1`,
    // {withCredentials: true}).toPromise()
    promise.then((data) => {
      if(data != null) {
        this.router.navigate (['/main'])
      } else {
        this.router.navigate(['/fail'])
      }

      
    })
  } 

  /*createUser(newUser){
    this.HttpCli.post(`http://localhost:9005/social/api/createUser/?user=${newUser}`,
    {withCredentials: true})
    console.log("over here?")
  }*/

  createUser(user: User) {
    const promise = this.HttpCli.post<User>(`http://localhost:9005/social/api/createUser/`, user,
    {withCredentials: true}).toPromise()
    promise.then((data) => {
      // this.user = this.userConstruct(data);


      
    })
  }




  getUserSession(): Observable<User>{
    return this.HttpCli.get<User>(`http://localhost:9005/social/api/getUser`,
    {withCredentials: true})
  }


  createNewUser(user: User) {
    console.log("in the create new user method service")
    const promise = this.HttpCli.post(`http://localhost:9005/social/api/createUser`, user
    ).toPromise()
    promise.then((data) => {
    console.log("complete??")
  })
  }

  updateNewUser(user: User) {
    const promise = this.HttpCli.post(`http://localhost:9005/social/api/udpateUser`, user
    ).toPromise()
    promise.then((data) => {

        this.router.navigate (['/profile'])
      
    })
  }

  getFriendList(): Observable<User[]>{
    return this.HttpCli.get<User[]>(`http://localhost:9005/social/api/getAllFriends`,
    {withCredentials: true})
  }

    
    
  
}
