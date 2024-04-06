import { Component, OnInit } from '@angular/core';
import { AuthData } from 'src/app/models/auth-data.interface';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    user!: AuthData | null;

    constructor(private authSrv: AuthService) {}

    ngOnInit(): void {
        this.authSrv.user$.subscribe((user) => {
            this.user = user;
        });
    }

    logout() {
        this.authSrv.logout();
    }

}