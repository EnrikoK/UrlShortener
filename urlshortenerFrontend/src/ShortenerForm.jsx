import { useState } from "react";
import axios from 'axios';

export default function ShortenerFrom(){
    const [message, setMessage] = useState('');
    const [body, setBody] = useState('');
    const [buttonActive, setButtonActive] = useState(false)
    const [loading, setLoading] = useState(false);
     
    function handleSubmit(e){
        e.preventDefault();
        setLoading(true);
        setButtonActive(true)
        axios.post('http://localhost:8080/generate',
            {userInput: message}
        ).then((response) =>setBody(response.data.outputUrl)).then(setButtonActive(false));
        setLoading(false);
        
    }


    return(
        <>
        <form className="form-component" onSubmit={handleSubmit}>
            <label>Enter url:</label>
            <input className="form-input" type="text" required value={message} onChange={(e) => setMessage(e.target.value)}/>
            <button className="submit-button" disabled={buttonActive}>submit</button> 
            {loading?<p>loading...</p>:<a href={body} >{body?window.location.href+ body:null}</a> }
        </form>
    
        </>
    )
}