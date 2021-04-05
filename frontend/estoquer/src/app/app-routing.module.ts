import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InitialComponent } from './views/estoquer/initial/initial.component';
import { LoginComponent } from './views/estoquer/login/login.component';

const routes: Routes = [
  {
    path: '',
    component: InitialComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
