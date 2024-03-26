import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Article } from 'src/app/models/article.interface';

@Component({
  selector: 'app-edit-page',
  templateUrl: './edit-page.component.html',
  styleUrls: ['./edit-page.component.scss']
})
export class EditPageComponent implements OnInit {
  posts: Article[] = [];
  articleId!: number;
  article!: Article;
  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    // Ottieni l'ID dall'URL
    this.route.paramMap.subscribe(params => {
      this.articleId = +params.get('id')!;
      console.log(this.articleId);
      
      // Ora puoi usare this.articleId per ottenere l'articolo con quell'ID
      this.getArticleById(this.articleId).then(article => {
        this.article = article;
      });
    });
}

  async getArticleById(id: number) {
    let response = await fetch('https://66019bf987c91a11641b3f3e.mockapi.io/postApp/posts/');
    let data = await response.json();
    let filteredArticle = data.find((article: Article) => article._id === id);
    console.log(filteredArticle); // Puoi fare qualcosa con l'articolo filtrato qui
    return filteredArticle;
  }  

  async updateArticle(id: number, updatedTitle: string, updatedBody: string) {
    const url = `https://66019bf987c91a11641b3f3e.mockapi.io/postApp/posts/${id}`;

    // Creare una copia dell'articolo e modificarla
    const updatedArticle = { ...this.article };
    updatedArticle.title = updatedTitle;
    updatedArticle.body = updatedBody;

    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedArticle), // Invia la copia modificata per l'aggiornamento
    });

    if (response.ok) {
        const responseData = await response.json();
        console.log('Articolo aggiornato:', responseData);
        return responseData;
    } else {
        console.error('Errore durante l\'aggiornamento dell\'articolo:', response.statusText);
        return null;
    }
}

onSave(event: Event) {
  event.preventDefault(); // Evita il comportamento predefinito del form
  const title = (document.getElementById('articleTitle') as HTMLInputElement).value;
  const body = (document.getElementById('articleBody') as HTMLInputElement).value;
  alert("The article was modified");
  this.updateArticle(this.articleId, title, body);
}


}

