import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Task } from 'src/app/models/task.interface';
import { User } from 'src/app/models/user.interface';
import { UsersService } from 'src/app/services/users.service';
import { TasksService } from 'src/app/services/tasks.service';
import { Input } from '@angular/core';

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
    const user = this.users.find(u => u.id == userId); // Converti userId in una stringa
    return user ? `${user.firstName} ${user.lastName}` : 'Unknown'; // Ritorna il nome dell'utente se trovato, altrimenti 'Unknown'
  }

  toggleTaskCompletion(task: Task) {
    task.completed = !task.completed; // Cambia lo stato della proprietà completed
    this.tasksSrv.updateTask(task).subscribe(
      (updatedTask: Task) => {
        // Se l'aggiornamento ha successo, aggiorna gli array di completedTasks e pendingTasks
        if (task.completed) {
          // Se il task è stato completato, spostalo da pendingTasks a completedTasks
          this.pendingTasks = this.pendingTasks.filter(t => t.id !== task.id);
          this.completedTasks.push(updatedTask);
        } else {
          // Se il task è stato segnato come non completato, spostalo da completedTasks a pendingTasks
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
  

  private subscribeToTaskUpdates(): void {
    // Sottoscrivi il componente agli Observable dei compiti completati e in sospeso
    this.tasksSrv.getCompletedTasksObservable().subscribe(
      (completedTasks: Task[]) => {
        this.completedTasks = completedTasks;
      },
      (err) => {
        alert(err);
      }
    );

    this.tasksSrv.getPendingTasksObservable().subscribe(
      (pendingTasks: Task[]) => {
        this.pendingTasks = pendingTasks;
      },
      (err) => {
        alert(err);
      }
    );
  }
}

