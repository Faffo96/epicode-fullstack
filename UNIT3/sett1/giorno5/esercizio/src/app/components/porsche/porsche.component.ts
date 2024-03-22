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
       Promise.all([this.getPorsche()]).then(cars => {
      for (let i = 0; i < cars.length; i++) {
        const brandsCars = cars[i];
        for (let j = 0; j < brandsCars.length; j++) {
          const car = brandsCars[j];
          if (car.brand === 'Porsche') {
          this.porsche.push(car);
        } 
        }
      }
    });
  }

  async getPorsche() {
    const response = await fetch('../../../assets/db.json');
    const cars = await response.json();
    const porscheCars = cars.filter((car: CarData, index: number) => {
      return car.brand === "Porsche";
    });
    return porscheCars;
  }

}

