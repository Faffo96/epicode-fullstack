import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/service/post.service';
import { Post } from 'src/app/models/post.interface';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { AuthData } from 'src/app/models/auth-data.interface';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.scss']
})
export class NewPostComponent implements OnInit {
  user!: AuthData | null;

  constructor(private postSrv: PostService, private authsrv: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.authsrv.user$.subscribe((data) => {
      this.user = data;
      console.log('User data from auth service:', this.user); // Debug
    });
  }

  createPost(form: NgForm) {
    let post = {
      title: form.value.title,
      body: form.value.body,
      userId: this.user?.user.id
    }
    console.log(post);
    this.postSrv.createPost(post).subscribe();
    alert('Post creato!');
    this.router.navigate(['/'])
  }
}
