import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Event as NavigationEvent, NavigationStart, NavigationEnd } from '@angular/router'
import { Subscription } from 'rxjs';
import { Post } from '../models/post';
import { User } from '../models/user';
import { PostService } from '../services/post.service';
import { UserServicesService } from '../services/user-services.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  private id: number;
  user: User;
  allPosts: Post[];
  private _routerSub = Subscription.EMPTY;

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserServicesService, private postService: PostService) { 
    this._routerSub= this.router.events.subscribe(
    (event: NavigationEvent) => {
      if(event instanceof NavigationEnd) {
          this.id = Number(this.route.snapshot.paramMap.get('id'))
          this.userService.setCurrentProfileView(this.id);
      }
      
    });
  }

  ngOnInit(): void {
    this.getUsersPosts();
  }

  async getUsersPosts(){
    
    this.postService.getAllPosts().subscribe(posts =>{
      
      
      this.allPosts = posts.filter(x => x.userId.userId == this.id);
    }
      
    )
  }

  ngOnDestroy(){
    this._routerSub.unsubscribe();
  }

}
