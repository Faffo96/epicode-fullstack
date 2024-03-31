import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Task } from 'src/app/models/task.interface';
import { User } from 'src/app/models/user.interface';
import { UsersService } from 'src/app/services/users.service';
import { TasksService } from 'src/app/services/tasks.service';
import { Input } from '@angular/core';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {
  users: User[] = [];
  pendingTasks: Task[] = [];
  completedTasks: Task[] = [];
  filteredUsers: User[] = [];
  searchTerm: string = '';
  sub!: Subscription;

  constructor(private usersSrv: UsersService, private tasksSrv: TasksService) { }

  ngOnInit(): void {

    this.loadPendingTasks();
    this.loadCompletedTasks();
    this.loadUsers();
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


    // Ottieni tutti gli userId dei task, sia completati che pendenti, convertendoli in stringhe
    const taskUserIds = [...this.pendingTasks.map(task => task.userId.toString()), ...this.completedTasks.map(task => task.userId.toString())];


    // Itera su tutti gli utenti e aggiungi quelli che hanno almeno un task al nuovo array
    this.users.forEach(user => {
      const userIdString = user.id.toString();
      if (taskUserIds.includes(userIdString)) {
        this.filteredUsers.push(user);
      }
    });

    // Assegna il nuovo array con gli utenti che hanno almeno un task all'array users
    this.users = this.filteredUsers;
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

  toggleTaskCompletion(task: Task) {
    task.completed = !task.completed; // Cambia lo stato della proprietà completed
    this.tasksSrv.updateTask(task).subscribe(
      (updatedTask: Task) => {
        // Se l'aggiornamento ha successo, aggiorna gli array di completedTasks e pendingTasks
        if (task.completed) {
          // Se il task è stato completato, spostalo da pendingTasks a completedTasks
          this.pendingTasks = this.pendingTasks.filter(t => t.id !== task.id);
          if (!this.completedTasks.find(t => t.id === task.id)) {
            this.completedTasks.push(updatedTask);
          }
        } else {
          // Se il task è stato segnato come non completato, spostalo da completedTasks a pendingTasks
          this.completedTasks = this.completedTasks.filter(t => t.id !== task.id);
          if (!this.pendingTasks.find(t => t.id === task.id)) {
            this.pendingTasks.push(updatedTask);
          }
        }
      },
      (err) => {
        alert(err);
        // Se si verifica un errore, reimposta lo stato del checkbox alla sua posizione precedente
        task.completed = !task.completed;
      }
    );
  }

  filterUsers() {
    // Filtra gli utenti in base alla stringa di ricerca
    this.filteredUsers = this.users.filter(user =>
      user.firstName.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      user.lastName.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}
