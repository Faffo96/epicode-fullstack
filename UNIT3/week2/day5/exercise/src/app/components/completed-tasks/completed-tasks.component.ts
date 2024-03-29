import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Task } from 'src/app/models/task.interface';
import { User } from 'src/app/models/user.interface';
import { UsersService } from 'src/app/services/users.service';
import { TasksService } from 'src/app/services/tasks.service';

@Component({
  selector: 'app-completed-tasks',
  templateUrl: './completed-tasks.component.html',
  styleUrls: ['./completed-tasks.component.scss']
})
export class CompletedTasksComponent {
  users: User[] = [];
  completedTasks: Task[] = [];
  pendingTasks: Task[] = [];
  sub!: Subscription;

  constructor(private usersSrv: UsersService, private tasksSrv: TasksService) { }

  ngOnInit(): void {
    this.loadUsers();
    this.loadCompletedTasks();
  }

  loadUsers() {
    this.sub = this.usersSrv.getUsersObservable().subscribe(
      (users) => {
        if (users.length > 0) {
          this.users = users;
        } else {
          console.log('Array degli utenti vuoto');
        }
      },
      (err) => {
        alert(err);
      }
    );
  }

  loadCompletedTasks() {
    this.sub = this.tasksSrv.getCompletedTasksObservable().subscribe(
      (completedTasks: Task[]) => {
        if (completedTasks.length > 0) {
          this.completedTasks = completedTasks;
          console.log("Completed tasks:", completedTasks);
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
  
  toggleTaskCompletion(task: Task) {
    task.completed = !task.completed; // Inverti lo stato della proprietà completed
    this.tasksSrv.updateTask(task).subscribe(
      (updatedTask: Task) => {
        // Se l'aggiornamento ha successo, aggiorna gli array di completedTasks e pendingTasks
        if (!task.completed) {
          // Se il task viene segnato come non completato, spostalo da completedTasks a pendingTasks
          this.completedTasks = this.completedTasks.filter(t => t.id !== task.id);
          this.pendingTasks.push(updatedTask);
        }
      },
      (err) => {
        alert(err);
        // Se si verifica un errore, reimposta lo stato del checkbox alla sua posizione precedente
        task.completed = !task.completed;
      }
    );
  }

}
