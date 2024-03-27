import { Component } from '@angular/core';
import { Article } from 'src/app/models/article.interface';

@Component({
  selector: 'app-unactive-posts',
  templateUrl: './unactive-posts.component.html',
  styleUrls: ['./unactive-posts.component.scss']
})
export class UnactivePostsComponent {
  articles!: Article[];
  cards: Article[] = [];
  randomNumbers: number[] = [];

  constructor() {
  
        this.getArticles().then((article) => {
          this.articles = article;
          this.cards = this.articles.filter((article: Article) => article.active === false);
           
        });
     
  }

  async getArticles() {
     let response = await fetch('https://66019bf987c91a11641b3f3e.mockapi.io/postApp/posts/');
     let data = await response.json();
     return data;
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
