import { Component, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { PostServicesService } from '../services/post-services.service';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {


  descriptionField: string;


  get inputField(){
    return this.descriptionField;
  }

  set inputField(temp: string){
    this.descriptionField = temp;
  }

  constructor(private postService: PostServicesService) { }

  ngOnInit(): void {
  }

  createNewPost(post: Post){
    this.postService.createNewPost(post)
  }

}
