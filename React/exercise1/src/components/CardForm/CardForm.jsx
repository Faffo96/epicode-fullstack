import { useState } from "react";

function CardForm({ creaCard }) {
    const [cardTitle, setCardTitle] = useState('');
    const [cardText, setCardText] = useState('');
    const [imageSrc, setImageSrc] = useState('');
  
    const handleCard = () => {
      const newCard = {
        cardTitle,
        cardText,
        imageSrc
      };
      creaCard(newCard);
      // Clear form fields after submission
      setCardTitle('');
      setCardText('');
      setImageSrc('');
    };
  
    return (
      <div>
        <input
          type="text"
          placeholder="Card Title"
          value={cardTitle}
          onChange={(e) => setCardTitle(e.target.value)}
        />
        <input
          type="text"
          placeholder="Card Text"
          value={cardText}
          onChange={(e) => setCardText(e.target.value)}
        />
        <input
          type="text"
          placeholder="Image URL"
          value={imageSrc}
          onChange={(e) => setImageSrc(e.target.value)}
        />
        <button onClick={handleCard}>Aggiungi Card</button>
      </div>
    );
  }
  
  export default CardForm;
