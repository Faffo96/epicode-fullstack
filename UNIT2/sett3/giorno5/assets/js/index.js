const searchURL = 'https://striveschool-api.herokuapp.com/api/product/';
const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWVhZjBmZTJkN2IxMTAwMTkwZTcwZTEiLCJpYXQiOjE3MDk4OTU5MzUsImV4cCI6MTcxMTEwNTUzNX0.7kH7f98W__c4yWzcCT_rArdR_VnozbLwG1IVeb4hjVk';
let fetchResult = [];
const documentLoading = document.getElementById('loading');
const documentMain = document.querySelector('.main');

addEventListener('load', init);

async function init() { // Creates card elements based on the given array of items obtained in async and appends them to the cards container.
    try {
        fetchResult = await getDatabase("");
        createCards(fetchResult);
        console.log(fetchResult);
    } catch (error) {
        console.error('Si è verificato un errore durante l\'inizializzazione:', error);
    }
}

async function getDatabase(id) { // Retrieves data from the database using the provided id.
    try {
        const response = await fetch(searchURL + id, {
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

function createCards(array) { // Creates card elements based on the given array of items and appends them to the cards container.
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

function createCardElement(item) { // Creates a card element for a given item.
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

function createImageElement(imageUrl) { // Creates an image element with the provided image URL.
    const image = document.createElement('img');
    image.classList.add('card-img-top');
    image.src = imageUrl;
    image.alt = 'Product Image';
    return image;
}

function createCardBodyElement(name, description, item) { // Creates a card body element with the given name, description, and item.
    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');
    cardBody.appendChild(createTitleElement(name));
    cardBody.appendChild(createTextElement(description));
    cardBody.appendChild(createButtonContainerElement(item));
    return cardBody;
}

function createTitleElement(name) { // Creates a title element with the provided name.
    const title = document.createElement('h5');
    title.classList.add('card-title');
    title.textContent = name;
    return title;
}


function createTextElement(description) { // Creates a text element with the provided description.
    const text = document.createElement('p');
    text.classList.add('card-text');
    text.textContent = description;
    return text;
}

function createButtonContainerElement(item) { // Creates a button container element.
    const buttonContainer = document.createElement('div');
    buttonContainer.classList.add('d-flex', 'flex-column', 'justify-content-start');
    buttonContainer.appendChild(createDeleteButtonElement(item));
    buttonContainer.appendChild(createEditButtonElement(item));
    buttonContainer.appendChild(createInfoButtonElement(item));
    return buttonContainer;
}

function createDeleteButtonElement(item) { // Creates a delete button element.
    const deleteButtonDiv = document.createElement('div');
    deleteButtonDiv.classList.add('mb-2', 'btnZoom');
    const deleteButton = createButtonElement('Delete', 'btn-danger', 'onclick',`deleteRecord("${item._id}")`);
    deleteButtonDiv.appendChild(deleteButton);
    return deleteButtonDiv;
}

function createEditButtonElement(item) { // Creates an edit button element.
    const editButtonDiv = document.createElement('div');
    editButtonDiv.classList.add('mb-2', 'btnZoom');
    const editButton = createButtonElement('Edit', 'btn-warning', 'href' ,`addProduct.html?id=${item._id}`);
    editButtonDiv.appendChild(editButton);
    return editButtonDiv;
}

function createInfoButtonElement(item) { // Creates an info button element.
    const infoButtonDiv = document.createElement('div');
    infoButtonDiv.classList.add('btnZoom');
    const infoButton = createButtonElement('More info', 'btn-info', 'href' ,`product.html?id=${item._id}`);
    infoButtonDiv.appendChild(infoButton);
    return infoButtonDiv;
}

function createButtonElement(text, className, prop, value) { // Creates a button element with the provided text, class name, property, and value.
    const button = document.createElement('a');
    button.href = '#';
    button.setAttribute(prop, value);
    button.classList.add('btn', className);
    button.textContent = text;
    return button;
}

async function deleteRecord(id) { // Deletes a record from the database using the ID of the record.
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
        window.location.href = 'index.html';
    } catch (error) {
        console.error('Error deleting record:', error);
    }
}

const documentSearchBtn = document.getElementById('searchBtn'); // This code snippet attaches a click event listener to the 'searchBtn' element.
documentSearchBtn.addEventListener('click', function() {
    const documentSearchInput = document.getElementById('searchInput');
    const searchTerm = documentSearchInput.value.toLowerCase().trim(); // Retrieves the search input value, converts it to lowercase and trims any extra spaces.
    const searchResult = searchRecords(fetchResult, searchTerm); //Calls the searchRecords function with the fetchResult array and the search term as arguments.
    displaySearchResult(searchResult);
});

function searchRecords(array, searchTerm) { // Filters GET response based on a search term.
    const matchedItems = array.filter(item => item.name.toLowerCase().includes(searchTerm));
    return matchedItems;
}

function clearElement(element) { // Clears all child elements from the given element.
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

function displaySearchResult(searchResult) { // Displays the search result on the webpage.
    const cardsContainer = document.getElementById('cardsContainer');
    const documentSearchAlert = document.getElementById('searchAlert');
    const documentProductsH1 = document.getElementById('products');

    clearElement(cardsContainer); // Empty the card bin before viewing the results

    if (searchResult.length > 0) {
        createCards(searchResult);
        documentSearchAlert.style.display = 'none'; // Hide a "No Items Found" warning
    } else {
        documentSearchAlert.style.display = 'block'; // Displays a "No Items Found" warning
        documentProductsH1.style.display = 'none'; 
    }
}
