import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostServicesService {

  constructor(private HttpCli: HttpClient, private router: Router) { }


  createNewPost(post: Post){
    this.HttpCli.post<Post>(`http://localhost:9005/social/api/createPost`,
    post, {withCredentials: true})
  }

}
