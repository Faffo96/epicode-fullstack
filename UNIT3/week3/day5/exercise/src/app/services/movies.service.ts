import { Injectable } from '@angular/core';
import { Movie } from '../models/movie.interface';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { BehaviorSubject, forkJoin } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { UserService } from './user.service';
import { AuthData } from '../models/auth-data.interface';

@Injectable({
    providedIn: 'root',
})
export class MoviesService {
    private _favoriteMoviesSubject: BehaviorSubject<Movie[]> = new BehaviorSubject<Movie[]>([]);
    readonly favoriteMovies$ = this._favoriteMoviesSubject.asObservable();
    apiURL = environment.apiURL;
    user!: AuthData | null;
    favoriteIds: number [] = [];

    constructor(private http: HttpClient, private authService: AuthService, private userService: UserService) {
        this.authService.user$.subscribe((user) => {
            this.user = user;
        });
        this.userService.favorites$.subscribe((favorites) => {
            this.favoriteIds = favorites;
        });

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

    getUserFavoriteMovies(favorites: Movie[]): void {
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
