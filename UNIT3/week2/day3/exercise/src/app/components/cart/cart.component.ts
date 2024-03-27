import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product.interface';
import { ProductsService } from 'src/app/services/products.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent {
  @Input() cartItems: Product[] = [];
  private subscription!: Subscription;
  cartTotal: number = 0;

  constructor(private productService: ProductsService) {}

  ngOnInit(): void {
    this.subscription = this.productService.cartItemsSub.subscribe((products: Product[]) => {
      this.cartItems = products;
      this.calculateCartTotal(); // Aggiungi questa linea per calcolare il totale del carrello
    });
  }
  
  
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  deleteFromCart(product: Product) {
    this.productService.removeFromCart(product);
  }

  calculateCartTotal() {
    this.cartTotal = this.cartItems.reduce((total, item) => total + (item.price * item.quantity), 0);
  }
}
