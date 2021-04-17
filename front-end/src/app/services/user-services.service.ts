import { HttpClient, HttpResponse } from '@angular/common/http';
import { ThisReceiver, ThrowStmt } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, isObservable, Observable, of } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserServicesService {
  private user: User = {userId: 0,
    fname: "",
    lname: '',
    email: '',
    password: '',
    username: '',
    avatar: '',
    userDescription: '',
    posts: []
  }
  
  private userFriends : User[];
  private currentUser : User;
  public currentUserProfileView: BehaviorSubject<User> = new BehaviorSubject<User>(this.user);

  constructor(private HttpCli: HttpClient, private router: Router) { }

  async checkLogin(user: User) {
    try {    
    const promise = await this.HttpCli.post<User>(`http://localhost:9005/social/login/`, user,
    {withCredentials: true}).toPromise()
    if(promise != null) {
      this.router.navigate (['/main'])
      console.log("hey")
      return true
  } else {
    this.router.navigate(['/fail'])   
  }
  console.log("hey")
    }
    catch(err) {
      console.log("bad")
      return false

    }
//return false
  } 


  createUser(user: User) {
    const promise = this.HttpCli.post<User>(`http://localhost:9005/social/api/createUser/`, user,
    {withCredentials: true}).toPromise()
    promise.then((data) => {
      this.router.navigate(['/fail'])
    })
  }

/**
 * Grabs a list of all users
 * @returns Friend list
 */
  getFriendsList(): Observable<User[]> {
      this.getFriendList().subscribe(
        data=> {
          this.userFriends= data;
        }
      )
        // let index = this.userFriends.indexOf(this.currentUser)
        // this.userFriends.splice(index,1)

      return of(this.userFriends);
  }

  /**
   * Emits an observable user 
   * that is based on the url param http://localhost:4200/profile/:username/:userId
   * @param id 
   * @returns 
   */
   setCurrentProfileView(id:number){
    console.log("in the set currentprofile cire method in user service")
    if(id == this.currentUser.userId) {
      console.log(this.currentUser)
      this.currentUserProfileView.next(this.currentUser)
    } else {
      for(let friend of this.userFriends) {
        if(id == friend.userId) {
          this.currentUserProfileView.next(friend)
        }
      }
    }
    this.currentUserProfileView.subscribe(data => {console.log(data)})
    // console.log(this.currentUserProfileView)
    // return this.CurrentProfileView;
  }



  setCurrentUserSession(user: User) {
    this.currentUser = user;
  }

  getCurrentUserSession():User {
    return this.currentUser
  }


  getUserSession(): Observable<User>{
    return this.HttpCli.get<User>(`http://localhost:9005/social/api/getUser`,
    {withCredentials: true})
  }


  createNewUser(user: User) {
    console.log("in the create new user method service")
    const promise = this.HttpCli.post(`http://localhost:9005/social/api/createUser`, user
    ).toPromise()
  }

  updateUser(user: User) {
    const promise = this.HttpCli.post(`http://localhost:9005/social/api/updateUser`, user
    ).toPromise()
    promise.then((data) => {

        this.router.navigate (['/profile'])
      
    })
  }

  getFriendList(): Observable<User[]>{
    return this.HttpCli.get<User[]>(`http://localhost:9005/social/api/getAllFriends`,
    {withCredentials: true})
  }

  sendPasswordEmail(email:string) {
    console.log(email)
    this.HttpCli.get(`http://localhost:9005/social/api/emailPassword/?email=${email}`)
  }
  
}
