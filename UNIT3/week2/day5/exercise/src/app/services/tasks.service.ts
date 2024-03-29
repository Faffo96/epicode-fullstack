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

