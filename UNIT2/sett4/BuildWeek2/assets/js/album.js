const documentAlbumTrackList = document.querySelector('.album-trackList');
const documentAlbumPageTitle = document.getElementById('albumPageTitle');
const documentAlbumPageArtist = document.getElementById('albumPageArtist');
const documentAlbumPageYear = document.getElementById('albumPageYear');
const documentAlbumPageTracksQuantity = document.getElementById('albumPageTracksQuantity');
const documentAlbumPageAlbumDuration = document.getElementById('albumPageAlbumDuration');
const documentAlbumPageAlbumImg = document.getElementById('albumPageAlbumImg');
const documentAlbumPagePlayer = document.getElementById('albumPagePlayer')



function fillAlbumPage(id) {
    clearMain();
    const mainAlbum = document.getElementById('mainAlbum');
    mainAlbum.style.display = 'initial';
    removeAllChildren(documentAlbumTrackList)
    // Ritarda l'esecuzione del codice all'interno di setTimeout di 1 secondo
    setTimeout(() => {
        documentAlbumPagePlayer.setAttribute('onclick', `togglePlayer('${currentObject.mp3}', this, '${currentObject.trackTitle}','${currentObject.artist}', '${currentObject.albumImg}')`);
        documentAlbumPageAlbumImg.setAttribute('src', currentObject.albumImg);
        documentAlbumPageTitle.innerHTML = currentObject.albumTitle; 
        documentAlbumPageArtist.innerHTML = currentObject.artist;
        documentAlbumPageYear.innerHTML = currentObject.releaseDate;
        documentAlbumPageTracksQuantity.innerHTML = `${currentObject.tracksData.length} brani`;
        documentAlbumPageAlbumDuration.innerHTML = secondsToTime(currentObject.albumDuration, "min ", "sec.");
        const tracksQuantity = currentObject.tracksData.length;
        for (let i = 0; i < tracksQuantity; i++) {
            const element = currentObject.tracksData[i];
            console.log(element)
            createCardAlbum(element, i);
        }
        
    }, 1000); // 100 millisecondi equivalgono a 1 secondo
}



// Creazione delle mini cards 
function createCardAlbum(albumData, i) {


    // Creazione del div principale
    const mainDiv = document.createElement('div');
    mainDiv.classList.add('d-flex', 'justify-content-between', 'cardHover', 'ps-2', 'py-2', 'rounded', 'align-items-center', 'opacityHover');
    mainDiv.setAttribute('onclick', `togglePlayer2('${albumData.preview}', '${albumData.title}','${albumData.artist.name}', '${albumData.album.cover}')`);

    // Creazione del primo div interno
    const innerDiv1 = document.createElement('div');
    innerDiv1.classList.add('d-flex', 'align-items-center');

    // Creazione del primo paragrafo
    const p1 = document.createElement('p');
    p1.classList.add('id-track', 'me-4', 'mb-0', 'text-size-3', 'opacity-50');
    p1.textContent = i + 1;
    innerDiv1.appendChild(p1);

    // Creazione del secondo div interno
    const innerDiv2 = document.createElement('div');

    // Creazione dell'elemento h3
    const h3 = document.createElement('h3');
    h3.classList.add('text-size-3', 'mb-0');
    h3.textContent = albumData.title;
    innerDiv2.appendChild(h3);

    // Creazione del secondo paragrafo
    const p2 = document.createElement('p');
    p2.classList.add('text-size-2', 'fw-normal', 'm-0', 'opacity-50');
    p2.textContent = albumData.artist.name;
    innerDiv2.appendChild(p2);

    innerDiv1.appendChild(innerDiv2);

    // Aggiunta del primo div interno al div principale
    mainDiv.appendChild(innerDiv1);

    // Creazione del secondo div interno
    const innerDiv3 = document.createElement('div');
    innerDiv3.classList.add('d-flex', 'align-items-center');

    // Creazione dell'elemento svg
    const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svg.setAttribute('width', '24');
    svg.setAttribute('height', '20');
    svg.setAttribute('fill', 'none');
    svg.setAttribute('stroke', 'currentColor');
    svg.setAttribute('stroke-linecap', 'round');
    svg.setAttribute('stroke-linejoin', 'round');
    svg.setAttribute('stroke-width', '2');
    svg.setAttribute('viewBox', '0 0 24 24');
    svg.classList.add('bg-custom-3', 'border-custom', 'me-5', 'border-custom-2');

    const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
    path.setAttribute('d', 'M3.343 7.778a4.5 4.5 0 0 1 7.339-1.46L12 7.636l1.318-1.318a4.5 4.5 0 1 1 6.364 6.364L12 20.364l-7.682-7.682a4.501 4.501 0 0 1-.975-4.904Z');
    svg.appendChild(path);
    innerDiv3.appendChild(svg);

    // Creazione del terzo paragrafo
    const p3 = document.createElement('p');
    p3.classList.add('text-size-2', 'me-5', 'mb-0', 'opacity-50');
    p3.textContent = secondsToTime(albumData.duration);
    innerDiv3.appendChild(p3);

    // Aggiunta del secondo div interno al div principale
    mainDiv.appendChild(innerDiv3);

    // Aggiunta del div principale al documento
    documentAlbumTrackList.appendChild(mainDiv);

}

