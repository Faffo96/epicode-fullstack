import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/models/product.interface';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent implements OnInit {
  products: Product[] = [];
  cartItems: Product[] = [];
  sub!: Subscription;

  constructor(private productSrv: ProductsService) {}

  ngOnInit(): void {
      this.getProducts();
  }

  getProducts() {
    this.sub = this.productSrv.getProduct().subscribe(
      (products) => {
        this.products = products;
      },
      (err) => {
        alert(err);
      }
    );
  }

  addToCart(product: Product) {
    this.productSrv.addToCart(product, 1); // Passa la quantità 1 quando aggiungi un nuovo prodotto al carrello
  }
  

  addToFavorites(product: Product) {
    product.isLiked = true; // Imposta lo stato di preferenza su true
    this.productSrv.addFavorites(product);
  }

  isProductInFavorites(product: Product): boolean {
    return this.productSrv.isProductInFavorites(product); 
  }
}
