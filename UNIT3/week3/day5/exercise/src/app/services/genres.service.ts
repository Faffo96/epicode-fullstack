import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { Genre } from '../models/genre.interface';

@Injectable({
    providedIn: 'root',
})
export class GenresService {

    apiURL = environment.apiURL;

    constructor(private http: HttpClient) {}

    getGenres() {
        return this.http.get<Genre[]>(`${this.apiURL}genres`);
    }

    getGenre(id: number) {
        return this.http.get<Genre>(`${this.apiURL}genres/${id}`);
    }
}
