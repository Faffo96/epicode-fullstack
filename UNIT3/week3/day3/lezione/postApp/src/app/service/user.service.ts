import { Injectable } from '@angular/core';
import { User } from '../models/user.interface';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';

@Injectable({
    providedIn: 'root',
})
export class UserService {
    apiURL = environment.apiURL;

    constructor(private http: HttpClient) {}

    getUsers() {
        return this.http.get<User[]>(`${this.apiURL}utenti`);
    }

    getUser(id: number) {
        return this.http.get<User>(`${this.apiURL}utenti/${id}`);
    }
}
