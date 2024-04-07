import { Component, OnInit, Input } from '@angular/core';
import { MoviesService } from 'src/app/services/movies.service';
import { Movie } from 'src/app/models/movie.interface';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-films-carousel',
  templateUrl: './films-carousel.component.html',
  styleUrls: ['./films-carousel.component.scss']
})
export class FilmsCarouselComponent {
  @Input() movies: Movie[] = []; 
  @Input() topRatedMovies: Movie[] = [];
  @Input() popularMovies: Movie[] = [];
  https = environment.https;

  constructor(private moviesService: MoviesService) {}
}

