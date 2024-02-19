/*
REGOLE
- Tutte le risposte devono essere scritte in JavaScript
- Puoi usare Google / StackOverflow ma solo quanto ritieni di aver bisogno di qualcosa che non è stato spiegato a lezione
- Puoi testare il tuo codice in un file separato, o de-commentando un esercizio alla volta
- Per visualizzare l'output, lancia il file HTML a cui è collegato e apri la console dagli strumenti di sviluppo del browser. 
- Utilizza dei console.log() per testare le tue variabili e/o i risultati delle espressioni che stai creando.
*/

/* ESERCIZIO 1
 Elenca e descrivi i principali datatype in JavaScript. Prova a spiegarli come se volessi farli comprendere a un bambino.
*/

/* In javascript abbiamo come delle scatole per conservare le nostre cose al meglio. Le scatole sono di tanti tipi, fatti su misura per determinate cose, ad esempio:

La scatola di tipo String puo' contenere le parole.
La scatola di tipo Number puo' contenere numeri, interi o decimali.
Nella scatola di tipo Boolean non sappiamo cosa c'è ma ha un'etichetta sopra con scritto se è vuota o no.
La scatola di tipo Array è un grande scatolone con al suo interno altre scatole piu' piccole.
La scatola di tipo Null è una scatola vuota.
La scatola di tipo Undefined è una scatola di cui non si conosce il contenuto.
La scatola di tipo Object è una grande scatola per un grande giocattolo, che viene smontato e ogni suo componente viene messo all'interno di altre scatole piu' piccole.
 */

/* ESERCIZIO 2
 Crea una variable chiamata "myName" e assegna ad essa il tuo nome, sotto forma di stringa.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

let myName = "Fabio";

/* ESERCIZIO 3
 Scrivi il codice necessario ad effettuare un addizione (una somma) dei numeri 12 e 20.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

let a = 12;
let b = 20;
let somma;

somma = a + b;
console.log("Esercizio 1: " + somma);

/* ESERCIZIO 4
 Crea una variable di nome "x" e assegna ad essa il numero 12.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

let x = 12;

/* ESERCIZIO 5
  Riassegna un nuovo valore alla variabile "myName" già esistente: il tuo cognome.
  Dimostra l'impossibilità di riassegnare un valore ad una variabile dichiarata con il costrutto const.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

myName = "Scaramozzino";
console.log("Esercizio 5: " + myName);


const y = 10;
/* y = y * 2; */
console.log("Esercizio 5.1: " + y);

/* ESERCIZIO 6
 Esegui una sottrazione tra i numeri 4 e la variable "x" appena dichiarata (che contiene il numero 12).
*/

/* SCRIVI QUI LA TUA RISPOSTA */

let sottrazione;
sottrazione = 4 - x;
console.log("Esercizio 6: " + sottrazione);


/* ESERCIZIO 7
 Crea due variabili: "name1" e "name2". Assegna a name1 la stringa "john", e assegna a name2 la stringa "John" (con la J maiuscola!).
 Verifica che name1 sia diversa da name2 (suggerimento: è la stessa cosa di verificare che la loro uguaglianza sia falsa).
 EXTRA: verifica che la loro uguaglianza diventi true se entrambe vengono trasformate in lowercase (senza cambiare il valore di name2!).
*/

let name1 = "john";
let name2 = "John";

console.log("Esercizio 7: " + (name1===name2));

name2 = name2.toLowerCase();

console.log("Esercizio 7.1: " + (name1===name2));

let nomee = "mario"

let votoEsame = 18;

if (votoEsame >= 18 && nomee === "mario") {
  console.log("puo' entrare")
}
else {
  console.log("non puo' entrare")
}