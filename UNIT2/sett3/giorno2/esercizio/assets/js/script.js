const btnSalva = document.getElementById('salva');
const btnElimina = document.getElementById('elimina');
const btnNext = document.getElementById('nextPage');
let ciao = document.getElementById('lastUser');

btnSalva.addEventListener('click', function (e) {
    e.preventDefault();

    ciao.value = "";

    let scritta = document.getElementById('utente').value;
    localStorage.setItem("Utente", scritta)

    ciao.innerHTML = `Benvenuto/a ${scritta}`
    document.getElementById('utente').value = "";
});


btnElimina.addEventListener('click', function (e) {
    e.preventDefault();

    localStorage.clear();

    ciao.innerHTML = "";
})

btnNext.addEventListener('click', function (e) {
    e.preventDefault();

    window.location.href = '../pagina2.html'
})