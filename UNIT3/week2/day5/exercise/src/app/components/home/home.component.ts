import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Task } from 'src/app/models/task.interface';
import { User } from 'src/app/models/user.interface';
import { UsersService } from 'src/app/services/users.service';
import { TasksService } from 'src/app/services/tasks.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private users: User[] = [];
  private tasks: Task[] = [];

  sub!: Subscription;

  constructor(private usersSrv: UsersService, private tasksSrv: TasksService) {}

  ngOnInit(): void {
    this.loadUsers();
    this.loadTasks();
  }

  private loadUsers(): void {
    this.sub = this.usersSrv.getUsers().subscribe(users => {
      this.users = users;
      console.log("users:")
      console.log(this.users)
    });
  }

  private loadTasks(): void {
    this.sub = this.tasksSrv.getTasks().subscribe((tasks: Task[]) => {
      this.tasks = tasks;
      console.log("tasks:")
      console.log(this.tasks)
    });
  }

  
}
