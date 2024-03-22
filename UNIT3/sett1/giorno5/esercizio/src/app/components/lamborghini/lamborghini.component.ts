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
       Promise.all([this.getLamborghini()]).then(cars => {
      for (let i = 0; i < cars.length; i++) {
        const brandsCars = cars[i];
        for (let j = 0; j < brandsCars.length; j++) {
          const car = brandsCars[j];
          
        if (car.brand === 'Lamborghini') {
          this.lamborghini.push(car);
        }
        }
      }
    });
  }
  

  async getLamborghini() {
    const response = await fetch('../../../assets/db.json');
    const cars = await response.json();
    const lamborghiniCars = cars.filter((car: CarData, index: number) => {
      return car.brand === "Lamborghini";
    });
    return lamborghiniCars;
  }
}
