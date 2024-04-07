import { Injectable } from '@angular/core';
import { User } from '../models/user.interface';
import { Movie } from '../models/movie.interface';
import { AuthService } from '../auth/auth.service';
import { AuthData } from '../models/auth-data.interface';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { BehaviorSubject, switchMap, tap, catchError, throwError, of } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class UserService {
  private _favoritesSubject: BehaviorSubject<number[]> = new BehaviorSubject<number[]>([]);
  readonly favorites$ = this._favoritesSubject.asObservable();
  favoriteIds: number [] = [];

  user!: AuthData | null;
  apiURL = environment.apiURL;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.authService.user$.subscribe((user) => {
      this.user = user;
      if (user) {
      
      }
    });
  }

  getUsers() {
    return this.http.get<User[]>(`${this.apiURL}users`);
  }

  getUser(id: number) {
    return this.http.get<User>(`${this.apiURL}users/${id}`);
  }

  updateUser(id: number, data: Partial<User>) {
    return this.http.patch<User>(`${this.apiURL}users/${id}`, data);
  }

  deleteUser(id: User) {
    return this.http.delete(`${this.apiURL}users/${id}`);
  }

  getFavorites(userId: number) {
    return this.getUser(userId).pipe(
      switchMap((user: User) => {
        if (user) {
          // Se l'utente esiste, ritorna l'array dei preferiti
          return of(user.favorites);
        } else {
          // Se l'utente non esiste, ritorna un array vuoto
          return of([]);
        }
      }),
      catchError((error) => {
        console.error('Errore durante il recupero dei preferiti dell\'utente:', error);
        // Ritorna un observable con un valore di fallback o un observable vuoto
        return of([]);
      })
    );
  }
  

  newFavorite(userId: number, movie: Movie) {
    return this.getUser(userId).pipe(
      switchMap((user: User) => {
        if (!user) {
          console.error('Utente non trovato');
          return throwError('Utente non trovato');
        }
        
        const updatedUser = { ...user };
        updatedUser.favorites.push(movie);

        return this.http.patch(`${this.apiURL}users/${userId}`, { favorites: updatedUser.favorites }).pipe(
          tap(() => {
            console.log('Aggiunto film ai preferiti dell\'utente');
            this.updateFavorites(updatedUser.favorites.map(favMovie => favMovie.id));
          }),
          catchError((error) => {
            console.error('Errore durante il salvataggio dei preferiti dell\'utente:', error);
            return throwError(error);
          })
        );
      }),
      catchError((error) => {
        console.error('Errore durante il recupero dell\'utente:', error);
        return of(null);
      })
    ).subscribe(
      () => {
        console.log('Operazione newFavorite completata con successo');
      },
      (error) => {
        console.error('Errore durante l\'operazione newFavorite:', error);
      }
    );
}


  deleteFavorite(userId: number, movieId: number) {
    return this.getUser(userId).pipe(
      switchMap((user: User) => {
        // Clona l'oggetto utente per evitare di modificarlo direttamente
        const updatedUser = { ...user };

        // Rimuovi il movieId dall'array dei preferiti dell'utente clonato
        updatedUser.favorites = updatedUser.favorites.filter(favorite => favorite.id !== movieId);

        // Ora invia l'utente aggiornato al backend e ritorna l'observable
        return this.http.patch(`${this.apiURL}users/${userId}`, { favorites: updatedUser.favorites }).pipe(
          tap(() => {
            // Operazione di side-effect se necessaria
            console.log('Film rimosso dai preferiti dell\'utente');
          }),
          // Gestire eventuali errori durante la chiamata PATCH
          catchError((error) => {
            console.error('Errore durante la rimozione del film dai preferiti dell\'utente:', error);
            // Ritorna un observable di errore per continuare la catena di gestione degli errori
            return throwError(error);
          })
        );
      }),
      // Gestire eventuali errori durante il recupero dell'utente
      catchError((error) => {
        console.error('Errore durante il recupero dell\'utente:', error);
        // Ritorna un observable con un valore di fallback o un observable vuoto
        return of(null);
      })
    ).subscribe(
      () => {
        console.log('Operazione deleteFavorite completata con successo');
      },
      (error) => {
        console.error('Errore durante l\'operazione deleteFavorite:', error);
      }
    );
  }

  updateFavorites(newFavorites: number[]): void {
    this.favoriteIds = newFavorites; // Aggiorna l'array esistente
    console.log(newFavorites)
    this._favoritesSubject.next(newFavorites); // Aggiorna il BehaviorSubject
  }
  
}
