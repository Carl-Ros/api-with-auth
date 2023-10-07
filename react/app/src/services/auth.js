/* eslint-disable no-undef */
export function loginOrRegister(register, username, password){
    return fetch(`${process.env.REACT_APP_API_URL}/api/v1/${register ? "auth/register" : "auth/authenticate"}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({email: username, password: password}),
        mode: "cors"
    })
}

export function getLoggedInUser(jwt) {
    return fetch(`${process.env.REACT_APP_API_URL}/api/v1/users/me`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": jwt
        },
        mode: "cors"
    })
}