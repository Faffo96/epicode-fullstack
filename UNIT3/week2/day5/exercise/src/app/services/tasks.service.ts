import { Injectable } from '@angular/core';
import { User } from '../models/user.interface';
import { Task } from '../models/task.interface';
import { throwError, BehaviorSubject } from 'rxjs';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class TasksService {
  private apiURL = 'https://66069c8dbe53febb857e4948.mockapi.io/toDo/tasks';
  private tasks: Task[] = [];
  private completedTasks: Task[] = [];
  private pendingTasks: Task[] = [];
  tasksSub = new BehaviorSubject<Task[]>([]);
  completedTasksSub = new BehaviorSubject<Task[]>([]);
  pendingTasksSub = new BehaviorSubject<Task[]>([]);


  constructor(private http: HttpClient) { }

  getTasks() {
    return this.http.get<any[]>(this.apiURL).pipe(
      map(tasks => {
        this.completedTasks = tasks.filter((task: Task) => task.completed);
        this.pendingTasks = tasks.filter((task: Task) => !task.completed);
        this.emitCompletedTasks();
        this.emitPendingTasks();
        return tasks;
      }),
      catchError(error => {
        console.error('Error fetching tasks:', error);
        return throwError(error);
      })
    );
  }

  updateTask(task: Task) {
    const url = `${this.apiURL}/${task.id}`;
    return this.http.put<Task>(url, task).pipe(
      map(updatedTask => {
        this.updateCompletedAndPendingTasks(updatedTask); // Aggiorna i compiti completati e in sospeso
        return updatedTask;
      }),
      catchError(error => {
        console.error('Error updating task:', error);
        return throwError(error);
      })
    );
  }

  private updateCompletedAndPendingTasks(updatedTask: Task): void {
    // Aggiorna i compiti completati e in sospeso in base allo stato del compito aggiornato
    if (updatedTask.completed) {
      this.completedTasks.push(updatedTask);
      this.pendingTasks = this.pendingTasks.filter(task => task.id !== updatedTask.id);
    } else {
      this.pendingTasks.push(updatedTask);
      this.completedTasks = this.completedTasks.filter(task => task.id !== updatedTask.id);
    }
    // Emetti i compiti aggiornati
    this.emitCompletedTasks();
    this.emitPendingTasks();
  }

  private emitCompletedTasks(): void {
    this.completedTasksSub.next(this.completedTasks);
  }

  private emitPendingTasks(): void {
    this.pendingTasksSub.next(this.pendingTasks);
  }

  updateCompletedTasks(tasks: Task[]): void {
    this.completedTasks = tasks;
    this.emitCompletedTasks();
  }

  updatePendingTasks(tasks: Task[]): void {
    this.pendingTasks = tasks;
    this.emitPendingTasks();
  }

  getCompletedTasksObservable(): Observable<Task[]> {
    return this.completedTasksSub;
  }

  getPendingTasksObservable(): Observable<Task[]> {
    return this.pendingTasksSub.asObservable();
  }


}

