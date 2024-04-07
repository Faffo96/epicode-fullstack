import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { AuthData } from 'src/app/models/auth-data.interface';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
    user!: AuthData | null;

    constructor(private authService: AuthService, private router: Router) {
        this.authService.user$.subscribe((user) => {
            this.user = user;
        });
    }

    login(form: NgForm) {
        try {
            this.authService.login(form.value).subscribe();
            setTimeout(() => {
                this.router.navigate(['/']);
            }, 100);
        } catch (error) {
            console.error(error);
        }
    }
}
