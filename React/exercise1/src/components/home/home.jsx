/* import { useState } from 'react'
import reactLogo from './../../assets/react.svg'
import viteLogo from '/vite.svg' */
import './home.css'
import Card from '../card/card'
import CardForm from '../CardForm/CardForm'
import { useState } from 'react';

function Home() {
  // State for managing cards
  const [cities, setCities] = useState([
    {
      cardTitle: "Roma",
      cardText: "Lorem ipsum, dolor sit amet consectetur adipisicing elit. Aperiam, perspiciatis",
      imageSrc: "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2023/04/14/16814863035657.jpg"
    }
  ]);

  // Function to add a new card
  const addCard = (city) => {
    setCities([...cities, city]);
  };

  return (
    <>
      {cities.map((city, index) => (
        <Card
          key={index}
          cardTitle={city.cardTitle}
          cardText={city.cardText}
          imageSrc={city.imageSrc}
        />
      ))}
      <CardForm creaCard={addCard} />
    </>
  );
}

export default Home;