let libri;
let cart = [];



addEventListener('load', init())

function init() {
    fetch('https://striveschool-api.herokuapp.com/books')
        .then(response => {
            return response.json();
        })
        .then(data => {
            libri = data;
            console.log(libri)
            fillCards(libri);
            addToCart();
        })
        .catch(err => {
            console.log('Mio Errore');
        })
}

function fillCards(arrayLibri) {
    const bookList = document.querySelector('.bookList');

    for (let i = 0; i < arrayLibri.length; i++) {
        const element = arrayLibri[i];

        // Creazione della card
        const cardDiv = document.createElement('div');
        cardDiv.classList.add('card');
        cardDiv.setAttribute("id", element.asin);

        const img = document.createElement('img');
        img.classList.add('card-img-top');
        img.setAttribute('src', element.img);
        img.setAttribute('alt', '...');
        cardDiv.appendChild(img);

        const cardBody = document.createElement('div');
        cardBody.classList.add('card-body');
        cardDiv.appendChild(cardBody);

        const title = document.createElement('h5');
        title.classList.add('card-title');
        title.innerText = element.title;
        cardBody.appendChild(title);

        const category = document.createElement('p');
        category.classList.add('card-category');
        category.innerText = element.category;
        cardBody.appendChild(category);

        const price = document.createElement('p');
        price.classList.add('card-price');
        price.innerText = element.price + '€';
        cardBody.appendChild(price);

        const compraBtn = document.createElement('a');
        compraBtn.classList.add('card-buy', 'btn', 'btn-danger');
        compraBtn.setAttribute('href', '#');
        compraBtn.innerText = 'Compra Ora';
        cardBody.appendChild(compraBtn);

        const scartaBtn = document.createElement('a');
        scartaBtn.classList.add('btn', 'btn-outline-danger');
        scartaBtn.setAttribute('href', '#');
        scartaBtn.innerText = 'Scarta';
        cardBody.appendChild(scartaBtn);

        bookList.appendChild(cardDiv);
    }
}


function addToCart() {
    const documentBuy = document.getElementsByClassName('card-buy');
    for (let i = 0; i < documentBuy.length; i++) {
        const element = documentBuy[i];
        element.addEventListener('click', () => {
            const selectedBook = libri[i];
            console.log(selectedBook);
            cart.push(selectedBook);
            console.log('Elemento aggiunto al carrello:', selectedBook);

            const documentModalBody = document.querySelector('.modal-body');

            // Creazione della card del carrello
            const newDiv = document.createElement('div');
            newDiv.classList.add('cart-card', 'container-fluid');
            newDiv.setAttribute('id', `${selectedBook.asin}`);

            const newDivRow = document.createElement('div');
            newDivRow.classList.add('row');

            const newImg = document.createElement('img');
            newImg.setAttribute('src', `${selectedBook.img}`);
            newImg.classList.add('imgSize');

            const newDivCardInfo = document.createElement('div');
            newDivCardInfo.classList.add('cart-card-info', 'col-6');

            const newInfoP = document.createElement('p');
            newInfoP.classList.add('cart-card-info-title');
            newInfoP.innerText = selectedBook.title;

            const newInfoB = document.createElement('b');
            newInfoB.classList.add('cart-card-info-price');
            newInfoB.innerText = `${selectedBook.price}€`;

            const newDivCartCardBtn = document.createElement('div');
            newDivCartCardBtn.classList.add('cart-card-info-button', 'col-3');

            const newButton = document.createElement('button');
            newButton.classList.add('cart-card-info-delete', 'btn', 'btn-danger');

            const newIcon = document.createElement('i');
            newIcon.classList.add('bi', 'bi-trash3');

            newButton.appendChild(newIcon);
            newDivCardInfo.appendChild(newInfoP);
            newDivCardInfo.appendChild(newInfoB);
            newDivRow.appendChild(newImg);
            newDivRow.appendChild(newDivCardInfo);
            newDivRow.appendChild(newDivCartCardBtn);
            newDivCartCardBtn.appendChild(newButton);
            newDiv.appendChild(newDivRow);
            documentModalBody.appendChild(newDiv);
        });
    }
}






/* {
    0:
    asin: "1940026091"
    category: "scifi"
    img: "https://images-na.ssl-images-amazon.com/images/I/91xrEMcvmQL.jpg"
    price: 7.81
    title: "Pandemic (The Extinction Files, Book 1)"
}

{
    0:
    asin: "1940026091"
    category: "scifi"
    img: "https://images-na.ssl-images-amazon.com/images/I/91xrEMcvmQL.jpg"
    price: 7.81
    title: "Pandemic (The Extinction Files, Book 1)"
}

{
    0:
    asin: "1940026091"
    category: "scifi"
    img: "https://images-na.ssl-images-amazon.com/images/I/91xrEMcvmQL.jpg"
    price: 7.81
    title: "Pandemic (The Extinction Files, Book 1)"
}

{0:
    asin: "1940026091"
    category: "scifi"
    img: "https://images-na.ssl-images-amazon.com/images/I/91xrEMcvmQL.jpg"
    price: 7.81
    title: "Pandemic (The Extinction Files, Book 1)"
        } */

