import { Component } from '@angular/core';
import { CarData } from 'src/app/models/car-data.interface';

@Component({
  selector: 'app-lamborghini',
  templateUrl: './lamborghini.component.html',
  styleUrls: ['./lamborghini.component.scss']
})
export class LamborghiniComponent {
  lamborghini: CarData[] = [];

  constructor() {
    this.getLamborghini().then(cars => {
      this.lamborghini = cars;
    });
  }

  async getLamborghini() {
    const response = await fetch('https://65ffea89df565f1a61457c13.mockapi.io/carShowroom/carShowroom');
    const cars = await response.json();
    return cars.filter((car: CarData) => car.brand === 'Lamborghini');
  }
}

