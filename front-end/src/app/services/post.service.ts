import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private HttpCli: HttpClient) { }

  post: Post = {
    postId: 0,
    description: '',
    photos: [],
    userId: null,
  };
  
  getAllPosts(): Observable<Post[]> {

    return this.HttpCli.get<Post[]>(`http://localhost:9005/social/api/getAllPosts/`,
    {withCredentials: true});

  }

  
  updateData(post: Post){

  }

  getUserPosts(userId): Observable<Post[]> {
   
    const post= this.HttpCli.get<Post[]>(`http://localhost:9005/social/api/getPostsByUserId/?id=${userId}`,
    {withCredentials: true})
    
    return post;
  }

  createNewPost(post: Post){
    console.log("in the create new post method service")
    this.HttpCli.post(`http://localhost:9005/social/api/post/create`, post
    , {withCredentials: true}).toPromise()
    
  }
}
