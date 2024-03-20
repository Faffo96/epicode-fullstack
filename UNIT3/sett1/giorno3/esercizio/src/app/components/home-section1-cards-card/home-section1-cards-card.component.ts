import { Component } from '@angular/core';
import { Article } from 'src/app/models/article.interface';
@Component({
   selector: 'app-home-section1-cards-card',
   templateUrl: './home-section1-cards-card.component.html',
   styleUrls: ['./home-section1-cards-card.component.scss'],
})
export class HomeSection1CardsCardComponent {
   article!: Article[];
   cards: Article[] = [];

   constructor() {
      for (let i = 0; i < 2; i++) {
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
      return Math.floor(Math.random() * 31);
   }
}
