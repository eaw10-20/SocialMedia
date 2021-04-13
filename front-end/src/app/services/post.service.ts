import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private HttpCli: HttpClient) { }


  getAllPosts(): Observable<Post[]> {
    return this.HttpCli.get<Post[]>(`http://localhost:9005/social/api/getAllPosts/`,
    {withCredentials: true})
  }

  

  getUserPosts(userId): Observable<Post[]> {
   
    return this.HttpCli.get<Post[]>(`http://localhost:9005/social/api/getPostsByUserId/?id=${userId}`,
    {withCredentials: true})
  }

  createNewPost(post: Post){
    console.log("in the create new post method service")
    const promise = this.HttpCli.post(`http://localhost:9005/social/api/post/create`, post
    , {withCredentials: true}).toPromise()
  }
}
