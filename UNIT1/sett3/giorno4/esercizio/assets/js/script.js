


const documentTable = document.getElementById('table');
const documentExtractionDisplay = document.querySelector('#extraction h2');
console.log(documentExtractionDisplay)

let extractedNumbers = [];
let cartelle = [];
const extractButton = document.querySelector('#extraction input');
const buyButton = document.querySelector('#form input:last-child');
const statusAlert = document.querySelector('#folders h2');
const resetBtn = document.getElementById('reset')

// Creo una funzione che genera la tabella e assegno: 
// Una classe ai padri delle singole caselle.
// Un testo (l'indice del ciclo) ai figli delle singole caselle.

const createTable = () => {
    let numbers = [];
    for (let i = 1; i <= 90; i++) {
        let newDiv = document.createElement('div');
        newDiv.classList.add('box');
        let newP = document.createElement('p');
        newP.innerText = i;
        newDiv.appendChild(newP);
        documentTable.appendChild(newDiv);
        numbers.push(i);
        extractButton.style.display = 'none';
    }
}

createTable()

boxes = document.querySelectorAll('.box');
Array.from(boxes);

// Creo una funzione che genera un numero random tra 1 e 90

const generateNumber = () => {
    let randomNumber = Math.floor(Math.random() * 91);
    if (randomNumber <= 0 || randomNumber > 90) {
        generateNumber();
        return randomNumber;
    } else {
        return randomNumber;
    }
}
//Creo una funzione che cambia il testo dell'ExtractionDisplay con un numero random.
//Salvo lo stesso valore dentro un secondo array chiamato extractedNumbers

const showNumber = () => {
    let randomNumber = generateNumber();
    if (extractedNumbers.includes(randomNumber)) {
        showNumber();
    } else {
        documentExtractionDisplay.innerText = randomNumber;
        extractedNumbers.push(randomNumber);
        checkFolder();
    }

}

//Creo una funzione per colorare le caselle dei numeri estratti

const colorExtracted = () => {
    boxes.forEach(box => {
        const number = parseInt(box.querySelector('p').innerText);
        if (extractedNumbers.includes(number)) {
            box.classList.add('extracted');
        }
    });
};

//Creo un addeventlistner on click per il bottone Extract

extractButton.addEventListener('click', function (event) {
    event.preventDefault();
    showNumber();
    colorExtracted();
});

// creo una funzione per generare una cartella

const createFolder = (n) => {
    for (let i = 0; i < n; i++) {
        const documentDivFolders = document.getElementById('folders');
    const newDivCartella = document.createElement('div');
    newDivCartella.classList.add('folder');
    const folderNumbers = [];
    for (let i = 1; i <= 27; i++) {
        let randomNumber = generateNumber();
        do {
            randomNumber = generateNumber();
        } while (folderNumbers.includes(randomNumber));

        let newDiv = document.createElement('div');
        newDiv.classList.add('box');
        let newP = document.createElement('p');
        newP.innerText = randomNumber;
        newDiv.appendChild(newP);
        newDivCartella.appendChild(newDiv);
        documentDivFolders.appendChild(newDivCartella);
        folderNumbers.push(randomNumber);

    } cartelle.push(folderNumbers);
    boxes = document.querySelectorAll('.box');
    Array.from(boxes);
    } 
}



let ambo = false;
    let terna = false;
    let quaterna = false;
    let tombola = false;

// Creo una funzione che divide la cartella in 3 righe (array) e verifica il punteggio
const checkFolder = () => {
    for (let i = 0; i < cartelle.length; i++) {
        const cartella = cartelle[i];
        let righe = [];

        for (let j = 0; j < cartella.length; j += 9) {
            const riga = cartella.slice(j, j + 9);
            righe.push(riga);
        }
        
        righe.forEach(riga => {
            let contatore = 0;
            riga.forEach(numero => {    
                if (extractedNumbers.includes(numero)) {
                    contatore++;
                    
                }
            });
            
            switch (contatore) {
                case 2:
                    if (!ambo) {
                        ambo = true;
                        statusAlert.innerText += "Ambo per la cartella " + (i + 1) + "!";
                    }
                    break;
                case 3:
                    if (!terna) {
                        terna = true;
                        statusAlert.innerText += "\nTerna per la cartella " + (i + 1) + "!";
                    }
                    break;
                case 4:
                    if (!quaterna) {
                        quaterna = true;
                        statusAlert.innerText += "\nQuaterna per la cartella " + (i + 1) + "!";
                    }
                    break;
                case 5:
                    if (!tombola) {
                        tombola = true;
                        statusAlert.innerText += "\nTombola per la cartella " + (i + 1) + "!";
                        extractButton.style.display = 'none';
                        resetBtn.style.display = 'inline';
                    }
                    break;
            }
        });
    }

}

buyButton.addEventListener('click', function () {
    const h2Text = document.querySelector('#form h2');
    const numeroCartelleUtente = document.getElementById('numeroCartelleUtente');
    createFolder(numeroCartelleUtente.value);
    buyButton.style.display = 'none';
    numeroCartelleUtente.style.display = 'none';
    h2Text.style.display = 'none';
    extractButton.style.display = 'inline';
    

})

resetBtn.addEventListener('click', function () {
    location.reload();
});
