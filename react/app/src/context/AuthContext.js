import React, {useState, useContext, useEffect} from 'react';
import {loginOrRegister, getLoggedInUser} from "../services/auth";
import PropTypes from 'prop-types';

const AuthContext = React.createContext();

export function useAuth(){
    return useContext(AuthContext);
}

export function AuthProvider({children}){
    const [user, setUser] = useState(null);
    const [bearerToken, setBearerToken] = useState("");

    function login(username, password) {
        loginOrRegister(false, username, password)
            .then((result) => {
                if (result.ok) {
                    result.json().then(_storeToken)
                }
            })
            .catch(err => console.log("Failed to obtain access token: ", err));
    }

    function register(username, password) {
        loginOrRegister(true, username, password)
            .then((result) => {
                if (result.ok) {
                    result.json().then(_storeToken)
                }
            })
            .catch(err => console.log("Failed to register: ", err))
    }

    function logout() {
        _clearToken();
    }

    function _storeToken(json) {
        const token = json?.token || json;
        if (token) {
            const _bearerToken = token.startsWith("Bearer ") ? token : "Bearer " + token;
            setBearerToken(_bearerToken);
            localStorage.setItem('bearerToken', _bearerToken);
        }
    }

    function _clearToken() {
        localStorage.removeItem("bearerToken");
        setBearerToken("");
    }

    function isTokenExpired(jwt) {
        return jwt && Date.now() >= JSON.parse(atob(jwt.split('.')[1])).exp * 1000;
    }

    useEffect(() => {

        const jwt = bearerToken || localStorage.getItem("bearerToken");

        if (jwt && !isTokenExpired(jwt)) {
            _storeToken(jwt);
        } else {
            _clearToken();
        }
    }, [])

    useEffect(() => {
        if (!bearerToken) {
            return;
        }

        getLoggedInUser(bearerToken).then((response) => {
            if (response.ok) {
                response.json().then(it => {
                    setUser(it)
                });
            }
        })
    }, [bearerToken])
    
    return (
        <AuthContext.Provider value={{user, bearerToken, login, logout, register}}>{children}</AuthContext.Provider>
    )
}

AuthProvider.propTypes = {
    children : PropTypes.object.isRequired
};