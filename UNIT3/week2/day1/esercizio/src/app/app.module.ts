import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Route, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { MainComponent } from './components/main/main.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { HomeSection1JumbotronComponent } from './components/home-section1-jumbotron/home-section1-jumbotron.component';
import { HomeSection21CardsCardComponent } from './components/home-section2-1-cards-card/home-section2-1-cards-card.component';
import { HomeSection1CardsCardComponent } from './components/home-section1-cards-card/home-section1-cards-card.component';
import { ActivePostsComponent } from './components/active-posts/active-posts.component';
import { UnactivePostsComponent } from './components/unactive-posts/unactive-posts.component';
import { DetailsComponent } from './components/details/details.component';
import { EditPageComponent } from './components/edit-page/edit-page.component';

const routes: Route[] = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'activePosts',
    component: ActivePostsComponent
  },
  {
    path: 'unactivePosts',
    component: UnactivePostsComponent
  },
  {
    path: 'details',
    component: DetailsComponent
  },
  {
    path: 'edit/:id',
    component: EditPageComponent
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
    HomeComponent,
    HomeSection1JumbotronComponent,
    HomeSection21CardsCardComponent,
    HomeSection1CardsCardComponent,
    ActivePostsComponent,
    UnactivePostsComponent,
    DetailsComponent,
    EditPageComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
