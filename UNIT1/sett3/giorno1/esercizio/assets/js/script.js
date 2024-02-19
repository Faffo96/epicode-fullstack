
/* ESERCIZIO 1
 Scrivi una funzione per cambiare il titolo della pagina in qualcos'altro
*/

const changeTitle = function (string) {
    let h1 = document.getElementById('h1');
    h1.innerText = string;
}

changeTitle("Nuovo Titolo");

/* ESERCIZIO 2
 Scrivi una funzione per aggiungere al titolo della pagina una classe "myHeading"
*/

const addClassToTitle = function (className) {
    h1.classList.add(className);
}

addClassToTitle('myHeading');

/* ESERCIZIO 3
 Scrivi una funzione che cambi il testo dei p figli di un div
*/

let divs = document.querySelectorAll('div');


console.log(divs)

const changePcontent = function (string) {
    divs.forEach(div => {
        let paragrafi = div.querySelectorAll('p');
        paragrafi.forEach(p => {
            p.innerText = string;
        });
    });

}

changePcontent('cambio frase');

/* ESERCIZIO 4
 Scrivi una funzione che cambi la proprietà href di ogni link (tranne quello nel footer) con il valore https://www.google.com
*/

const changeUrls = function (url) {
    let links = document.querySelectorAll('a :not(footer a)');
    links.forEach(link => {
        link.setAttribute('href', url);
    });
}

changeUrls('https://www.google.com');

/* ESERCIZIO 5
 Scrivi una funzione che aggiunga un nuovo elemento lista alla seconda lista non ordinata
*/
let secondList = document.getElementById('secondList');
let nuovoLi = document.createElement('li')

const addToTheSecond = function () {
    nuovoLi.textContent = 'Nuovo li'
    secondList.appendChild(nuovoLi);
}

addToTheSecond();

/* ESERCIZIO 6
 Scrivi una funzione che aggiunga un paragrafo al primo div
*/

let primoDiv = document.querySelector('#primoDiv');
let nuovoP = document.createElement('p');
const addParagraph = function () {
    nuovoP.textContent = 'Nuovo p';
    primoDiv.appendChild(nuovoP);
}

addParagraph();

/* ESERCIZIO 7
 Scrivi una funzione che faccia scomparire la prima lista non ordinata
*/

let firstList = document.getElementById('firstList');

const hideFirstUl = function () {
    firstList.remove();
}

/* hideFirstUl(); */

/* ESERCIZIO 8 
 Scrivi una funzione che renda verde il background di ogni lista non ordinata
*/

let liste = document.querySelectorAll('ul');


const paintItGreen = function () {
    liste.forEach(lista => {
        lista.classList.add('bg-green');
    });
}

paintItGreen();

/* ESERCIZIO 9
 Scrivi una funzione che rimuova l'ultima lettera dall'h1 ogni volta che l'utente lo clicca
*/

const makeItClickable = function () {
    let nuovaStringa = h1.innerText.slice(0, -1);
    h1.innerText = nuovaStringa;
}

h1.addEventListener('click', makeItClickable);

/* ESERCIZIO 10
 Crea una funzione che, al click sul footer, riveli l'URL del link interno come contenuto di un alert()
*/

let footer = document.querySelector('footer');
let footer_link = document.getElementById('linkInterno');

const revealFooterLink = function () {
    alert("https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Client-side_web_APIs/Manipulating_documents");
}

footer.addEventListener('click', revealFooterLink)

/* ESERCIZIO 11
 Crea una funzione che crei una tabella nell'elemento con id "tableArea". 
 La tabella avrà 5 elementi e questa struttura: immagine, nome prodotto, quantità, prezzo
*/
let tableArea = document.getElementById('tableArea');
let newTable = document.createElement('table');
let newThead = document.createElement('thead');
let newTbody = document.createElement('tbody');

let newTr = document.createElement('tr');
let newTd = document.createElement('td');
const generateTable = function () {
    tableArea.appendChild(newTable);
    let table = document.querySelector('table');
    table.appendChild(newThead);
    table.appendChild(newTbody);
    let thead = document.querySelector('thead');
    thead.appendChild(newTr);
    let tr = document.querySelector('tr');
    let titoli = ['Immagine', 'Nome prodotto', 'Quantità', 'Prezzo']

    titoli.forEach(titolo => {
        let newTh = document.createElement('th');
        newTh.innerText = titolo;
        tr.appendChild(newTh);
    });

    let tbody = document.querySelector('tbody');

    for (let i = 0; i < 4; i++) {
        newTr = document.createElement('tr');
        tbody.appendChild(newTr);
        
        let tdImmagine = document.createElement('td');
    tdImmagine.innerText = 'immagine';
    tdImmagine.setAttribute('class', 'immagine')
    newTr.appendChild(tdImmagine);

    let tdNomeProdotto = document.createElement('td');
    tdNomeProdotto.innerText = 'nomeProdotto';
    newTr.appendChild(tdNomeProdotto);

    let tdQuantita = document.createElement('td');
    tdQuantita.innerText = 'quantita';
    newTr.appendChild(tdQuantita);

    let tdPrezzo = document.createElement('td');
    tdPrezzo.innerText = 'prezzo';
    newTr.appendChild(tdPrezzo);
        } 
    }

    


generateTable()

/* ESERCIZIO 12
 Crea una funzione che aggiunga una riga alla tabella precedentemente creata e fornisca i dati necessari come parametri
*/

const addRow = function (immagine, nomeProdotto, quantita, prezzo) {
    let tbody = document.querySelector('tbody');
    let newTr = document.createElement('tr');

    let tdImmagine = document.createElement('td');
    tdImmagine.innerText = immagine;
    tdImmagine.setAttribute('class', 'immagine')
    newTr.appendChild(tdImmagine);

    let tdNomeProdotto = document.createElement('td');
    tdNomeProdotto.innerText = nomeProdotto;
    newTr.appendChild(tdNomeProdotto);

    let tdQuantita = document.createElement('td');
    tdQuantita.innerText = quantita;
    newTr.appendChild(tdQuantita);

    let tdPrezzo = document.createElement('td');
    tdPrezzo.innerText = prezzo;
    newTr.appendChild(tdPrezzo);

    tbody.appendChild(newTr);
 }

 addRow('nuovo1', 'nuovo2', 'nuovo3', 'nuovo4')

/* ESERCIZIO 14
Crea una funzione che nasconda le immagini della tabella quando eseguita
*/

const hideAllImages = function () {
    let trs = document.querySelectorAll('tbody tr');
    console.log(trs)
    trs.forEach(tr => {
        let td = tr.querySelector('.immagine');
        td.textContent = '';
    });
 }

 hideAllImages();

/* EXTRA ESERCIZIO 15
Crea una funzione che cambi il colore del h2 con id "changeMyColor" con un colore random ad ogni click ricevuto
*/
let h2Element = document.querySelector('#primoDiv h2');

      
      h2Element.addEventListener('click', changeColorWithRandom = function () {
        
      
        // Genera un colore casuale
        let coloreCasuale = '#' + Math.floor(Math.random()*16777215).toString(16);
      
        // Cambia il colore dell'h2
        h2Element.style.color = coloreCasuale;
      });
