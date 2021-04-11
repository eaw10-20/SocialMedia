import { Component, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { PostService } from '../services/post.service';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  post: Post = {
    postId: 0,
    description: '',
    photos: [],
    userId: 0
  }

  photo: File;

  constructor(private postService: PostService) { }


  ngOnInit(): void {
  }

  get descriptionField () {
    return this.post.description;
  }

  set descriptionField (str: string) {
    this.post.description = str;
  }

  onFileSelect(files:FileList) {
    for (let i = 0; i < files.length; i++) {
      this.post.photos.push(files.item(i))
      
    }
  }

  sendPost() {
    this.postService.newPost(this.post);
  }

}
