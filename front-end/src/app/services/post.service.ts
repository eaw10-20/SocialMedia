import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private HttpCli: HttpClient) { }


  getAllPosts(): Observable<HttpResponse<Post[]>> {
    return this.HttpCli.get<HttpResponse<Post[]>>(`http://localhost:9005/social/api/getAllPosts/`,
    {withCredentials: true})
  }

  getUserPosts(userId): Observable<HttpResponse<Post[]>> {
    return this.HttpCli.get<HttpResponse<Post[]>>(`http://localhost:9005/social/api/getPostsByUserId/?id=${userId}`,
    {withCredentials: true})
  }

  createNewPost(post):
}
