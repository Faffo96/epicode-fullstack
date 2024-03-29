import { Injectable, OnInit } from '@angular/core';
import { User } from '../models/user.interface';
import { Task } from '../models/task.interface';
import { Observable } from 'rxjs';
import { throwError, BehaviorSubject } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private apiURL = 'https://66069c8dbe53febb857e4948.mockapi.io/toDo/users';
  private users: User[] = [];
  usersSub = new BehaviorSubject<User[]>([]);

  constructor(private http: HttpClient) { }
  getUsers() {
    return this.http.get<any[]>(this.apiURL).pipe(
      map(users => {
        this.users = users;
        this.emitUsers();
        return users;
      }),
      catchError(error => {
        console.error('Error fetching users:', error);
        return throwError(error);
      })
    );
  }

  getUsersObservable(): Observable<User[]> {
    return this.usersSub;
  }
  
  private emitUsers() {
    this.usersSub.next(this.users);
  }

}
