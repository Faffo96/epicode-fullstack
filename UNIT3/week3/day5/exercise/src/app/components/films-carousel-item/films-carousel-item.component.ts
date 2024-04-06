import { Component, OnInit, Input } from '@angular/core';
import { MoviesService } from 'src/app/services/movies.service';
import { Movie } from 'src/app/models/movie.interface';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-films-carousel-item',
  templateUrl: './films-carousel-item.component.html',
  styleUrls: ['./films-carousel-item.component.scss']
})
export class FilmsCarouselItemComponent {
  @Input() movies: Movie[] = []; 
  topRatedMovies: Movie[] = [];
  popularMovies: Movie[] = [];
  https = environment.https;

  constructor(private moviesService: MoviesService) {}

  ngOnInit(): void {
    this.fetchMoviesTopRated();
    this.fetchMoviesPopular();
  }

  fetchMoviesTopRated(): void {
    this.moviesService.getMoviesTopRated().subscribe(
      (movies: Movie[]) => {
        this.topRatedMovies = movies;
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
      },
      (error: any) => {
        console.error('Error fetching movies:', error);
      }
    );
  }
}