const documentContainerCardTrack = document.getElementById('containerCardTrack')
const documentArtistPageTitle = document.getElementById('artistPageTitle')
const documentArtistPagePlayer = document.getElementById('artistPagePlayer');
const documentContainerArtistCorrelate = document.getElementById('containerArtistCorrelate')


const urll = 'https://deezerdevs-deezer.p.rapidapi.com/search?q=geolier';

async function fillArtistPage() {
    clearMain();
    const mainArtist = document.getElementById('mainArtist');
    mainArtist.style.display = 'initial';
    removeAllChildren(documentContainerCardTrack)
    removeAllChildren(documentContainerArtistCorrelate)
    documentArtistPagePlayer.setAttribute('onclick', `togglePlayer('${currentObject.mp3}', this, '${currentObject.trackTitle}','${currentObject.artist}', '${currentObject.albumImg2}')`);
    setTimeout(() => {
        console.log(currentObject)
        documentArtistPageTitle.innerText = currentObject.artist;
        for (let i = 0; i <= 5; i++) {
            const element = currentObject.artistTracks[i];
            console.log(element);
            createCardTrack(element, i);
        }
        for (let i = 6; i <= 12; i++) {
            const element = currentObject.artistTracks[i];
            createCardArtistCorrelate(element, i < 10 ? false : true, documentContainerArtistCorrelate,i)
        }
    }, 100);
    const namee = currentObject.artist;
    const artistFeed = await getFetch('search', ('?q=' + namee));
        console.log(artistFeed);


}


function createCardTrack(element, i) {
   // Creazione degli elementi HTML
const li = document.createElement('li');
li.classList.add('list-style-position-inside', 'container-fluid', 'mb-3', 'cardHover', 'rounded', 'opacityHover');
li.setAttribute('onclick', `togglePlayer2('${element.preview}','${element.title}','${element.artist.name}', '${element.album.cover}')`);


const divRow = document.createElement('div');
divRow.classList.add('row', 'align-items-center');

const pIndex = document.createElement('p');
pIndex.classList.add('col-1', 'mb-0', 'opacity-50');
pIndex.textContent = i + 1;
/* pIndex.setAttribute('onclick', `togglePlayerIcon(this);`) */

const img = document.createElement('img');
img.classList.add('img-2', 'col-1', 'object-fit-cover');
img.src = element.album.cover;
img.alt = '';

const h5 = document.createElement('h5');
h5.classList.add('col-6', 'mb-0', 'lh-1');
h5.textContent = element.title;

const pViews = document.createElement('p');
pViews.classList.add('col-2', 'mb-0', 'opacity-50');
pViews.textContent = '45.826.239';

const divSvg = document.createElement('div');
divSvg.classList.add('col-2', 'd-flex', 'align-items-center');

const svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
svg.setAttribute('width', '24');
svg.setAttribute('height', '20');
svg.setAttribute('fill', 'none');
svg.setAttribute('stroke', 'currentColor');
svg.setAttribute('stroke-linecap', 'round');
svg.setAttribute('stroke-linejoin', 'round');
svg.setAttribute('stroke-width', '2');
svg.setAttribute('viewBox', '0 0 24 24');
svg.classList.add('bg-custom-3', 'border-custom', 'me-5', 'border-custom-2');

const path = document.createElementNS('http://www.w3.org/2000/svg', 'path');
path.setAttribute('d', 'M3.343 7.778a4.5 4.5 0 0 1 7.339-1.46L12 7.636l1.318-1.318a4.5 4.5 0 1 1 6.364 6.364L12 20.364l-7.682-7.682a4.501 4.501 0 0 1-.975-4.904Z');

const pDuration = document.createElement('p');
pDuration.classList.add('mb-0', 'opacity-50');
pDuration.textContent = secondsToTime(element.duration, ':', '');

// Aggiunta degli elementi al DOM
divSvg.appendChild(svg);
svg.appendChild(path);

divRow.appendChild(pIndex);
divRow.appendChild(img);
divRow.appendChild(h5);
divRow.appendChild(pViews);
divSvg.appendChild(pDuration);
divRow.appendChild(divSvg);

li.appendChild(divRow);

// Aggiunta dell'elemento li al DOM
documentContainerCardTrack.appendChild(li);


function createCard3(albumData, isNone, targetElement) {
    console.log(albumData)
    const outerDiv = document.createElement('div');
    outerDiv.classList.add('card3', 'd-flex', 'm-1');
    outerDiv.onclick = function () {
        fillAlbumPage(this);
        saveCurrentObject(albumData);
    };
    if (isNone) {
        outerDiv.classList.add('d-xxl-none', 'card-3XL');
    }

    const innerDiv = document.createElement('div');
    innerDiv.classList.add('card', 'p-2', 'cardHover');
    innerDiv.style.width = '191px';
    innerDiv.style.height = '260px';

    const imageDiv = document.createElement('div');
    imageDiv.classList.add('text-center');

    const img = document.createElement('img');
    img.setAttribute('src', albumData.cover_medium);
    img.setAttribute('alt', '...');
    img.classList.add('object-fit-cover');
    img.style.width = '161px';
    img.style.height = '161px';
    imageDiv.appendChild(img);

    const playIcon = document.createElement('i');
    playIcon.classList.add('bi', 'bi-play-circle-fill', 'icon-size-bigger', 'mx-3', 'playerCard3');
    playIcon.setAttribute('onclick', `togglePlayer('${albumData.tracks.data[0].preview}', this, '${albumData.tracks.data[0].title}','${albumData.artist.name}', '${albumData.cover}' ); event.stopPropagation();`);
    playIcon.style.position = 'absolute';
    playIcon.style.transform = 'translate(-94px, 71px)';
    playIcon.style.fontSize = '3.5rem';
    imageDiv.appendChild(playIcon);

    const textDiv = document.createElement('div');
    textDiv.classList.add('mt-2', 'ms-1');

    const title = document.createElement('h5');
    title.classList.add('card-title', 'text-white', 'fs-6');
    title.textContent = albumData.title;
    textDiv.appendChild(title);

    const artist = document.createElement('p');
    artist.classList.add('card-text', 'text-white-50', 'text-size-2');
    artist.textContent = albumData.artist.name;
    textDiv.appendChild(artist);

    innerDiv.appendChild(imageDiv);
    innerDiv.appendChild(textDiv);

    outerDiv.appendChild(innerDiv);

    targetElement.appendChild(outerDiv);
}
}

function createCardArtistCorrelate(albumData, isNone, targetElement, i) {
    console.log(albumData)
    const outerDiv = document.createElement('div');
    outerDiv.classList.add('card3', 'd-flex', 'm-1');
    outerDiv.onclick = function () {
        saveCurrentObject(albumData);
    };
    if (isNone) {
        outerDiv.classList.add('d-xxl-none', 'card-3XL');
    }

    const innerDiv = document.createElement('div');
    innerDiv.classList.add('card', 'p-2', 'cardHover');
    innerDiv.style.width = '191px';
    innerDiv.style.height = '260px';

    const imageDiv = document.createElement('div');
    imageDiv.classList.add('text-center');

    const img = document.createElement('img');
    img.setAttribute('src', albumData.album.cover);
    img.setAttribute('alt', '...');
    img.classList.add('object-fit-cover');
    img.style.width = '161px';
    img.style.height = '161px';
    imageDiv.appendChild(img);

    const playIcon = document.createElement('i');
    playIcon.classList.add('bi', 'bi-play-circle-fill', 'icon-size-bigger', 'mx-3', 'playerCard3');
    playIcon.setAttribute('onclick', `togglePlayer('${albumData.preview}', this, '${albumData.title}','${albumData.artist.name}', '${albumData.album.cover}' ); event.stopPropagation();`);
    playIcon.style.position = 'absolute';
    playIcon.style.transform = 'translate(-94px, 71px)';
    playIcon.style.fontSize = '3.5rem';
    imageDiv.appendChild(playIcon);

    const textDiv = document.createElement('div');
    textDiv.classList.add('mt-2', 'ms-1');

    const title = document.createElement('h5');
    title.classList.add('card-title', 'text-white', 'fs-6');
    title.textContent = albumData.title;
    textDiv.appendChild(title);

    const artist = document.createElement('p');
    artist.classList.add('card-text', 'text-white-50', 'text-size-2');
    artist.textContent = albumData.artist.name;
    textDiv.appendChild(artist);

    innerDiv.appendChild(imageDiv);
    innerDiv.appendChild(textDiv);

    outerDiv.appendChild(innerDiv);

    targetElement.appendChild(outerDiv);
}
