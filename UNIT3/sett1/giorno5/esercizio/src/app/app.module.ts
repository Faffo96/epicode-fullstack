import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Route, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Import FormsModule


import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { MainComponent } from './components/main/main.component';
import { FooterComponent } from './components/footer/footer.component';
import { PorscheComponent } from './components/porsche/porsche.component';
import { FerrariComponent } from './components/ferrari/ferrari.component';
import { LamborghiniComponent } from './components/lamborghini/lamborghini.component';
import { HomeComponent } from './components/home/home.component';
import { DetailsComponent } from './components/details/details.component';
import { AppRoutingModule } from './modules/app-routing.module';

const routes: Route[] = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'ferrari',
    component: FerrariComponent
  },
  {
    path: 'lamborghini',
    component: LamborghiniComponent
  },
  {
    path: 'porsche',
    component: PorscheComponent
  },
  {
    path: 'details/:model',
    component: DetailsComponent,
  },
  {
    path: '**',
    redirectTo:''
  }
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    FooterComponent,
    PorscheComponent,
    FerrariComponent,
    LamborghiniComponent,
    HomeComponent,
    DetailsComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes),
    AppRoutingModule,
   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
