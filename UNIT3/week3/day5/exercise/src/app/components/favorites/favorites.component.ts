import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.interface';
import { Movie } from 'src/app/models/movie.interface';
import { UserService } from 'src/app/services/user.service';
import { MoviesService } from 'src/app/services/movies.service';
import { AuthData } from 'src/app/models/auth-data.interface';
import { AuthService } from 'src/app/auth/auth.service';
import { environment } from 'src/environments/environment.development';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.scss']
})
export class FavoritesComponent implements OnInit {
  favoriteIds: number[] = [];
  user!: AuthData | null;
  favoriteMovies: Movie[] = [];
  https = environment.https;

  constructor(private moviesService: MoviesService, private authService: AuthService, private userService: UserService) {
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
    this.userService.favorites$.subscribe((favoriteIds) => {
      this.favoriteIds = favoriteIds;
    });
  }

  ngOnInit(): void {
    this.moviesService.favoriteMovies$.subscribe((favorites) => {
      this.favoriteMovies = favorites;
    });
    
    // Chiamare fetchUserFavoritesById quando atterra sulla rotta
    if (this.user) {
      this.fetchUserFavoritesById(this.user.user.id);
      console.log(this.favoriteMovies)
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
  
 
  
  
}
