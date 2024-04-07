import { Component, OnInit, Input } from '@angular/core';
import { MoviesService } from 'src/app/services/movies.service';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/auth/auth.service';
import { Movie } from 'src/app/models/movie.interface';
import { environment } from 'src/environments/environment.development';
import { AuthData } from 'src/app/models/auth-data.interface';
import { User } from 'src/app/models/user.interface';


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
  favoriteMovies: Movie[] = [];

  constructor(private moviesService: MoviesService, private authService: AuthService, private userService: UserService) {
   
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
  }

  ngOnInit(): void {
   /*  this.moviesService.favoriteMovies$.subscribe((favorites) => {
      this.favoriteMovies = favorites;
    });
     */
    // Chiamare fetchUserFavoritesById quando atterra sulla rotta
    if (this.user) {
      this.fetchUserFavoritesById(this.user.user.id);
      console.log(this.favoriteMovies)
    }
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
  
fetchUserFavoritesById(userId: number): void {
  this.userService.getUser(userId).subscribe(
    (user: User) => {
      console.log('Utente recuperato con successo:', user);
      if (user && user.favorites) {
        return this.favoriteMovies = user.favorites; // Assegna i film preferiti dell'utente a this.favoriteMovies
      } else {
        return this.favoriteMovies = []; // Assegna un array vuoto se l'utente non ha preferiti
      }
    },
    (error) => {
      console.error('Errore durante il recupero dell\'utente:', error);
      // Qui puoi gestire il caso in cui si verifichi un errore durante il recupero dell'utente
      // Ad esempio, puoi mostrare un messaggio di errore all'utente nell'interfaccia utente
    }
  );
}


isFavorite(movieId: number): boolean {
  return this.favoriteMovies.some(movie => movie.id === movieId);
}

}
