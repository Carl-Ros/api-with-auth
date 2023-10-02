import React, { useState } from "react";
import PropTypes from 'prop-types';


function LoginForm({ onLogin, onRegister }) {
    // eslint-disable-next-line no-unused-vars
    const [userName, setUserName] = useState("");



    function handleLogin(e){
        e.preventDefault();
        console.log(`onLogin: ${userName}`)
        onLogin(userName, document.getElementById('loginPassword').value);
    }

    function handleRegister(e){
        e.preventDefault();
        console.log(`onRegister: ${userName}`)
        onRegister(userName, document.getElementById('registerPassword').value);
    }    

    function handleChange(e) {
        setUserName(e.target.value);
    }
    
    function flipCard() {
        const card = document.querySelector(".flippable-card");
        if(card.style.transform == "rotateY(180deg)") {
            card.style.transform = "rotateY(0deg)";

        } else {
            card.style.transform = "rotateY(180deg)";
        }
    }

    return (
        <div className="p-8 h-full md:min-h-[762px] m-auto flex flex-col items-center max-w-6xl">
            <div className="flippable-card m-auto h-96 w-full md:w-2/4">
                <form className='absolute w-full card-front flex flex-col items-center shadow-2xl'
                    onSubmit={handleLogin}
                >
                    <input
                        className='border-b-2 mt-12 w-3/4 h-8 rounded-lg hover:ring-1 p-6 text-xl'
                        type='email'
                        placeholder='Enter email'
                        onChange={handleChange}
                        value={userName}
                        required
                    />

                    <input
                        id='loginPassword'
                        className='border-b-2 mt-12 w-3/4 h-8 rounded-lg hover:ring-1 p-6 text-xl'
                        type='password'
                        placeholder='Enter password'
                        required
                    />

                    <button
                        className='mt-16 w-3/4 h-12 rounded shadow-md hover:bg-opacity-80 bg-primary text-white text-xl'
                        type="submit"
                    >
                        Log in
                    </button>

                    <button
                        className='p-4 shadow-secondary mt-12 w-full h-24 rounded shadow-md hover:bg-opacity-80 bg-secondary text-white text-xl'
                        type="button"
                        onClick={flipCard}
                    >
                        Register
                        <svg 
                            xmlns="http://www.w3.org/2000/svg"
                            viewBox="0 0 512 512"
                            width="38"
                            height="20"
                            fill="currentColor"
                            className="inline-block mr-2"
                        >
                        <path d="M256 8c137 0 248 111 248 248S393 504 256 504 8 393 8 256 119 8 256 8zm-28.9 143.6l75.5 72.4H120c-13.3 0-24 10.7-24 24v16c0 13.3 10.7 24 24 24h182.6l-75.5 72.4c-9.7 9.3-9.9 24.8-.4 34.3l11 10.9c9.4 9.4 24.6 9.4 33.9 0L404.3 273c9.4-9.4 9.4-24.6 0-33.9L271.6 106.3c-9.4-9.4-24.6-9.4-33.9 0l-11 10.9c-9.5 9.6-9.3 25.1.4 34.4z"/></svg>
                    </button>
                </form>

                <form className='absolute w-full card-back flex flex-col items-center shadow-2xl bg-gray-100'
                    onSubmit={handleRegister}
                >
                    <input
                        className='border-b-2 mt-12 w-3/4 h-8 rounded-lg hover:ring-1 p-6 text-xl'
                        type='email'
                        placeholder='Enter email'
                        onChange={handleChange}
                        required
                        value={userName}
                    />

                    <input
                        id='registerPassword'
                        className='border-b-2 mt-12 w-3/4 h-8 rounded-lg hover:ring-1 p-6 text-xl'
                        type='password'
                        placeholder='Enter password'
                        required
                    />

                    <button
                        className='mt-16 w-3/4 h-12 rounded shadow-md hover:bg-opacity-80 bg-primary text-white text-xl'
                        type="submit"
                    >
                        Register
                    </button>

                    <button
                        className='text-xl p-4 shadow-secondary mt-12 w-full h-24 rounded shadow-md hover:bg-opacity-80 bg-secondary text-white'
                        type="button"
                        onClick={flipCard}
                    >
                        
                        <svg 
                            xmlns="http://www.w3.org/2000/svg"
                            viewBox="0 0 512 512"
                            width="24"
                            height="20"
                            fill="currentColor"
                            className="inline-block mr-2"
                        >
                            <path d="M256 504C119 504 8 393 8 256S119 8 256 8s248 111 248 248-111 248-248 248zm116-292H256v-70.9c0-10.7-13-16.1-20.5-8.5L121.2 247.5c-4.7 4.7-4.7 12.2 0 16.9l114.3 114.9c7.6 7.6 20.5 2.2 20.5-8.5V300h116c6.6 0 12-5.4 12-12v-64c0-6.6-5.4-12-12-12z"/>
                        </svg>
                        Log in
                    </button>
                </form>
                
            </div>

        </div>
    )
}

LoginForm.propTypes = {
    onLogin: PropTypes.func.isRequired,
    onRegister: PropTypes.func.isRequired,
};

export default LoginForm;