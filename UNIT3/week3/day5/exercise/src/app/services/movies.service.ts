import { Injectable } from '@angular/core';
import { Movie } from '../models/movie.interface';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';

@Injectable({
    providedIn: 'root',
})
export class MoviesService {

    apiURL = environment.apiURL;

    constructor(private http: HttpClient) {}

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
}
