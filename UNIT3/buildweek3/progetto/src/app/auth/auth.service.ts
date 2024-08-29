import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Register } from '../models/register.interface';
import { environment } from 'src/environments/environment.development';
import { BehaviorSubject, throwError } from 'rxjs';
import { catchError, delay, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthData } from '../models/auth-data.interface';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    apiURL = environment.apiURL;
    jwtHelper = new JwtHelperService();

    private authSub = new BehaviorSubject<AuthData | null>(null);
    user$ = this.authSub.asObservable();
    timeOut: any;

    constructor(private http: HttpClient, private router: Router) { }

    login(data: { email: string; password: string }) {
        return this.http.post<{ accessToken: string; user: any }>(`${this.apiURL}login`, data).pipe(
            tap((response) => {
                console.log('Login response:', response);
                this.authSub.next(response);
                localStorage.setItem('user', JSON.stringify(response.user));
                localStorage.setItem('accessToken', response.accessToken); // Memorizza il token
            }),
            catchError((error) => this.handleAuthErrors(error, 'login'))
        );
    }

    signup(data: Register) {
        return this.http.post(`${this.apiURL}register`, data).pipe(
            catchError((error) => this.handleAuthErrors(error, 'signup'))
        );
    }

    private handleAuthErrors(err: any, action: string) {
        console.log(err.error);
        switch (err.error) {
            case 'Incorrect password':
                return throwError('Password errata');
            case 'Cannot find user':
                return throwError('Utente non trovato');
            case 'Password is too short':
                return throwError('La password è troppo breve');
            case 'Email format is invalid':
                return throwError('Inserire una email valida');
            default:
                if (action === 'signup' && err.error === 'Email already exists') {
                    return throwError('Utente già presente');
                }
                return throwError('Errore nella chiamata');
        }
    }

    logout() {
        this.authSub.next(null);
        localStorage.removeItem('user');
        localStorage.removeItem('accessToken'); // Rimuove anche il token
        this.router.navigate(['/login']);
    }

    restore() {
        const userJson = localStorage.getItem('user');
        const accessToken = localStorage.getItem('accessToken');
        
        if (userJson && accessToken) {
            try {
                const user = JSON.parse(userJson);
                this.authSub.next({ accessToken, user });
            } catch (e) {
                console.error('Error parsing user data from localStorage:', e);
                this.authSub.next(null);
            }
        } else {
            this.authSub.next(null);
        }
    }
    
}
