import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/service/post.service';
import { Post } from 'src/app/models/post.interface';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-new-post',
    templateUrl: './new-post.component.html',
    styleUrls: ['./new-post.component.scss'],
})
export class NewPostComponent implements OnInit {

    arrayUsers: any[] = [];

    constructor(private postSrv: PostService, private userSrv: UserService, private router: Router) {}

    ngOnInit(): void {
        this.userSrv.getUsers().subscribe((data) => {
            this.arrayUsers = data;
        });
    }

    save(form: NgForm) {
        this.postSrv.newPost(form.value).subscribe();
        this.router.navigate(['/']);
    }
}
