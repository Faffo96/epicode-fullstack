/* ESERCIZIO 1
 Scrivi una funzione di nome "area", che riceve due parametri (l1, l2) e calcola l'area del rettangolo associato.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

area = (l1, l2) => {
    return l1 * l2;
}

console.log(area(8, 14));


/* ESERCIZIO 2
 Scrivi una funzione di nome "crazySum", che riceve due numeri interi come parametri.
 La funzione deve ritornare la somma dei due parametri, ma se il valore dei due parametri è il medesimo deve invece tornare
 la loro somma moltiplicata per tre.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

crazySum = (a, b) => {
    if (a === b) {
        return (a + b) * 3;
    } else {
        return a + b;
    }
}

console.log(crazySum(3, 2));

/* ESERCIZIO 3
 Scrivi una funzione di nome "crazyDiff" che calcola la differenza assoluta tra un numero fornito come parametro e 19.
 Deve inoltre tornare la differenza assoluta moltiplicata per tre qualora il numero fornito sia maggiore di 19.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

crazyDiff = (a) => {
    if (a > 19){
        return (a - 19) * 3;
    } else {
        return a - 19;
    }
}

console.log(crazyDiff());

/* ESERCIZIO 4
 Scrivi una funzione di nome "boundary" che accetta un numero intero n come parametro, e ritorna true se n è compreso tra 20 e 100 (incluso) oppure
 se n è uguale a 400.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

boundary = (n) => {
    if (n >= 20 && n <= 100) {
        return true;
    } else if (n === 400) {
        return true;
    } else {
        return false;
    }
}

console.log(boundary(400));

/* ESERCIZIO 5
 Scrivi una funzione di nome "epify" che accetta una stringa come parametro.
 La funzione deve aggiungere la parola "EPICODE" all'inizio della stringa fornita, ma se la stringa fornita comincia già con "EPICODE" allora deve
 ritornare la stringa originale senza alterarla.
*/



/* SCRIVI QUI LA TUA RISPOSTA */

epify = (string) => {
    
if (string.indexOf("EPICODE") === 0) { 
    return string;
} else {
    return "EPICODE " + string;
}
}

console.log(epify("Mi chiamo Fabio"))

/* ESERCIZIO 6
 Scrivi una funzione di nome "check3and7" che accetta un numero positivo come parametro. La funzione deve controllare che il parametro sia un multiplo
 di 3 o di 7. (Suggerimento: usa l'operatore modulo)
*/

/* SCRIVI QUI LA TUA RISPOSTA */

check3and7 = (a) => {
    if (a % 3 === 0 || a % 7 === 0) {
        return true;
    } else {
        return false;
    }
}

console.log(check3and7(3));

/* ESERCIZIO 7
 Scrivi una funzione di nome "reverseString", il cui scopo è invertire una stringa fornita come parametro (es. "EPICODE" --> "EDOCIPE")
*/

/* SCRIVI QUI LA TUA RISPOSTA */

reverseString = (string) => {
    return string.split("").reverse().join("");
}

console.log(reverseString("EPICODE"));

/* ESERCIZIO 8
 Scrivi una funzione di nome "upperFirst", che riceve come parametro una stringa formata da diverse parole.
 La funzione deve rendere maiuscola la prima lettera di ogni parola contenuta nella stringa.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

upperFirst = (string) => {
    let parole = string.split(" ");
    let risultato = parole.map(parola => parola.charAt(0).toUpperCase() + parola.slice(1));
    return risultato.join(" ");
}

console.log(upperFirst("Epicode è fantastico"));
/* ESERCIZIO 9
 Scrivi una funzione di nome "cutString", che riceve come parametro una stringa. La funzione deve creare una nuova stringa senza il primo e l'ultimo carattere
 della stringa originale.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

cutString = (inputString) => {
    if (inputString.length <= 2) {
        return "La stringa è troppo corta per essere tagliata.";
    } else {
        return inputString.slice(1, -1);
    }
}

console.log(cutString("Ciao sono Fabio"))

/* ESERCIZIO 10
 Scrivi una funzione di nome "giveMeRandom", che accetta come parametro un numero n e ritorna un'array contenente n numeri casuali inclusi tra 0 e 10.
*/

/* SCRIVI QUI LA TUA RISPOSTA */

giveMeRandom = (n) => {
    const numeriCasuali = [];

    for (let i = 0; i < n; i++) {
        const numero = Math.floor(Math.random() * 11);
        numeriCasuali.push(numero);
    }

    return numeriCasuali;
}

console.log(giveMeRandom(10))

