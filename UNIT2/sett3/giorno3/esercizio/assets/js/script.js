let libri;
let cart = [];

fetch('https://striveschool-api.herokuapp.com/books')
    .then(response => {
        return response.json();
    })
    .then(data => {
        libri = data;
        console.log(libri)
        fillCards(libri)
    })
    .catch(err => {
        console.log('Mio Errore');
    })


    function fillCards(arrayLibri) {
        const documentCardImg = document.getElementsByClassName('card-img-top');
        const documentCardTitle = document.getElementsByClassName('card-title');
        const documentCardCategory = document.getElementsByClassName('card-category');
        const documentCardPrice = document.getElementsByClassName('card-price');

        
        for (let i = 0; i < arrayLibri.length; i++) {
            const element = arrayLibri[i];
            documentCardImg[i].setAttribute('src', `${element.img}`);
            documentCardTitle[i].innerText = `${element.title}`;
            documentCardCategory[i].innerText = `${element.category}`;
            documentCardPrice[i].innerText = `${element.price}`;
        }
    }

    const documentBuy = document.getElementsByClassName('card-buy');
    for (let i = 0; i < documentBuy.length; i++) {
        const element = documentBuy[i];
        element.addEventListener('click', () => {
            const selectedBook = libri[i];
            console.log(selectedBook)
            cart.push(selectedBook);
            console.log('Elemento aggiunto al carrello:', selectedBook);
            
            const documentCartCardBody = document.getElementsByClassName('modal-body');
            let newDiv = document.createElement('div');
            newDiv.setAttribute('class', 'cart-card container-fluid');
            let newDivRow = document.createElement('div');
            newDivRow.setAttribute('class', 'row');
            let newImg = document.createElement('img');
            newImg.setAttribute('src', `${element.img}`);
            let newDivCardInfo = document.createElement('div');
            newDivCardInfo.setAttribute('class', '.cart-card-info col-6')
            let newInfoP = document.createElement('p');
            newInfoP.setAttribute('class', 'cart-card-info-title');
            let newInfoB = document.createElement('b');
            newInfoB.setAttribute('class', 'cart-card-info-price');
            
    })
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

