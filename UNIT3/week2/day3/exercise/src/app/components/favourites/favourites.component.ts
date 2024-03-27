import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/models/product.interface';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.scss']
})
export class FavouritesComponent implements OnInit, OnDestroy {
  products: Product[] = [];
  private subscription!: Subscription;

  constructor(private productService: ProductsService) {}

  ngOnInit(): void {
    this.subscription = this.productService.favoritesSub.subscribe((products: Product[]) => {
      this.products = products;
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  deleteFromFavorites(product: Product) {
    // Verifica se product è definito e non è null
    if (product !== undefined && product !== null) {
      // Rimuovi il prodotto dai preferiti utilizzando il servizio ProductsService
      // In questo caso, dovresti chiamare il metodo deleteProduct del service
      this.productService.removeFromFavorites(product).subscribe(() => {
        // Rimuovi il prodotto dalla lista dei preferiti nel componente
        this.products = this.products.filter(p => p !== product);
      });
      product.isLiked = false;
    }
  }
}
