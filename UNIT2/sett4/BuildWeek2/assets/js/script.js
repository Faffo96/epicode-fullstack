const player = document.getElementById('player');
const btnPlay = document.getElementById('btnPlay');
const volume = document.getElementById('volume');
const search = document.getElementById('search');
const documentCard1 = document.getElementById('containerCard1')
const documentCard2 = document.getElementById('containerCard2')
const documentCard3Box = document.getElementById('containerCard3')
const documentCard3_2Box = document.getElementById('containerCard3_2')
const documentCard4 = document.getElementById('containerCard4')
const documentPlayerBtns = document.getElementsByClassName('bi-play-circle-fill');
const documentPlayerImg = document.getElementById('playerImg');
const documentPlayerArtist = document.getElementById('playerArtist');
const documentPlayerTitle = document.getElementById('playerTitle');
const documentContainerMoreBy = document.getElementById('containerMoreBy')
let albumData;
let currentObject = {};
let history = [];



/* const keys = [
    'c776300d22mshd3bea6709348bbfp1dc7c8jsn11b2c60ef719',
    '835a0e9802msh157b5b5d7ff5689p1402cfjsna720f5edd6fa',
    'c81611be3dmshc4dfc559f330731p135b38jsn1f885d01cc8',
    'e24f190dfamsh3b4e04c6923511ep1e6fbdjsn6a9e95f0b3d6',
    '0e88591ab3mshfb4bba544a6c2b8p1d513ejsn6b9bf8b7580e',
    '0a58588e6cmsh61297e891fcc988p1ef45bjsn9be9ca038584'
  ]; */


idBraniPreferiti = [];
let colors = ['#DC148C', '#8400E7', '#27856A', '#0D73EC', '#777777', '#E13300'];
let algorithmUserFeed = [
    '426683117',
    '13420001',
    '60357832',
    '13943974',
    '13994766',
    '100111992',
    '74308932',
    '309377597',
    '557619402',
    '517196372',
    '61419582',
    '446034935',
    '171945282',
    '124513502',
]

let algorithmUserFeed2 = [
    'geolier',
    'Rose Villain',
    'co sang',
    'lee "scratch" perry',
    'sfera ebbasta',
    'luche',
    'articolo31',
    'lazza',
]

const getFetch = async (category, id /* key */) => {
    const url = `https://deezerdevs-deezer.p.rapidapi.com/${category}/${id}`;
    const options = {
        method: 'GET',
        headers: {
            'X-RapidAPI-Key': 'c776300d22mshd3bea6709348bbfp1dc7c8jsn11b2c60ef719',
            'X-RapidAPI-Host': 'deezerdevs-deezer.p.rapidapi.com'
        }
    };

    try {
        const response = await fetch(url, options);
        return result = await response.json();
    } catch (error) {
        console.error(error);
    }
};


function createCard1(albumData) {
    const outerDiv = document.createElement('div');
    outerDiv.classList.add('d-flex', 'cardHover', 'p-2', 'rounded');
    outerDiv.onclick = function () {
        console.log(albumData)
        saveCurrentArtist(albumData);
        fillArtistPage();          
    };


    const imageDiv = document.createElement('div');
    imageDiv.classList.add('me-3');

    const img = document.createElement('img');
    img.classList.add('rounded-5');
    img.setAttribute('src', albumData.data[0].artist.picture);
    img.setAttribute('alt', 'Artista');
    imageDiv.appendChild(img);

    const textDiv = document.createElement('div');

    const namePara = document.createElement('p');
    namePara.classList.add('m-0', 'text-white');
    namePara.textContent = albumData.data[0].artist.name;
    textDiv.appendChild(namePara);

    const typePara = document.createElement('p');
    typePara.classList.add('m-0', 'text-white-50');
    typePara.textContent = 'Artista';
    textDiv.appendChild(typePara);

    outerDiv.appendChild(imageDiv);
    outerDiv.appendChild(textDiv);

    documentCard1.appendChild(outerDiv);
}


function createCard2(artistData) {
    const newDiv = document.createElement('div');
    newDiv.classList.add('col-3', 'px-3', 'circular-regular');
    newDiv.onclick = function () {
        saveCurrentArtist(artistData);
        fillArtistPage();
    };
    const div = document.createElement('div');
    div.classList.add('card2', 'd-flex', 'align-items-center', 'bgBanner', 'rounded-2', 'p-0', 'mt-2', 'w-20');
    newDiv.appendChild(div);

    const img = document.createElement('img');
    img.setAttribute('src', artistData.data[0].artist.picture_small);
    img.setAttribute('width', '80px');
    img.setAttribute('height', '80px');
    img.classList.add('ms-0', 'rounded-start-2');
    div.appendChild(img);

    const innerDiv = document.createElement('div');
    innerDiv.classList.add('d-flex', 'justify-content-between', 'align-items-center', 'w-100');
    div.appendChild(innerDiv);

    const p = document.createElement('p');
    p.classList.add('m-0', 'ms-2');
    p.textContent = artistData.data[0].artist.name;
    innerDiv.appendChild(p);

    const playIcon = document.createElement('i');
    playIcon.classList.add('bi', 'bi-play-circle-fill', 'icon-size-bigger', 'mx-3', 'playerCard2', 'custom-4');
    playIcon.setAttribute('onclick', `togglePlayer('${artistData.data[0].preview}', this, '${artistData.data[0].title}','${artistData.data[0].artist.name}', '${artistData.data[0].album.cover}'); event.stopPropagation();`);
    innerDiv.appendChild(playIcon);

    documentCard2.appendChild(newDiv);
}

async function saveCurrentObject(object) {
    currentObject = {
        albumTitle: await object.title,
        artist: await object.artist.name,
        albumImg: await object.cover,
        releaseDate: await object.release_date,
        tracksQuantity: await object.nb_tracks,
        albumDuration: await object.duration,
        tracksData: await object.tracks.data,
        mp3: await object.tracks.data[0].preview,
        trackTitle: await object.tracks.data[0].title,
    };
    history.push(currentObject);
}

function saveCurrentArtist(object) {
    console.log(object)
    currentObject = {
        artist: object.data[0].artist.name,
        albumImg: object.data[0].artist.picture_small,
        albumImg2: object.data[0].album.cover,
        trackDuration: object.data[0].duration,
        artistTracks: object.data,
        mp3: object.data[0].preview,
        trackTitle: object.data[0].title,
    }
}



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


function clearMain() {
    const showMain = document.getElementById('showMain');
    showMain.style.display = 'none';

}


async function fillIndexPage() {
    const promises = [];
    for (let i = 0; i < algorithmUserFeed2.length; i++) {
        promises.push(getFetch('search', ('?q=' + algorithmUserFeed2[i])));
    }
    const albumsData = await Promise.all(promises);
    for (let i = 0; i < albumsData.length; i++) {
        createCard1(albumsData[i]);
    }
    // Creazione delle card di tipo 2
    for (let i = 0; i < 8; i++) {
        albumData = await getFetch('search', ('?q=' + algorithmUserFeed2[i]), '' /* , keys[5] */);
        createCard2(albumData);
    }

    // Fetch dei dati degli album e popolamento delle card di tipo 3
    for (let i = 0; i < algorithmUserFeed.length - 7; i++) {
        albumData = await getFetch('album', algorithmUserFeed[i], '' /* , keys[6] */);
        createCard3(albumData, i < 5 ? false : true, documentCard3Box);

    }

    for (let i = 0; i < algorithmUserFeed.length - 7; i++) {
        albumData = await getFetch('album', algorithmUserFeed[i], '' /* , keys[6] */);
        createCard3(albumData, i < 5 ? false : true, documentContainerMoreBy);
    }

    for (let i = 7; i < algorithmUserFeed.length; i++) {
        albumData = await getFetch('album', algorithmUserFeed[i], '' /* , keys[4] */);
        createCard3(albumData, i < 12 ? false : true, documentCard3_2Box);
    }
}

window.addEventListener('load', init);

function init() {
    createCardSearch();
    fillIndexPage();
}

function playPause() {
    if (player.paused) { //check audio is playing
        player.play();

    } else {
        player.pause();
    }
}

player.addEventListener('play', function () {
    btnPlay.classList.remove('bi-play-circle-fill');
    btnPlay.classList.add('bi-pause-circle-fill');
});

player.addEventListener('pause', function () {
    btnPlay.classList.add('bi-play-circle-fill');
    btnPlay.classList.remove('bi-pause-circle-fill');
});



function togglePlayer(albumPreview, element, albumTitle, albumArtist, albumImg) {
    const player = document.getElementById('player');
    documentPlayerImg.setAttribute('src', albumImg);
    documentPlayerTitle.innerText = albumTitle;
    documentPlayerArtist.innerText = albumArtist;
    if (player.src !== albumPreview) {
        player.src = albumPreview;
        player.load();
        player.play();
        element.classList.remove('bi-play-circle-fill');
        element.classList.add('bi-pause-circle-fill');

    } else {
        if (player.paused) {
            player.play();
            player.addEventListener('play', function () {
                element.classList.remove('bi-play-circle-fill');
                element.classList.add('bi-pause-circle-fill');
            });
        } else {
            player.pause();
            player.addEventListener('pause', function () {
                element.classList.add('bi-play-circle-fill');
                element.classList.remove('bi-pause-circle-fill');
            });
        }
    }
}

function togglePlayer2(albumPreview, albumTitle, albumArtist, albumImg) {
    const player = document.getElementById('player');
    documentPlayerImg.setAttribute('src', albumImg);
    documentPlayerTitle.innerText = albumTitle;
    documentPlayerArtist.innerText = albumArtist;
    if (player.src !== albumPreview) {
        player.src = albumPreview;
        player.load();
        player.play();
      
    } else {
        if (player.paused) {
            player.play();
            player.addEventListener('play', function () {
            });
        } else {
            player.pause();
            player.addEventListener('pause', function () {
              
            });
        }
    }
}

function secondsToTime(duration, minSeparator = ":", secSeparator = "") {
    const minutes = parseInt(duration / 60);
    let seconds = parseInt(duration % 60);

    if (seconds < 10) {
        seconds = "0" + seconds;
    }
    return minutes + minSeparator + seconds + secSeparator;
}

// Esempi di utilizzo:
console.log(secondsToTime(120)); // Output: "2:00"
console.log(secondsToTime(65, "m", "s")); // Output: "1m05s"


player.addEventListener("timeupdate", (event) => {

    const curTime = document.getElementById('currentTime');
    const remainingTime = document.getElementById('remainingTime');

    const currentTime = event.currentTarget.currentTime;
    const duration = event.currentTarget.duration;
    if (!isNaN(duration)) {
        let remTime = duration - currentTime;

        curTime.innerText = secondsToTime(currentTime, ":", "");
        if (remTime < 0) {
            remTime = 0; // Impostiamo il tempo rimanente a zero se è negativo
        }
        remainingTime.innerText = secondsToTime(remTime);
    }

    // Regoliamo il volume
    const inputRange = document.getElementById('inputRange').value;
    if (inputRange > 1) {
        event.currentTarget.volume = inputRange / 100;
    }

    // Visualizziamo la barra della durata
    const barraDurata = document.querySelector('.elapsed');
    const perc = currentTime / duration * 100;
    barraDurata.style.width = perc + "%";
});

function mutaAudio() {
    if (player.muted) {
        player.muted = false;
    } else {
        player.muted = true;
    }
}

const barraGrigia = document.querySelector(".time");
barraGrigia.addEventListener("click", function (e) {
    const offset = e.offsetX;
    const perc = (offset / barraGrigia.offsetWidth) * 100;
    const barraVerde = document.querySelector(".elapsed");
    barraVerde.style.width = perc + "%";

    const durata = player.duration;
    const nuovaDurata = (durata * perc) / 100;
    player.currentTime = nuovaDurata;
});

function showMain() {
    const mainSearch = document.getElementById('mainSearch')
    const searchbar2 = document.getElementById('searchBar2')
    const showMain = document.getElementById('showMain');
    const mainArtist = document.getElementById('mainArtist');
    const mainAlbum = document.getElementById('mainAlbum');
    showMain.style.display = 'initial'
    mainSearch.style.display = 'none';
    searchbar2.style.display = 'none';
    mainArtist.style.display = 'none';
    mainAlbum.style.display = 'none';
}

function removeAllChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

