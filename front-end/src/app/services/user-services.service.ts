import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserServicesService {

  constructor(private HttpCli: HttpClient) { }

  firstRequest(userId: number): Observable<User> {
    console.log(userId)
    return this.HttpCli.get<User>(`http://localhost:9005/social/api/getUserById/?id=${userId}`)
  } 
}
