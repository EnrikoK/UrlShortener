import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios, { HttpStatusCode } from "axios";

export default function ShortUrlComponent(){
    const [error, setError] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    let params = useParams()
    useEffect(() =>{
        axios.get("http://localhost:8080/"+params.shortUrl,{headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json',
          },}).then((resp) => window.location.replace(resp.data.inputUrl)).catch((err) => {setError(true); setErrorMessage(err.response.status)});
          
    },[])
        
       
    
    return(
        <h1>{error?<p>Ooop something went wrong: {errorMessage}</p>:<p>loading...</p>}</h1>
    )
    
}