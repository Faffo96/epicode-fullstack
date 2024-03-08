const searchURL = 'https://striveschool-api.herokuapp.com/api/product/';
const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZjBmZTJkN2IxMTAwMTkwZTcwZTEiLCJpYXQiOjE3MDk4OTU5MzUsImV4cCI6MTcxMTEwNTUzNX0.7kH7f98W__c4yWzcCT_rArdR_VnozbLwG1IVeb4hjVk';
let fetchResult = [];
addEventListener('load', init);


async function init() {
    try {
        const fetchResult = await getDatabase(""); // Ottieni i dati dal database in modo asincrono
        createCards(fetchResult); // Crea le carte usando i dati ottenuti
        console.log(fetchResult); // Stampa i dati ottenuti dalla richiesta
    } catch (error) {
        console.error('Si è verificato un errore durante l\'inizializzazione:', error);
        // Gestisci eventuali errori qui
    }
}



async function getDatabase(slug) {
    try {
        const response = await fetch(searchURL + slug, {
            headers: {
                'Authorization': 'Bearer ' + API_KEY
            }
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        fetchResult = await response.json();
        return fetchResult;
    } catch (error) {
        throw error;
    }
}

async function createCards(array) {
    try {
        const cardsContainer = document.getElementById('cardsContainer');
        array.forEach(item => {
            const card = document.createElement('div');
            card.classList.add('card');
            const image = document.createElement('img');
            image.classList.add('card-img-top');
            image.src = item.imageUrl; // Aggiorna per accedere alla proprietà corretta dell'immagine
            image.alt = 'Product Image';
            const cardBody = document.createElement('div');
            cardBody.classList.add('card-body');
            const title = document.createElement('h5');
            title.classList.add('card-title');
            title.textContent = item.name; // Aggiorna per accedere alla proprietà corretta del nome
            const text = document.createElement('p');
            text.classList.add('card-text');
            text.textContent = item.description; // Aggiorna per accedere alla proprietà corretta della descrizione
            const buttonContainer = document.createElement('div');
            buttonContainer.classList.add('d-flex', 'flex-column', 'justify-content-start');
            const editButtonDiv = document.createElement('div');
            editButtonDiv.classList.add('mb-2'); // Aggiungi la classe per la distanza inferiore tra i pulsanti
            const editButton = document.createElement('a');
            editButton.href = `editProduct.html?id=${item._id}`; // Aggiorna per accedere alla proprietà corretta dell'id
            editButton.classList.add('btn', 'btn-warning');
            editButton.textContent = 'Edit';
            const infoButtonDiv = document.createElement('div');
            const infoButton = document.createElement('a');
            infoButton.href = '#';
            infoButton.classList.add('btn', 'btn-info');
            infoButton.textContent = 'More info';
            infoButtonDiv.appendChild(infoButton);
            editButtonDiv.appendChild(editButton);
            buttonContainer.appendChild(editButtonDiv);
            buttonContainer.appendChild(infoButtonDiv);
            cardBody.appendChild(title);
            cardBody.appendChild(text);
            cardBody.appendChild(buttonContainer);
            card.appendChild(image);
            card.appendChild(cardBody);
            cardsContainer.appendChild(card);
        });
    } catch (error) {
        console.error("Error creating cards:", error);
    }
}
