import { Component } from '@angular/core';
import { CarData } from 'src/app/models/car-data.interface';

@Component({
  selector: 'app-ferrari',
  templateUrl: './ferrari.component.html',
  styleUrls: ['./ferrari.component.scss']
})
export class FerrariComponent {
  ferrari: CarData[] = [];

  constructor() {
    this.getFerrari().then(cars => {
      this.ferrari = cars;
    });
  }

  async getFerrari() {
    const response = await fetch('https://65ffea89df565f1a61457c13.mockapi.io/carShowroom/carShowroom');
    const cars = await response.json();
    return cars.filter((car: CarData) => car.brand === 'Ferrari');
  }
}

