// server.js
const jsonServer = require('json-server');
const server = jsonServer.create();
const router = jsonServer.router('db.json');
const middlewares = jsonServer.defaults();
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

// Configura una chiave segreta per firmare i token JWT
const JWT_SECRET = 'your-secret-key'; // Modifica con una chiave segreta sicura

server.use(middlewares);
server.use(jsonServer.bodyParser);

server.post('/register', (req, res) => {
    const { email, password, name } = req.body;
    const users = router.db.get('users').value();

    // Controlla se l'email esiste già
    const userExists = users.some(user => user.email === email);

    if (userExists) {
        return res.status(400).json('Email already exists');
    }

    // Hash della password e salvataggio dell'utente
    const hashedPassword = bcrypt.hashSync(password, 10);
    const newUser = { id: users.length + 1, email, password: hashedPassword, name };
    router.db.get('users').push(newUser).write();

    res.status(201).json(newUser);
});

server.post('/login', (req, res) => {
    const { email, password } = req.body;
    const user = router.db.get('users').find({ email }).value();

    if (!user) {
        return res.status(400).json('Cannot find user');
    }

    // Verifica della password
    const isPasswordValid = bcrypt.compareSync(password, user.password);

    if (!isPasswordValid) {
        return res.status(400).json('Incorrect password');
    }

    // Genera un token JWT
    const token = jwt.sign({ id: user.id, email: user.email }, JWT_SECRET, { expiresIn: '1h' });
    console.log('Generated JWT Token:', token);

    // Restituisce il token e i dati dell'utente
    res.status(200).json({ accessToken: token, user });
});

server.use(router);
server.listen(3000, () => {
    console.log('JSON Server is running');
});
