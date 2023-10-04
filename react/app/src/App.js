/* eslint-disable no-undef */
import './dist/App.css';
import LoginForm from "./components/LoginForm";
import ContentPage from "./components/ContentPage";
import React, { useState, useEffect } from "react";

function App() {
    const [bearerToken, setBearerToken] = useState(null);

    function login(username, password) {
        loginOrRegister(false, username, password)
        .then((result) => {
            if(result.ok){
                result.json().then(storeToken)
            }
        })
        .catch(err => console.log("Failed to obtain access token: ", err));
    }

    function register(username, password) {
        loginOrRegister(true, username, password)
        .then((result) => {
            if(result.ok){
                result.json().then(storeToken)
            }
        })
        .catch(err => console.log("Failed to register: ", err))
    }    

    function loginOrRegister(register, username, password){

        return fetch(`${process.env.REACT_APP_API_URL}/api/v1/${register ? "auth/register" : "auth/authenticate"}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({email: username, password: password}),
            mode: "cors"
        })
    }

    function storeToken(json) {
            const token = json?.token;
            if(token){
                setBearerToken(token);
                localStorage.setItem('bearerToken', token);
            }
    }

    function isTokenExpired(jwt){
        return jwt && Date.now() >= JSON.parse(atob(jwt.split('.')[1])).exp * 1000;
    }

    useEffect(() => {
        const jwt = bearerToken || localStorage.getItem("bearerToken");

        if(jwt && !isTokenExpired(jwt)){
            setBearerToken(jwt);
        } else {
            localStorage.removeItem("bearerToken");
            setBearerToken("");
        }
        
    }, [bearerToken])

    return (
        <div className="App min-h-screen">
            <header className="min-w-full h-16 border-t-2 border-b-2 bg-primary shadow-md flex">
                <h1 className='text-2xl m-auto'>Header..</h1>
            </header>
            
            { bearerToken ? <ContentPage/> : <LoginForm onLogin={login} onRegister={register}/> }
            
        </div>
    );
}

export default App;
