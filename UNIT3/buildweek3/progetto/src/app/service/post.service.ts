import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Post } from '../models/post.interface';
import { environment} from 'src/environments/environment.development';

import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  apiURL = `${environment.apiURL}posts`;

  private _postsSubject = new BehaviorSubject<Post[]>([]);
  posts$ = this._postsSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadPosts();
  }

  loadPosts() {
    this.http.get<Post[]>(this.apiURL).subscribe((posts) => this._postsSubject.next(posts));
  }

  getPost(id: number): Observable<Post> {
    return this.http.get<Post>(`${this.apiURL}/${id}`);
  }

  createPost(data: Partial<Post>): Observable<Post> {
    return this.http.post<Post>(this.apiURL, data).pipe(
      tap(() => this.loadPosts())
    );
  }

  deletePost(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/${id}`).pipe(
      tap(() => this.loadPosts())
    );
  }

  editPost(id: number, data: Partial<Post>): Observable<Post> {
    return this.http.patch<Post>(`${this.apiURL}/${id}`, data).pipe(
      tap(() => this.loadPosts())
    );
  }
}