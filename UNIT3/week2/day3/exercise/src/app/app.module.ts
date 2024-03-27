import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Route, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { CartComponent } from './components/cart/cart.component';
import { FavouritesComponent } from './components/favourites/favourites.component';
import { HeaderComponent } from './components/header/header.component';


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
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
