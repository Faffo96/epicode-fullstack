import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/service/post.service';
import { CommentService } from 'src/app/service/comment.service';
import { Post } from 'src/app/models/post.interface';
import { Comment } from 'src/app/models/comment.interface';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  posts: Post[] = []; // Add this line to define 'posts'
  comments: Comment[] = [];

  constructor(private PostsSrv: PostService, private commentSrv: CommentService) {
    this.PostsSrv.posts$.subscribe((data) => {
      this.posts = data;
    });
    this.commentSrv.comments$.subscribe((data) => {
      this.comments = data;
    });

    // Carica i dati all'inizio
    this.PostsSrv.loadPosts();
    this.commentSrv.loadComments();
  }

  ngOnInit(): void {
    
  }
}
