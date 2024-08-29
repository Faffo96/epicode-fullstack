import { Injectable } from '@angular/core';
import { Comment } from '../models/comment.interface';
import { HttpClient } from '@angular/common/http';
import { environment} from 'src/environments/environment.development';
import { Post } from '../models/post.interface';
import { BehaviorSubject, Observable, tap } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CommentService {
  apiURL = `${environment.apiURL}comments`;

  private _commentsSubject = new BehaviorSubject<Comment[]>([]);
  comments$ = this._commentsSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadComments();
  }

  loadComments() {
    this.http.get<Comment[]>(this.apiURL).subscribe((comments) => this._commentsSubject.next(comments));
  }

  getComment(id: number): Observable<Comment> {
    return this.http.get<Comment>(`${this.apiURL}/${id}`);
  }

  deleteComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/${id}`).pipe(
      tap(() => this.loadComments())
    );
  }

  editComment(id: number, data: Partial<Comment>): Observable<Comment> {
    return this.http.patch<Comment>(`${this.apiURL}/${id}`, data).pipe(
      tap(() => this.loadComments())
    );
  }

  createComment(data: Partial<Comment>): Observable<Comment> {
    return this.http.post<Comment>(this.apiURL, data).pipe(
      tap(() => this.loadComments())
    );
  }
}