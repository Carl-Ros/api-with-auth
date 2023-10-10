/* eslint-disable no-undef */

const todoTypes = {
    APPOINTMENT : "appointments",
    CONSUMABLE : "consumables",
    TIMER : "timers",
}

export function getAppointment(workspaceId, appointmentId, bearerToken) {
    return _getTodo(workspaceId, appointmentId, todoTypes.APPOINTMENT, bearerToken);
}

export function getTimer(workspaceId, timerId, bearerToken) {
    return _getTodo(workspaceId, timerId, todoTypes.TIMER, bearerToken);
}

export function getConsumable(workspaceId, consumableId, bearerToken) {
    return _getTodo(workspaceId, consumableId, todoTypes.CONSUMABLE, bearerToken);
}

function _getTodo(workspaceId, todoId, type, bearerToken) {
    return fetch(`${process.env.REACT_APP_API_URL}/api/v1/workspaces/${workspaceId}/${type}/${todoId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": bearerToken
        },
        mode: "cors"
    })
}

export function createAppointment(workspaceId, appointment, bearerToken) {
    return _createTodo(workspaceId, appointment, todoTypes.APPOINTMENT, bearerToken)
}

export function createConsumable(workspaceId, consumable, bearerToken) {
    return  _createTodo(workspaceId, consumable, todoTypes.CONSUMABLE, bearerToken)
}

export function createTimer(workspaceId, timer, bearerToken) {
    return _createTodo(workspaceId, timer, todoTypes.TIMER, bearerToken)
}


function _createTodo(workspaceId, todo, type, bearerToken){
    if(!_isValidTodo(todo, type)){
        throw `${type} missing required attributes.`
    }

    return fetch(`${process.env.REACT_APP_API_URL}/api/v1/workspaces/${workspaceId}/${type}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": bearerToken
        },
        mode: "cors",
        body: JSON.stringify(todo)
    })   
}

export function getWorkspace(workspaceId, bearerToken) {
    return fetch(`${process.env.REACT_APP_API_URL}/api/v1/workspaces/${workspaceId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": bearerToken
        },
        mode: "cors"
    })
}

export function getUserWorkspaces(userId, bearerToken) {
    return fetch(`${process.env.REACT_APP_API_URL}/api/v1/users/${userId}/workspaces`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": bearerToken
        },
        mode: "cors"
    })
}

export function createUserWorkspace(userId, workspace, bearerToken) {
    if(!_isValidWorkspace(workspace)){
        throw "Workspace missing required attributes."
    }

    return fetch(`${process.env.REACT_APP_API_URL}/api/v1/users/${userId}/workspaces`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": bearerToken
        },
        mode: "cors",
        body: JSON.stringify(workspace)
    })
}

export function inviteUserToWorkspace(userId, workspaceId, bearerToken) {
    return fetch(`${process.env.REACT_APP_API_URL}/api/v1/workspaces/${workspaceId}/memberships`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": bearerToken
        },
        mode: "cors",
        body: JSON.stringify({ id: userId })
    })
}

function _isValidWorkspace(workspace){
    return workspace?.name?.length > 0;
}

function _isValidTodo(todo, type){

    if(!todo || !todo.name || todo.name.length === 0){
        return false;
    }

    switch(type){
        case todoTypes.APPOINTMENT: 
            return !!todo.dateTime;
            
        case todoTypes.CONSUMABLE:
            return !!todo.quantity && !!todo.dailyConsumptionRate;

        case todoTypes.TIMER: 
            return !!todo.durationInDays;
        
        default:
            return false;
        }
}