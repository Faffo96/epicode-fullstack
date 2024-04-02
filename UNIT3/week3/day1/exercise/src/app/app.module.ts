import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Route, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { CartComponent } from './components/cart/cart.component';
import { FavouritesComponent } from './components/favourites/favourites.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';


const routes: Route[] = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'favourites',
    component: FavouritesComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registerUser',
    component: RegisterUserComponent
  },
  {
    path: '**',
    redirectTo:''
  }
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CartComponent,
    FavouritesComponent,
    HeaderComponent,
    LoginComponent,
    RegisterUserComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
