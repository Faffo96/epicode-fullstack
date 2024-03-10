const searchURL = 'https://striveschool-api.herokuapp.com/api/product/';
const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZjBmZTJkN2IxMTAwMTkwZTcwZTEiLCJpYXQiOjE3MDk4OTU5MzUsImV4cCI6MTcxMTEwNTUzNX0.7kH7f98W__c4yWzcCT_rArdR_VnozbLwG1IVeb4hjVk';
let fetchResult = [];
const documentLoading = document.getElementById('loading');
const documentMain = document.querySelector('.main');

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
        documentLoading.style.display = "none";
        documentMain.style.display = "block";
        return fetchResult;
    } catch (error) {
        throw error;
    }
}

function createCards(array) {
    try {
        const cardsContainer = document.getElementById('cardsContainer');
        array.forEach(item => {
            const card = createCardElement(item);
            cardsContainer.appendChild(card);
        });
    } catch (error) {
        console.error("Error creating cards:", error);
    }
}

function createCardElement(item) {
    const card = document.createElement('div');
    card.classList.add('card');
    card.appendChild(createImageElement(item.imageUrl));
    
    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');
    
    cardBody.appendChild(createTitleElement(item.name));
    
    if (item.description) {
        cardBody.appendChild(createTextElement(item.description));
    } else {
        cardBody.appendChild(createTextElement("No description available"));
    }
    
    cardBody.appendChild(createButtonContainerElement(item));
    
    card.appendChild(cardBody);
    
    return card;
}
function createImageElement(imageUrl) {
    const image = document.createElement('img');
    image.classList.add('card-img-top');
    image.src = imageUrl;
    image.alt = 'Product Image';
    return image;
}

function createCardBodyElement(name, description, item) {
    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');
    cardBody.appendChild(createTitleElement(name));
    cardBody.appendChild(createTextElement(description));
    cardBody.appendChild(createButtonContainerElement(item));
    return cardBody;
}


function createTitleElement(name) {
    const title = document.createElement('h5');
    title.classList.add('card-title');
    title.textContent = name;
    return title;
}

function createTextElement(description) {
    const text = document.createElement('p');
    text.classList.add('card-text');
    text.textContent = description;
    return text;
}


function createButtonContainerElement(item) { // Aggiungi item come parametro
    const buttonContainer = document.createElement('div');
    buttonContainer.classList.add('d-flex', 'flex-column', 'justify-content-start');
    buttonContainer.appendChild(createDeleteButtonElement(item)); // Passa l'oggetto item come parametro
    buttonContainer.appendChild(createEditButtonElement(item)); // Passa l'oggetto item come parametro
    buttonContainer.appendChild(createInfoButtonElement(item)); // Passa l'oggetto item come parametro
    return buttonContainer;
}

function createDeleteButtonElement(item) { // Aggiungi item come parametro
    const deleteButtonDiv = document.createElement('div');
    deleteButtonDiv.classList.add('mb-2');
    const deleteButton = createButtonElement('Delete', 'btn-danger', 'onclick',`deleteRecord("${item._id}")`);
    deleteButtonDiv.appendChild(deleteButton);
    return deleteButtonDiv;
}

function createEditButtonElement(item) { // Aggiungi item come parametro
    const editButtonDiv = document.createElement('div');
    editButtonDiv.classList.add('mb-2');
    const editButton = createButtonElement('Edit', 'btn-warning', 'href' ,`addProduct.html?id=${item._id}`);
    editButtonDiv.appendChild(editButton);
    return editButtonDiv;
}

function createInfoButtonElement(item) { // Aggiungi item come parametro
    const infoButtonDiv = document.createElement('div');
    const infoButton = createButtonElement('More info', 'btn-info', 'href' ,`product.html?id=${item._id}`);
    infoButtonDiv.appendChild(infoButton);
    return infoButtonDiv;
}


function createButtonElement(text, className, prop, value) {
    const button = document.createElement('a');
    button.href = '#';
    button.setAttribute(prop, value);
    button.classList.add('btn', className);
    button.textContent = text;
    return button;
}

async function deleteRecord(id) {
    try {
        const response = await fetch(searchURL + id, {
            method: 'DELETE',
            headers: {
                'Authorization': 'Bearer ' + API_KEY
            }
        });
        if (!response.ok) {
            throw new Error('Failed to delete record');
        }
        console.log('Record deleted successfully');

        // Reindirizza l'utente alla pagina index.html
        window.location.href = 'index.html';
    } catch (error) {
        console.error('Error deleting record:', error);
        // Gestisci eventuali errori qui
    }
}