import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { MainComponent } from './components/main/main.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { HomeSection1JumbotronComponent } from './components/home-section1-jumbotron/home-section1-jumbotron.component';
import { HomeSection21CardsCardComponent } from './components/home-section2-1-cards-card/home-section2-1-cards-card.component';
import { HomeSection1CardsCardComponent } from './components/home-section1-cards-card/home-section1-cards-card.component';



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
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
