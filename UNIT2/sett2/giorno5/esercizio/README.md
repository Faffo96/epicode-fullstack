 
# Esercizio NetFlix

In quest'esercizio ho trasformato in codice cio' che veniva mostrato nei mockup. Ho fatto qualche aggiunta extra-traccia.

## Tabella dei Contenuti

1. [Introduzione](#introduzione)
2. [Modifiche Extra](#modifiche-extra)
3. [Contatti](#contatti)
4. [Funzioni](#funzioni)
## Introduzione

Il layout è stato replicato al meglio su tutte e 3 le pagine, cercando di rimanere il piu' fedele possibile ai mockups, in tutte le risoluzioni richieste.

## Modifiche Extra

Elenco delle principali modifche extra:

1. index.html:

1.1. Aggiunta di un bottone di accensione all'apertura di index.html.
1.2 Tutti gli elementi cliccabili (dropdown compresi) hanno un effetto di hover.
1.3 Le immagini del carousel hanno un effetto di hover.
1.4 Il logo NetFlix ha un effetto di hover.
1.5 I bottoni social del footer hanno un loro effetto di hover specifico.
1.6 Ho provato ad aggiungere il suono di intro di NetFlix al click del bottone iniziale. Ma non funziona.

2. profile.html:

2.1 aggiunta di checkbox personalizzati.

3. account.html:

3.1 Il bottone "Cancel Membership" ha un effetto di hover unico.

## Funzioni

addFadeClassToModal():

La funzione addFadeClassToModal aggiunge la classe CSS "fade" all'elemento modale con ID "exampleModal" dopo un breve ritardo per garantirne l'applicazione, se il modale è attualmente visualizzato.
Ottieni l'elemento modale con ID "exampleModal".
Verifica se l'elemento modale ha la classe "show".
Se il modale ha la classe "show", aggiungi la classe "fade" all'elemento modale.

/-----/

generateRandomNumber():

Funzione per generare un numero casuale all'interno di un intervallo dato.
La funzione accetta due parametri: min e max.
Utilizza il metodo Math.random() per generare un numero decimale casuale compreso tra 0 e 1.
Il numero decimale viene quindi moltiplicato per la differenza tra max e min per ottenere un numero casuale all'interno dell'intervallo desiderato.
Il risultato viene arrotondato verso il basso al numero intero più vicino utilizzando Math.floor().
Il numero casuale finale viene restituito come output della funzione.

@param {number} min - Il valore minimo dell'intervallo.
@param {number} max - Il valore massimo dell'intervallo.
@returns {number} - Il numero casuale generato.

/-----/

populateCarousel():

Funzione per popolare un carousel con immagini in ordine casuale senza ripetizioni.
Itera su ogni elemento nell'array del carousel.
Genera un numero casuale tra 0 e 10 usando la funzione generateRandomNumber.
Controlla se il numero generato è già presente nell'array randomNumbers.
Se l'array randomNumbers ha raggiunto una lunghezza di 6, reimpostalo su un array vuoto.
Ripeti i passaggi 2-4 finché non viene generato un numero casuale unico.
Aggiungi il numero casuale unico all'array randomNumbers.
Imposta l'attributo src dell'elemento corrente del carousel alla concatenazione di generalSrc e il numero casuale unico seguito dall'estensione del file.
Ripeti i passaggi 2-7 per ogni elemento nell'array del carousel.
@param {Array} carouselArray - L'array degli elementi del carousel.
@param {string} generalSrc - Il percorso generale delle immagini.

## Contatti

Discord: #faffo8049

