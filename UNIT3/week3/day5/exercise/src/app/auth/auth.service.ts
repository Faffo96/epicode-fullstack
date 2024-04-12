import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Register } from '../models/register.interface';
import { environment } from 'src/environments/environment.development';
import { BehaviorSubject, Observable, throwError, from } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthData } from '../models/auth-data.interface';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Auth, createUserWithEmailAndPassword, updateProfile} from '@angular/fire/auth';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    firebaseAuth: Auth; 

    apiURL = environment.apiURL;
    jwtHelper = new JwtHelperService();

    // elementi per gestire la procedura di login
    private authSub = new BehaviorSubject<AuthData | null>(null);
    user$ = this.authSub.asObservable();
    timeOut: any;

    constructor(private http: HttpClient, private router: Router, auth: Auth) {
        this.firebaseAuth = auth;
    }

    login(data: { email: string; password: string }) {
        return this.http.post<AuthData>(`${this.apiURL}login`, data).pipe(
            tap((data) => {
                console.log('Auth data: ', data);
            }),
            tap((data) => {
                this.authSub.next(data);
                localStorage.setItem('user', JSON.stringify(data));
                this.autoLogout(data);
            }),
            catchError(this.errors)
        );
    }

    register(email:string, username: string, password: string): Observable<void> {
        const promise = createUserWithEmailAndPassword(this.firebaseAuth,
            email,
            password,
            ).then(response => updateProfile(response.user, {displayName: username}))

            return from(promise)
    }

    signup(data: Register) {
        return this.http
            .post(`${this.apiURL}register`, data)
            .pipe(catchError(this.errors));
    }

    logout() {
        this.authSub.next(null);
        localStorage.removeItem('user');
        this.router.navigate(['/login']);
    }

    restore() {
        const userJson = localStorage.getItem('user');
        if (!userJson) {
            return;
        }
        const user: AuthData = JSON.parse(userJson);
        this.authSub.next(user);
        this.autoLogout(user);
    }

    autoLogout(user: AuthData) {
        const dateExpiration = this.jwtHelper.getTokenExpirationDate(user.accessToken) as Date;
        const millisecondsExp = dateExpiration.getTime() - new Date().getTime();
        this.timeOut = setTimeout(() => {
            this.logout();
        }, millisecondsExp);
    }

    private errors(err: any) {
        console.log(err.error);
        switch (err.error) {
            case 'Email already exists':
                return throwError('utente già presente');
                break;

            case 'Incorrect password':
                return throwError('password errata');
                break;

            case 'Cannot find user':
                return throwError('Utente non trovato');
                break;

            default:
                return throwError('Errore nella chiamata');
                break;
        }
    }
}
