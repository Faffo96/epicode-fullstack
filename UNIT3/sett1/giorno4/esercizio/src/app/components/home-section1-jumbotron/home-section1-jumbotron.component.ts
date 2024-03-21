import { Component } from '@angular/core';
import { Article } from 'src/app/models/article.interface';

@Component({
  selector: 'app-home-section1-jumbotron',
  templateUrl: './home-section1-jumbotron.component.html',
  styleUrls: ['./home-section1-jumbotron.component.scss']
})
export class HomeSection1JumbotronComponent {
  title!: Article;

  constructor() {
    this.getTitle().then((title) => {
      this.title = title;
    })
  }

  async getTitle() {
    let response = await fetch('../../assets/db.json');
    let data = await response.json();
    let randomNumber = this.randomNumberTo30();
    return data[randomNumber].title;
  }

  randomNumberTo30(): number {
    return Math.floor(Math.random() * 31);
}
}
