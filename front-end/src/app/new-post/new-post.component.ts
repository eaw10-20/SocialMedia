import { Component, OnInit } from '@angular/core';
import { Post } from '../models/post';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {
  // export interface Post{
  //   postId: number,
  //   description: string,
  //   photos: [],
  //   userId: number
  // }

  constructor() { }

  ngOnInit(): void {
  }

  onFileSelected(event){

  }
}
