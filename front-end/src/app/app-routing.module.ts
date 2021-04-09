import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewUserComponent } from './new-user/new-user.component';
import { NewPostComponent } from './new-post/new-post.component';
import { LoginpageComponent} from './loginpage/loginpage.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  { path: 'new-user', component: NewUserComponent },
  { path: 'main', component: MainpageComponent},
  { path: 'profile', component: ProfileComponent},
  { path: '**', component: LoginpageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
