import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewUserComponent } from './new-user/new-user.component';
import { LoginpageComponent} from './loginpage/loginpage.component';
import { EmailPasswordComponent} from './loginpage/email-password/email-password.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ProfileComponent } from './profile/profile.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { ChatComponent } from './chat/chat.component';


const routes: Routes = [
  { path: 'update-user', component: UpdateUserComponent },
  {path: 'forgotPassword', component: EmailPasswordComponent},
  { path: 'chat', component: ChatComponent },
  { path: 'new-user', component: NewUserComponent },
  { path: 'main', component: MainpageComponent},
  { path: 'profile/:username/:id', component: ProfileComponent},
  { path: '**', component: LoginpageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
