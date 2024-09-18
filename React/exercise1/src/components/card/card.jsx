
function Card({ cardTitle, imageSrc, cardText, addCard }) {
    return (
        <div className="card" style={{ width: '18rem' }}>
            <img src={imageSrc} className="card-img-top" alt={cardTitle} style={{ width: '200px' }}/>
            <div className="card-body">
                <h5 className="card-title">{cardTitle}</h5>
                <p className="card-text">{cardText}</p>
                <button className="btn btn-primary" onClick={addCard}>
                    bottone
                </button>
            </div>
        </div>
    );
}

export default Card