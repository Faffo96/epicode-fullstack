import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarData } from 'src/app/models/car-data.interface';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  carData!: CarData;
  searchTerm: string = '';

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const model = params.get('model');
      if (model) {
        this.getCar(model);
      }
    });
  }

  async getCar(model: string) {
    try {
      const response = await fetch('../../../assets/db.json');
      const cars: CarData[] = await response.json();
      const car = cars.find((car: CarData) => car.model === model);
      if (car) {
        this.carData = car;
      } else {
        console.log('Auto non trovata');
      }
    } catch (error) {
      console.error('Errore nel recupero dei dati delle auto', error);
    }
  }

  async onSubmit() {
    if (this.searchTerm.trim() === '') {
      return;
    }
    try {
      const response = await fetch('../../../assets/db.json');
      const cars: CarData[] = await response.json();
      
      // Ricerca per corrispondenza parziale del modello dell'auto
      const matchingCarsByModel = cars.filter((car: CarData) =>
        car.model.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
      if (matchingCarsByModel.length > 0) {
        // Se ci sono corrispondenze parziali per il modello, reindirizziamo alla prima corrispondenza
        const firstMatchingCar = matchingCarsByModel[0];
        this.router.navigate(['/details', firstMatchingCar.model]);
        return; // Terminiamo la funzione per evitare di eseguire la ricerca per il marchio
      }
      
      // Ricerca per corrispondenza parziale del marchio dell'auto
      const matchingCarsByBrand = cars.filter((car: CarData) =>
        car.brand.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
      if (matchingCarsByBrand.length > 0) {
        // Se ci sono corrispondenze parziali per il marchio, reindirizziamo alla pagina del marchio corrispondente
        const firstMatchingCar = matchingCarsByBrand[0];
        this.router.navigate(['/' + firstMatchingCar.brand.toLowerCase()]);
        return; // Terminiamo la funzione poiché abbiamo trovato una corrispondenza
      }
  
      console.log('Nessuna corrispondenza trovata per il modello o il marchio');
    } catch (error) {
      console.error('Errore nel recupero dei dati delle auto', error);
    }
  }
  
  
  
}
