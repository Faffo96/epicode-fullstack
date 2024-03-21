import { Component } from '@angular/core';
import { Article } from 'src/app/models/article.interface';

@Component({
  selector: 'app-home-section2-1-cards-card',
  templateUrl: './home-section2-1-cards-card.component.html',
  styleUrls: ['./home-section2-1-cards-card.component.scss']
})
export class HomeSection21CardsCardComponent {
  article!: Article[];
  cards: Article[] = [];
  randomNumbers: number[] = [];

  constructor() {
     for (let i = 0; i < 4; i++) {
        this.getArticle().then((article) => {
           this.article = article;
           this.cards.push(article);
        });
     }
  }

  async getArticle() {
     let response = await fetch('../../assets/db.json');
     let data = await response.json();
     let randomNumber = this.randomNumberTo30();
     return data[randomNumber];
  } 

  randomNumberTo30(): number {
     let randomNumber = Math.floor(Math.random() * 31);
     if (this.randomNumbers.includes(randomNumber)) {
      randomNumber = Math.floor(Math.random() * 31);
  }
  this.randomNumbers.push(randomNumber);
  return randomNumber;

  }
}
