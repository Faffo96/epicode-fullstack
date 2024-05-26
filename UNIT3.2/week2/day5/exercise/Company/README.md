L'applicazione permette di tenere traccia di tutto ciò che succede grazie a un sistema di 3 livelli di logback nei file "myAppErrors.log", 
"myAppWarn.log", "myAppTrace.log".
La maggior parte degli errori vengono gestiti attraverso i validators e Exception personalizzate.
E' possibile creare impiegati tramite REST e ricevere una mail di benvenuto con nome e cognome.
E' possibile settare una foto come avatar del profilo.
E' possibile creare Pc, Tablet o Smartphone e successivamente assegnarli a un impiegato. Anche in questo caso si riceve una mail con nome, cognome e
dettagli sul device, a patto che lo stato sia AVAILABLE.
E' possibile dissociare un device da un utente.

Link to Postman presetted workspace https://www.postman.com/maintenance-astronaut-57114539/workspace/public/collection/33393721-d465512e-9aad-434d-a220-fe7128a9ad9a?action=share&creator=33393721

Ecco una lista degli endpoints da chiamare:

{{BaseUrl}} = localhost:8080/api
{{Users}} = /users
{{Pcs}} = /pcs
{{Tablet}} = /tablets
{{Smartphone}} = /smartphones

per rimuovere un employee da un device inserire dopo l'id del device /removeEmployee

Ecco una lista di dati per testare velocemente le risposte:

EMPLOYEES: 

{
"username": "john_doe",
"name": "John",
"surname": "Doe",
"email": "john.doe@example.com"
}

{
"username": "jane_smith",
"name": "Jane",
"surname": "Smith",
"email": "jane.smith@example.com"
}

PCS:

{
"brand": "HP",
"model": "Pavilion 15",
"storageGb": 512,
"deviceState": "AVAILABLE",
"ram": 16,
"gpu": "NVIDIA GeForce GTX 1650",
"cpu": "Intel Core i7-10750H",
"laptop": true
}

{
"brand": "Dell",
"model": "OptiPlex 7080",
"storageGb": 1024,
"deviceState": "MANTAINANCE",
"ram": 32,
"gpu": "AMD Radeon RX 580",
"cpu": "Intel Core i9-10900",
"laptop": false
}

TABLETS:

{
"brand": "Samsung",
"model": "Galaxy Tab S7+",
"storageGb": 256,
"deviceState": "AVAILABLE",
"haveSimSlot": true
}

{
"brand": "Microsoft",
"model": "Surface Pro 8",
"storageGb": 128,
"deviceState": "RETIRED",
"haveSimSlot": false
}


SMARTPHONES:

{
"brand": "Samsung",
"model": "Galaxy S21",
"storageGb": 128,
"deviceState": "AVAILABLE",
"IsDualSim": true
}

{
"brand": "Apple",
"model": "iPhone 12",
"storageGb": 256,
"deviceState": "ASSIGNED",
"isDualSim": false
}

