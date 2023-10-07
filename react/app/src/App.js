/* eslint-disable no-undef */
import './dist/App.css';
import LoginForm from "./components/LoginForm";
import ContentPage from "./containers/ContentPage";
import LogoutButton from "./components/LogoutButton";
import React from "react";
import { AuthProvider, useAuth} from './context/AuthContext';

function Components(){
    const { bearerToken, login, logout, register } = useAuth();

    return (
        <div className="App min-h-screen">
            <header className="min-w-full h-16 border-t-2 border-b-2 bg-primary shadow-md flex">
                <h1 className='text-2xl m-auto'>Header..</h1>
                {bearerToken ? <LogoutButton onLogout={logout} /> : ""}
            </header>
            
            {bearerToken ? <ContentPage/> : <LoginForm onLogin={login} onRegister={register} />}
        </div>
    );
}

function App() {
    return (
        <AuthProvider>
            <Components />
        </AuthProvider>
    );
}

export default App;
