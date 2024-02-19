/*
REGOLE
- Tutte le risposte devono essere scritte in JavaScript
- Puoi usare Google / StackOverflow ma solo quanto ritieni di aver bisogno di qualcosa che non è stato spiegato a lezione
- Puoi testare il tuo codice in un file separato, o de-commentando un esercizio alla volta
- Per visualizzare l'output, lancia il file HTML a cui è collegato e apri la console dagli strumenti di sviluppo del browser. 
- Utilizza dei console.log() per testare le tue variabili e/o i risultati delle espressioni che stai creando.
*/

/* ESERCIZIO 1
    Dato il seguente array, scrivi del codice per stampare ogni elemento dell'array in console.
*/



console.log("Esercizio 1:");

const pets = ['dog', 'cat', 'hamster', 'redfish'];

pets.forEach(pet => {
  console.log(pet);
});

/* ESERCIZIO 2
    Scrivi del codice per ordinare alfabeticamente gli elementi dell'array "pets".
*/

console.log("Esercizio 2:");

pets.sort();

console.log(pets);

/* ESERCIZIO 3
    Scrivi del codice per stampare nuovamente in console gli elementi dell'array "pets", questa volta in ordine invertito.
*/

console.log("Esercizio 3:");

pets.reverse();

console.log(pets);

/* ESERCIZIO 4
    Scrivi del codice per spostare il primo elemento dall'array "pets" in ultima posizione.
*/

console.log("Esercizio 4:");

let primoElemento = pets.splice(0, 1)[0];
pets.push(primoElemento);

console.log(pets);

/* ESERCIZIO 5
    Dato il seguente array di oggetti, scrivi del codice per aggiungere ad ognuno di essi una proprietà "licensePlate" con valore a tua scelta.
*/

console.log("Esercizio 5:");

const cars = [
  {
    brand: 'Ford',
    model: 'Fiesta',
    color: 'red',
    trims: ['titanium', 'st', 'active'],
  },
  {
    brand: 'Peugeot',
    model: '208',
    color: 'blue',
    trims: ['allure', 'GT'],
  },
  {
    brand: 'Volkswagen',
    model: 'Polo',
    color: 'black',
    trims: ['life', 'style', 'r-line'],
  },
];

for (let i = 0; i < cars.length; i++) {
  cars[i].licensePlate = 'NuovoValore';
}

console.log(cars);

/* ESERCIZIO 6
    Scrivi del codice per aggiungere un nuovo oggetto in ultima posizione nell'array "cars", rispettando la struttura degli altri elementi.
    Successivamente, rimuovi l'ultimo elemento della proprietà "trims" da ogni auto.
*/

console.log("Esercizio 6:");

let nuovoOggetto = {
  brand: 'Audi',
  model: 'A1',
  color: 'black',
  trims: ['life', 'allure'],
  licensePlate: 'NuovoValore',
}

cars.push(nuovoOggetto);

console.log(cars);

/* ESERCIZIO 7
    Scrivi del codice per salvare il primo elemento della proprietà "trims" di ogni auto nel nuovo array "justTrims", sotto definito.
*/

console.log("Esercizio 7:");

const justTrims = [];


pets.push(primoElemento);

for (let i = 0; i < cars.length; i++) {
  let primoElemento;
  primoElemento = cars[i].trims.splice(0, 1)[0];


  justTrims.push(primoElemento);

}

console.log(justTrims);

/* ESERCIZIO 8
    Cicla l'array "cars" e costruisci un if/else statament per mostrare due diversi messaggi in console. Se la prima lettera della proprietà
    "color" ha valore "b", mostra in console "Fizz". Altrimenti, mostra in console "Buzz".
*/

console.log("Esercizio 8:");

cars.forEach(element => {
  if (element.color[0] === "b") {
    console.log("Fizz");
  } else {
    console.log("Buzz");
  }
});

/* ESERCIZIO 9
    Utilizza un ciclo while per stampare in console i valori del seguente array numerico fino al raggiungimento del numero 32.
*/

console.log("Esercizio 9: ");

const numericArray = [
  6, 90, 45, 75, 84, 98, 35, 74, 31, 2, 8, 23, 100, 32, 66, 313, 321, 105,];

let i = 0;
while (i < numericArray.length) {
  if (numericArray[i] === 32) {
    break;
  }
  console.log(numericArray[i]);
  i++;
}


/* ESERCIZIO 10
    Partendo dall'array fornito e utilizzando un costrutto switch, genera un nuovo array composto dalle posizioni di ogni carattere all'interno
    dell'alfabeto italiano.
    es. [f, b, e] --> [6, 2, 5]
*/

/* console.log("Esercizio 10: ");

const charactersArray = ['g', 'n', 'u', 'z', 'd'];
const alphabet = 'abcdefghijklmnopqrstuvwxyz';
for (let i = 0; i < charactersArray.length; i++) {
  const element = charactersArray[i];
  console.log(alphabet.indexOf(element));
}
console.log(alphabet.indexOf('g'));
const nuovoArray = [];

for (let i = 0; i < charactersArray.length; i++) {
  const element = charactersArray[i];

  switch (element) {
    case 'g':
      nuovoArray.push('6');
      break;
    case 'n':
      nuovoArray.push('13');
      break;
    case 'u':
      nuovoArray.push('20');
      break;
    case 'z':
      nuovoArray.push('25');
      break;
    case 'd':
      nuovoArray.push('3');
      break;
  }
}

console.log(nuovoArray); */

const charactersArray = ['g', 'n', 'u', 'z', 'd'];
const alphabet = 'abcdefghijklmnopqrstuvwxyz';
const nuovoArray = [];

for (let i = 0; i < charactersArray.length; i++) {
  const character = charactersArray[i];
  const index = alphabet.indexOf(character);

  switch (true) {
    case character === alphabet :
      
      break;
  
    default:
      break;
  }

  if (index !== -1) {
    nuovoArray.push(index);
  }
}

console.log(nuovoArray);


