import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Il component importa HttpClient per usare i metodi HTTP, in quanto il proprio module ha importato HttpClientService
import { User } from 'src/app/interfaces/user';

@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.scss'],
})
export class UserListComponent {
    users: User[] = [];

    constructor(http: HttpClient) {
        // Dando il tipo alla get (in questo caso User[]) i dati arrivano nel formato desiderato
        http.get<User[]>('https://jsonplaceholder.typicode.com/users').subscribe((data) => {
            this.users = data;
            console.log(this.users);
        });
    }
}
