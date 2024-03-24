import { Component } from '@angular/core';
import { CarData } from 'src/app/models/car-data.interface';

@Component({
  selector: 'app-porsche',
  templateUrl: './porsche.component.html',
  styleUrls: ['./porsche.component.scss']
})
export class PorscheComponent {
  porsche: CarData[] = [];

  constructor() {
    this.getPorsche().then(cars => {
      this.porsche = cars;
    });
  }

  async getPorsche() {
    const response = await fetch('https://65ffea89df565f1a61457c13.mockapi.io/carShowroom/carShowroom');
    const cars = await response.json();
    return cars.filter((car: CarData) => car.brand === 'Porsche');
  }
}
