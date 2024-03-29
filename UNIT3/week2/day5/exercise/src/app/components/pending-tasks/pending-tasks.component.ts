import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Task } from 'src/app/models/task.interface';
import { User } from 'src/app/models/user.interface';
import { UsersService } from 'src/app/services/users.service';
import { TasksService } from 'src/app/services/tasks.service';

@Component({
  selector: 'app-pending-tasks',
  templateUrl: './pending-tasks.component.html',
  styleUrls: ['./pending-tasks.component.scss']
})
export class PendingTasksComponent {
  users: User[] = [];
  completedTasks: Task[] = [];
  pendingTasks: Task[] = [];
  sub!: Subscription;

  constructor(private usersSrv: UsersService, private tasksSrv: TasksService) { }

  ngOnInit(): void {
    this.loadUsers();
    this.loadPendingTasks();
  }

  loadUsers() {
    this.sub = this.usersSrv.getUsersObservable().subscribe(
      (users) => {
        if (users.length > 0) {
          this.users = users;
        }
      },
      (err) => {
        alert(err);
      }
    );
  }

  loadPendingTasks() {
    this.sub = this.tasksSrv.getPendingTasksObservable().subscribe(
      (pendingTasks: Task[]) => {
        if (pendingTasks.length > 0) {
          this.pendingTasks = pendingTasks;
          console.log("Pending tasks:", pendingTasks);
        }
      },
      (err) => {
        alert(err);
      }
    )
  }

  getAssignedUserName(userId: number): string {
    const user = this.users.find(u => u.id === userId.toString()); // Converti userId in una stringa
    return user ? user.firstName : 'Unknown'; // Ritorna il nome dell'utente se trovato, altrimenti 'Unknown'
  }

  /* toggleTaskCompletion(task: Task) {
    task.completed = !task.completed;
    this.tasksSrv.updateTask(task).subscribe(
      () => {
        // Aggiorna i compiti pendenti e completati dopo l'aggiornamento
        this.loadPendingTasks();
        this.loadCompletedTasks();
      },
      (err) => {
        console.error("Errore nell'aggiornamento del compito:", err);
      }
    );
  } */

}

