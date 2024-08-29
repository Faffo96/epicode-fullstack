import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Like } from '../models/like.interface';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LikeService {
  apiURL = `${environment.apiURL}likes`;

  private _likesSubject = new BehaviorSubject<Like[]>([]);
  likes$ = this._likesSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadLikes();
  }

  private loadLikes() {
    this.http.get<Like[]>(this.apiURL).subscribe((likes) => this._likesSubject.next(likes));
  }

  getLike(id: number): Observable<Like> {
    return this.http.get<Like>(`${this.apiURL}/${id}`);
  }

  createLike(data: Partial<Like>): Observable<Like> {
    console.log('Creating like with data:', data);
    return this.http.post<Like>(this.apiURL, data).pipe(
      tap((response) => {
        console.log('Like created response:', response);
        this.loadLikes();
      }),
      catchError(error => {
        console.error('Error creating like:', error);
        return throwError(error);
      })
    );
  }
  
  
  

  deleteLike(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/${id}`).pipe(
      tap(() => this.loadLikes())
    );
  }
}