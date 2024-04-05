import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
    user = localStorage.getItem('user');
    logged!: boolean;

    constructor(private authSrv: AuthService, private router: Router) {}

    ngOnInit(): void {
        this.logged = this.authSrv.isLoggedIn;
        console.log(this.logged);
    }

    checkUser() { // Verifica se esiste il localStorage user; se non esiste non restituisce nulla
        return localStorage.getItem('user');
    }

    onLogout(): void {
        localStorage.removeItem('user');
        this.user = null;
        this.logged = this.authSrv.logout();
        if (!this.logged) {
            this.router.navigate(['/']);
        }
    }
}
