import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.scss']
})
export class FavouritesComponent implements OnInit {
  totFavorites :number = 0;
  constructor(private productSrv: ProductsService) {}

  ngOnInit(): void {
      this.productSrv.favoritesSub.subscribe((count) => {
        this.totFavorites = count;
      })
  }
}
