import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
    constructor(private authSrv: AuthService, private router:Router) { }
    errore = '';

    login(form: NgForm) {
        console.log('Login form data:', form.value);
        this.authSrv.login(form.value).subscribe(
            (data) => {
                console.log('Login response:', data); // Verifica che la risposta contenga il token
                this.router.navigate(['/']);
            },
            (error) => {
                console.error('Login error:', error); // Stampa l'errore nella console
                this.errore = error;
            }
        );
    }
}
