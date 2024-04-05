import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
    loginForm!: NgForm;

    username!: string;
    password!: string;

    logged!: boolean;

    constructor(private router: Router, private authSrv: AuthService) {}

    onSubmit(form: NgForm) {
        console.log(form.value);
        localStorage.setItem('user', JSON.stringify(form.value));
        this.logged = this.authSrv.login();
        if (this.logged) {
            this.router.navigate(['/']);
        }
    }
}
