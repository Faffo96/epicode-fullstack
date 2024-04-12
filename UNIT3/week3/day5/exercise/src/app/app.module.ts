import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Route, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { MainComponent } from './components/main/main.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { AccountComponent } from './components/account/account.component';
import { AccountSettingsComponent } from './components/account-settings/account-settings.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterUserComponent } from './auth/register-user/register-user.component';
import { FilmsCarouselComponent } from './components/films-carousel/films-carousel.component';
import { TokenInterceptor } from './auth/token.interceptor';
import { FilmsCarouselItemComponent } from './components/films-carousel-item/films-carousel-item.component';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { MovieDetailsComponent } from './components/movie-details/movie-details.component';
import { EllipsisPipe } from './pipes/ellipsis.pipe';
import { AuthGuard } from './auth/auth.guard';
import { appConfig } from './app.config';
import { AngularFireModule } from '@angular/fire/compat';
import { AngularFireAuthModule } from '@angular/fire/compat/auth';
import { provideFirebaseApp, initializeApp } from '@angular/fire/app';


const routes: Route[] = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'account',
    component: AccountComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'accountSettings',
    component: AccountSettingsComponent,
    canActivate: [AuthGuard]
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
    path: 'favorites',
    component: FavoritesComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'movieDetails/:id',
    component: MovieDetailsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    redirectTo: ''
  }
]

const firebaseConfig = {
  apiKey: "AIzaSyAXhRwC6dmETqeXVMmcL35GKgkZeeiuUUc",
  authDomain: "netflix-clone-ca824.firebaseapp.com",
  projectId: "netflix-clone-ca824",
  storageBucket: "netflix-clone-ca824.appspot.com",
  messagingSenderId: "919652111338",
  appId: "1:919652111338:web:3874ea9651f648ef3b9893",
  measurementId: "G-HF1BKB3C1V"
};

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    FooterComponent,
    HomeComponent,
    AccountComponent,
    AccountSettingsComponent,
    LoginComponent,
    RegisterUserComponent,
    FilmsCarouselComponent,
    FilmsCarouselItemComponent,
    FavoritesComponent,
    MovieDetailsComponent,
    EllipsisPipe,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot([]), // Assicurati di importare RouterModule.forRoot([])
    AngularFireModule.initializeApp(firebaseConfig), 
    AngularFireAuthModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    {
      provide: appConfig,
      useValue: appConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
