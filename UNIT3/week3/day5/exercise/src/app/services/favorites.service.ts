import { Injectable } from '@angular/core';
import { Favorite } from '../models/favorite.interface';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';

@Injectable({
    providedIn: 'root',
})
export class favoritesService {

    apiURL = environment.apiURL;

    constructor(private http: HttpClient) {}

    getFavorites() {
        return this.http.get<Favorite[]>(`${this.apiURL}favorites`);
    }

    getFavorite(id: number) {
        return this.http.get<Favorite>(`${this.apiURL}favorites/${id}`);
    }

    newFavorite(data: Favorite) {
        return this.http.post<Favorite>(`${this.apiURL}favorites`, data);
    }

    deleteFavorite(id: number) {
        return this.http.delete(`${this.apiURL}favorites/${id}`);
    }
}
