const documentTable = document.getElementById('table');
const documentExtractionDisplay = document.querySelector('#extraction h2');
let numbers = [];
let extractedNumbers = [];
const extractButton = document.querySelector('#extraction input')

// Creo una funzione che genera la tabella e assegno: 
// Una classe ai padri delle singole caselle.
// Un testo (l'indice del ciclo) ai figli delle singole caselle.

export const createTable = () => {
    for (let i = 1; i <= 90; i++) {
        let newDiv = document.createElement('div');
        newDiv.classList.add('box');
        let newP = document.createElement('p');
        newP.innerText = i;
        newDiv.appendChild(newP);
        documentTable.appendChild(newDiv);
        numbers.push(i);
    }
}



//Creo una funzione che cambia il testo dell'ExtractionDisplay con un numero random.
//Salvo lo stesso valore dentro un secondo array chiamato extractedNumbers

export const extractNumber = () => {
    let randomNumber = Math.floor(Math.random() * 91);
    if (randomNumber <= 0 || randomNumber >= 90 || extractedNumbers.includes(randomNumber)) {
        extractNumber();
    } else {
        documentExtractionDisplay.innerText = randomNumber;
        extractedNumbers.push(randomNumber);
    }
}

//Creo una funzione per colorare le caselle dei numeri estratti

export const colorExtracted = () => {
    const boxes = document.querySelectorAll('.box');
    Array.from(boxes);
    boxes.forEach(box => {
        const number = parseInt(box.querySelector('p').innerText);
        if (extractedNumbers.includes(number)) {
            box.classList.add('extracted');
        }
    });
};

