import { Component, OnInit, Input } from '@angular/core';
import { Post } from 'src/app/models/post.interface';
import { PostService } from 'src/app/service/post.service';
import { UsersService } from 'src/app/service/users.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { AuthData } from 'src/app/models/auth-data.interface';
import { LikeService } from 'src/app/service/like.service';
import { Like } from 'src/app/models/like.interface';
import { Comment } from 'src/app/models/comment.interface';
import { CommentService } from 'src/app/service/comment.service';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user.interface';

@Component({
  selector: 'app-postcard',
  templateUrl: './postcard.component.html',
  styleUrls: ['./postcard.component.scss']
})
export class PostcardComponent {
  @Input() post!: Post;
  postComments: Comment[] = [];
  user!: AuthData | null;
  show = true;
  likeCount = 0;
  userLikes: Like[] = [];
  likes: Like[] = [];
  selectedUser!: User;
  isTextAreaFocused = false;
  userComment!: User;

  constructor(
    private postSrv: PostService,
    private router: Router,
    private authsrv: AuthService,
    private likeSrv: LikeService,
    private userSrv: UsersService,
    private commentSrv: CommentService
  ) {
    this.authsrv.user$.subscribe((data) => {
      this.user = data;
      console.log('User data from auth service:', this.user); // Debug
    });
    
  
    this.likeSrv.likes$.subscribe((data) => {
      this.likes = data;
      this.updateUserLikes();
      this.getLikeCount();
    });
  
    this.getComments();
    this.loadSelectedUser();

  }

  private updateUserLikes() {
    console.log('User:', this.user); // Verifica il valore di this.user
    const activeUserId = this.user?.user?.id;
    if (activeUserId) {
      this.userLikes = this.likes.filter(fav => fav.userId === activeUserId);
    } else {
      console.warn('User ID is not available or user is not logged in');
      this.userLikes = [];
    }
  }
  

  setSelectedUser(name: string, email: string, userId: number) {
    this.userSrv.setSelectedUser({ name, email, id: userId });
  }

  getComments() {
    this.commentSrv.comments$.subscribe((comments) => {
      this.postComments = comments.filter(comment => comment.postId === this.post.id);
    });
  }

  deletePost(id: number) {
    this.postSrv.deletePost(id).subscribe(() => {
      this.show = false;
    });
  }

  addLike(likedPostId: number) {
    console.log('Add Like called for post:', likedPostId);
    console.log('Active User Parsed:', this.user);
  
    // Verifica l'esistenza di user e id
    if (this.user?.user.id) {
      console.log("User ID from activeUserParsed:", this.user?.user.id);
  
      const like: Partial<Like> = {
        postId: likedPostId,
        userId: this.user?.user.id
      };
  
      this.likeSrv.createLike(like).subscribe({
        next: (response) => {
          console.log('Like added successfully', response);
          // Aggiorna il conteggio dei like dopo aver aggiunto il like
          this.getLikeCount();
        },
        error: (error) => {
          console.error('Error adding like', error);
        }
      });
    } else {
      console.warn('User not logged in or user ID is missing');
    }
  }
  
  
  
  
  

  removeLike(postId: number) {
    const likeToRemove = this.userLikes.find(fav => fav.postId === postId);
    if (likeToRemove) {
      this.likeSrv.deleteLike(likeToRemove.id).subscribe();
    }
  }

  getLikeCount() {
    this.likeCount = this.likes.filter(like => like.postId === this.post.id).length;
  }

  editPost(form: NgForm) {
    this.postSrv.editPost(this.post.id, form.value).subscribe(() => {
      location.reload();
    });
  }

  isFav(id: number): boolean {
    return this.likes.some(fav => fav.postId === id && fav.userId === this.user?.user.id);
  }

  loadSelectedUser() {
    this.userSrv.getUsers().subscribe(users => {
      this.selectedUser = users.find(user => user.id === this.post.userId) || {} as User;
    });
  }

  newComment(form: NgForm) {
    const activeUserId = this.user?.user.id;
    if (activeUserId) {
      const comment: Comment = {
        body: form.value.body,
        userId: activeUserId,
        postId: this.post.id,
        id: 0 // Assegna un valore di id valido se necessario
      };
      this.commentSrv.createComment(comment).subscribe();
    }
  }

  onTextAreaFocus() {
    this.isTextAreaFocused = true;
  }

  onTextAreaBlur() {
    this.isTextAreaFocused = false;
  }
}