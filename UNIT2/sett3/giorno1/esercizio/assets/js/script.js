class User {
    constructor(_firstName, _lastName, _age = 0, _location) {
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.age = _age;
        this.location = _location;
    }
    compareAge(user) {
        if (this.age > user.age) {
            console.log(`${this.firstName} è più vecchio/a di ${user.firstName}`)
        } else if (this.age > user.age) {
            console.log(`${this.firstName} e ${user.firstName} hanno la stessa età`)
        } else {
            console.log(`${this.firstName} è più giovane di ${user.firstName}`)
        }
    }
};

const user1 = new User('Mario', 'Rossi', 22, 'NAPOLI');
console.log(user1)

const user2 = new User('Felice', 'Cesarano', 23, 'NAPOLI');
console.log(user2)

user1.compareAge(user2);



class Pet {
    constructor(_petName, _ownerName, _species, _breed) {
        this.petName = _petName;
        this.ownerName = _ownerName;
        this.species = _species;
        this.breed = _breed;
    }
    compareOwner(pet) {
        return this.ownerName === pet.ownerName;
    }
}

const btn = document.getElementById('aggiungi');
const pets = [];
let petsNew;

btn.addEventListener('click', e => {
    e.preventDefault();

    let petName = document.getElementById('petName').value;
    let ownerName = document.getElementById('ownerName').value;
    let species = document.getElementById('species').value;
    let breed = document.getElementById('breed').value;

    petsNew = new Pet(petName, ownerName, species, breed);
    pets.push(petsNew);

    for (let i = 0; i < pets.length; i++) {
        if (pets[i].compareOwner(petsNew)) {
            console.log(`${pets[i].ownerName} ha già registrato uno o più animali`);
        }
    }

    printPets();
});

const printPets = () => {
    let newPet = document.getElementById('newPet');
    newPet.innerHTML = '';
    pets.forEach(detailsPet => {
        let col1 = document.createElement('td');
        col1.innerText = `${detailsPet.petName}`;
        let col2 = document.createElement('td');
        col2.innerText = `${detailsPet.ownerName}`;
        let col3 = document.createElement('td');
        col3.innerText = `${detailsPet.species}`;
        let col4 = document.createElement('td');
        col4.innerText = `${detailsPet.breed}`;
        let row = document.createElement('tr');
        row.append(col1, col2, col3, col4);
        newPet.appendChild(row);
    });
};
