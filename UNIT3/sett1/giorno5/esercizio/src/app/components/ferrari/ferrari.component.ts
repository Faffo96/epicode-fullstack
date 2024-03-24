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
       Promise.all([this.getFerrari()]).then(cars => {
      for (let i = 0; i < cars.length; i++) {
        const brandsCars = cars[i];
        for (let j = 0; j < brandsCars.length; j++) {
          const car = brandsCars[j];
          if (car.brand === 'Ferrari') {
          this.ferrari.push(car);
        } 
        }
      }
    });
  }

  async getFerrari() {
    const response = await fetch('https://65ffea89df565f1a61457c13.mockapi.io/carShowroom/carShowroom');
    const cars = await response.json();
    const ferrariCars = cars.filter((car: CarData, index: number) => {
      return car.brand === "Ferrari";
    });
    return ferrariCars;
  }

}