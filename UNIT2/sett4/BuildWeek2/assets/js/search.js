search.addEventListener('click', (e) => {
    e.preventDefault();
    const mainSearch = document.getElementById('mainSearch')
    const searchBar2 = document.getElementById('searchBar2')
    const showMain = document.getElementById('showMain')
    showMain.style.display = 'none';
    mainSearch.style.display = 'inline';
    searchBar2.style.display = 'inline';
})


async function createCardSearch() {
    for (let i = 0; i < algorithmUserFeed.length; i++) {
        const albumData = await getFetch('album', algorithmUserFeed[i]/* , keys[0] */);
        createCard4(albumData, i);
    }
}

function createCard4(albumData, index) {
    // Creazione dell'elemento div con le classi specificate
    const cardDiv = document.createElement('div');
    cardDiv.classList.add('rounded-3', 'cardGener', 'mb-3');
    // Assegna un colore di sfondo dalla lista di colori in base all'indice della card
    cardDiv.style.backgroundColor = colors[index % colors.length];
    


    // Creazione dell'elemento h4 con la classe specificata e il testo "Musica"
    const h4Element = document.createElement('h4');
    h4Element.classList.add('mt-2', 'ms-1', 'text-white', 'circular-regular');
    h4Element.textContent = albumData.artist.name;

    // Creazione dell'elemento img con la classe specificata, l'attributo src e l'attributo alt
    const imgElement = document.createElement('img');
    imgElement.classList.add('shadow');
    imgElement.setAttribute('src', albumData.artist.picture);
    imgElement.setAttribute('alt', '');

    // Aggiunta degli elementi figlio al div principale
    cardDiv.appendChild(h4Element);
    cardDiv.appendChild(imgElement);

    // Aggiunta del div principale al genitore specificato
    documentCard4.appendChild(cardDiv);
}

function fillSearchPage() {
    clearMain();

}