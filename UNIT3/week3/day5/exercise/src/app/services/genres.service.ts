import { Injectable } from '@angular/core';
import { Favorite } from '../models/favorite.interface';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';

@Injectable({
    providedIn: 'root',
})
export class GenresService {

    apiURL = environment.apiURL;

    constructor(private http: HttpClient) {}

    getGenres() {
        return this.http.get<Favorite[]>(`${this.apiURL}genres`);
    }

    getGenre(id: number) {
        return this.http.get<Favorite>(`${this.apiURL}genres/${id}`);
    }
}
