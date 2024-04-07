import { Injectable } from '@angular/core';
import { Movie } from '../models/movie.interface';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { BehaviorSubject, forkJoin, Observable, of } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { UserService } from './user.service';
import { AuthData } from '../models/auth-data.interface';
import { map, catchError } from 'rxjs/operators';

@Injectable({
    providedIn: 'root',
})
export class MoviesService {
    private _favoriteMoviesSubject: BehaviorSubject<Movie[]> = new BehaviorSubject<Movie[]>([]);
    readonly favoriteMovies$ = this._favoriteMoviesSubject.asObservable();
    apiURL = environment.apiURL;
    user!: AuthData | null;
    favoriteIds: number[] = [];

    constructor(private http: HttpClient, private authService: AuthService, private userService: UserService) {
        this.authService.user$.subscribe((user) => {
            this.user = user;
        });
        this.userService.favorites$.subscribe((favorites) => {
            this.favoriteIds = favorites;
        });
    }

    getMovieById(id: number): Observable<Movie | null> {
        const popularMovie$ = this.http.get<Movie[]>(`${this.apiURL}movies-popular`);
        const topRatedMovie$ = this.http.get<Movie[]>(`${this.apiURL}movies-toprated`);
    
        return forkJoin([popularMovie$, topRatedMovie$]).pipe(
            map(([popularMovies, topRatedMovies]: [Movie[], Movie[]]) => {
                // Cerca il film nell'array dei film popolari
                let movie = popularMovies.find(movie => movie.id === id);
    
                // Se il film non è stato trovato nei film popolari, cerca nell'array dei film con valutazione più alta
                if (!movie) {
                    movie = topRatedMovies.find(movie => movie.id === id);
                }
    
                // Restituisci il film trovato (o null se non è stato trovato)
                return movie || null;
            }),
            catchError(() => of(null)) // Gestisce il caso in cui si verifica un errore
        );
    }
    


    getMoviesPopular() {
        return this.http.get<Movie[]>(`${this.apiURL}movies-popular`);
    }

    getMoviePopular(id: number) {
        return this.http.get<Movie>(`${this.apiURL}movies-popular/${id}`);
    }

    getMoviesTopRated() {
        return this.http.get<Movie[]>(`${this.apiURL}movies-toprated`);
    }

    getMovieTopRated(id: number) {
        return this.http.get<Movie>(`${this.apiURL}movies-toprated/${id}`);
    }

    getUserFavoriteMovies(favorites: number[]): void {
        const favoriteMovies: Movie[] = [];

        forkJoin([
            this.getMoviesTopRated(),
            this.getMoviesPopular()
        ]).subscribe(
            ([topRatedMovies, popularMovies]: [Movie[], Movie[]]) => {
                favoriteMovies.push(...topRatedMovies.filter(movie => favorites.includes(movie.id)));
                favoriteMovies.push(...popularMovies.filter(movie => favorites.includes(movie.id)));
                this._favoriteMoviesSubject.next(favoriteMovies);
                console.log(favoriteMovies);
            },
            (error: any) => {
                console.error('Error fetching movies:', error);
            }
        );
    }

    /* updateFavorites(newFavoriteMovies: Movie[]): void {
        this._favoriteMoviesSubject.next(newFavoriteMovies); // Aggiorna il BehaviorSubject con i nuovi film preferiti
    } */
}
