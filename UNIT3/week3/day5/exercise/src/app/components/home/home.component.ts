import { Component, OnInit } from '@angular/core';
import { MoviesService } from 'src/app/services/movies.service';
import { Movie } from 'src/app/models/movie.interface';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  topRatedMovies: Movie[] = [];
  popularMovies: Movie[] = [];
  randomTopRatedMovies: Movie[] = [];
  randomPopularMovies: Movie[] = [];
  https = environment.https;

  constructor(private moviesService: MoviesService) {}

  ngOnInit(): void {
    this.fetchMoviesTopRated();
    this.fetchMoviesPopular();
  }

  generateRandomNumber = (min: number, max: number): number => {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  };

  fetchMoviesTopRated(): void {
    this.moviesService.getMoviesTopRated().subscribe(
      (movies: Movie[]) => {
        this.topRatedMovies = movies;
        this.randomTopRatedMovies =  this.randomizeMoviesArray(this.topRatedMovies);
      },
      (error: any) => {
        console.error('Error fetching movies:', error);
      }
    );
  }

  fetchMoviesPopular(): void {
    this.moviesService.getMoviesPopular().subscribe(
      (movies: Movie[]) => {
        this.popularMovies = movies;
        this.randomPopularMovies = this.randomizeMoviesArray(this.popularMovies)
      },
      (error: any) => {
        console.error('Error fetching movies:', error);
      }
    );
  }

  randomizeMoviesArray(array: Movie[]): Movie[] {
    const shuffled = array.slice(); // Clona l'array
    let currentIndex = shuffled.length;
  
    // Finché ci sono elementi rimasti da mescolare
    while (currentIndex !== 0) {
      // Scegli un elemento rimasto a caso
      const randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex--;
  
      // Scambia l'elemento corrente con l'elemento scelto casualmente
      [shuffled[currentIndex], shuffled[randomIndex]] = [shuffled[randomIndex], shuffled[currentIndex]];
    }
  
    return shuffled;
  }
  

  radomizePopularMovies() {
    
  }
}
