import { Component, OnInit, Input } from '@angular/core';
import { MoviesService } from 'src/app/services/movies.service';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/auth/auth.service';
import { Movie } from 'src/app/models/movie.interface';
import { environment } from 'src/environments/environment.development';
import { AuthData } from 'src/app/models/auth-data.interface';


@Component({
  selector: 'app-films-carousel-item',
  templateUrl: './films-carousel-item.component.html',
  styleUrls: ['./films-carousel-item.component.scss']
})
export class FilmsCarouselItemComponent {
  @Input() movies: Movie[] = []; 
  @Input() topRatedMovies: Movie[] = [];
  @Input() popularMovies: Movie[] = [];
  https = environment.https;
  isHeartFilled: boolean = false;
  user!: AuthData | null;
  favoriteIds: number [] = [];

  constructor(private moviesService: MoviesService, private authService: AuthService, private userService: UserService) {
   
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
  }

toggleFavorite(event: MouseEvent, userId: number, movie: Movie): void {
  const target = event.target as HTMLElement;
  if (target.classList.contains('bi-heart')) {
    target.classList.remove('bi-heart');
    target.classList.add('bi-heart-fill');
    this.userService.newFavorite(userId, movie);
  } else if (target.classList.contains('bi-heart-fill')) {
    target.classList.remove('bi-heart-fill');
    target.classList.add('bi-heart');
    this.userService.deleteFavorite(userId, movie.id);
  }
}


}
