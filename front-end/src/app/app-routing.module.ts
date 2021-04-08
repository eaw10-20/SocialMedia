import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewUserComponent } from './new-user/new-user.component';
import { NewPostComponent } from './new-post/new-post.component';
import { LoginpageComponent} from './loginpage/loginpage.component';

const routes: Routes = [
  { path: 'new-user', component: NewUserComponent },
  { path: '**', component: LoginpageComponent, }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
