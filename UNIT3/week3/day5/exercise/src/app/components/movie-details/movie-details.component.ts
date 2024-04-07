import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/models/movie.interface';
import { MoviesService } from 'src/app/services/movies.service';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.scss']
})
export class MovieDetailsComponent implements OnInit {
  urlMovieId: string | null = null;
  movie: Movie | null = null;
  https = environment.https;

  constructor(private route: ActivatedRoute, private moviesService: MoviesService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      // Ottieni il valore del parametro 'id' dall'URL
      this.urlMovieId = params.get('id');
      if (this.urlMovieId) {
        // Carica il film in base all'ID dall'URL
        this.loadMovieById(parseInt(this.urlMovieId, 10));
      }
    });
  }

  loadMovieById(id: number): void {
    this.moviesService.getMovieById(id).subscribe(
      (movie: Movie | null) => { // Modifica qui
        if (movie) { // Assicurati che il film non sia null prima di assegnarlo
          console.log('Film caricato con successo:', movie);
          this.movie = movie; // Salva il film nell'oggetto locale del componente
        } else {
          console.error('Film non trovato');
          // Gestisci il caso in cui il film non viene trovato
        }
      },
      (error) => {
        console.error('Errore durante il recupero del film:', error);
        // Gestisci eventuali errori durante il recupero del film
      }
    );
  }
  
}
