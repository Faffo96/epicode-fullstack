import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root',
})
export class AuthService {

    isLoggedIn!: boolean;

    constructor(private router: Router) {}

    login() {
        return this.isLoggedIn = true;
    }

    logout() {
        return this.isLoggedIn = false;
    }
}
