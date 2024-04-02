import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product.interface';
import { throwError, BehaviorSubject } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { of } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  private apiURL = 'https://dummyjson.com/products';
  private favorites: Product[] = [];
  private cartItems: Product[] = []; 
  favoritesSub = new BehaviorSubject<Product[]>([]);
  cartItemsSub = new BehaviorSubject<Product[]>([]);


  constructor(private http: HttpClient) { }

  getProduct() {
    return this.http.get<any>(this.apiURL).pipe(
      map((response: any) => {
        if (response && response.products && Array.isArray(response.products)) {
          return response.products as Product[];
        } else {
          throw new Error('Formato dati non valido');
        }
      }),
      catchError((err) => {
        return throwError(this.getErrorMessage(err.status));
      })
    );
  }

  addFavorites(product: Product) {
    const existingIndex = this.favorites.findIndex(p => p.id === product.id);
    if (existingIndex === -1) {
      this.favorites.push(product);
      this.updateFavorites();
    }
  }

  removeFromFavorites(product: Product) {
    const indexToRemove = this.favorites.findIndex(p => p.id === product.id);
    if (indexToRemove !== -1) {
      this.favorites.splice(indexToRemove, 1);
      this.updateFavorites();
    }
    // Restituisci un observable vuoto
    return of(null);
  }

  isProductInFavorites(product: Product): boolean {
    return this.favorites.some(p => p.id === product.id);
  }

  private updateFavorites() {
    this.favoritesSub.next(this.favorites.slice());
  }

  // Aggiungi un prodotto al carrello e gestisci la quantità
  addToCart(product: Product, quantity: number) {
    const existingItem = this.cartItems.find(item => item.id === product.id);
    if (existingItem) {
      existingItem.quantity += quantity; // Incrementa la quantità se il prodotto è già nel carrello
    } else {
      product.quantity = quantity; // Imposta la quantità se è il primo elemento del suo genere nel carrello
      this.cartItems.push(product); // Aggiungi il prodotto al carrello
    }
    // Aggiorna il carrello
    this.updateCart();
  }
  


  // Rimuovi un prodotto dal carrello
  removeFromCart(product: Product) {
    const indexToRemove = this.cartItems.findIndex(item => item.id === product.id);
    if (indexToRemove !== -1) {
      this.cartItems.splice(indexToRemove, 1); // Rimuovi il prodotto dal carrello
      this.updateCart(); // Aggiorna il carrello
    }
  }

  
  // Aggiorna l'observable dei prodotti nel carrello
  private updateCart() {
    this.cartItemsSub.next(this.cartItems.slice());
  }

  getCartTotal(): number {
    let total = 0;
    this.cartItems.forEach(item => {
      total += item.price * item.quantity; // Moltiplica il prezzo per la quantità e aggiungi al totale
    });
    return total;
  }
  

  getErrorMessage(status: number) {
    let message = '';
    switch (status) {
      case 404:
        message = "Elementi non trovati";
        break;

      default:
        message = "Qualcosa non ha funzionato nella chiamata";
        break;
    }
    return message;
  }

}


