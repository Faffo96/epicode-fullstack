import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product.interface';
import { Subject, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ServiceNameService {
  constructor(private httpClient: HttpClient) { }

}

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private apiURL = 'https://dummyjson.com/products';
  favoritesSub = new Subject<number>();
  favouritesCounter = 0;

  constructor(private http: HttpClient) { }

  getProduct() {
    return this.http.get<any>(this.apiURL).pipe(
      map((response: any) => {
        // Assicurati che la risposta contenga la proprietà "products" e che sia un array
        if (response && response.products && Array.isArray(response.products)) {
          return response.products as Product[]; // Cast esplicitamente a Product[]
        } else {
          throw new Error('Formato dati non valido');
        }
      }),
      catchError((err) => {
        return throwError(this.getErrorMessage(err.status));
      })
    );
  }
  
  deleteProduct(id :number) {
    return this.http.delete(`${this.apiURL}/${id}`).pipe(
      catchError((err) => {
        return throwError(this.getErrorMessage(err.status));
      })
    );
  }

  addFavorites() {
    this.favouritesCounter++;
    this.favoritesSub.next(this.favouritesCounter)
  }

  getErrorMessage(status: number) {
    let message = '';
    switch (status) {
      case 404:
        message = "Elementi non trovati";
        break;

      default:
        message = "Qualcosa non ha funzioato nella chiamata";
        break;
    }
    return message;
  }
}
