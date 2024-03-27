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
      )
  }

  add() {
    this.sub
  }
}
