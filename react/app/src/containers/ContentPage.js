import React, { useEffect, useState } from "react";
import * as api from "../services/api";
import { useAuth } from '../context/AuthContext';
import CreationDialog from "../components/CreationDialog";
import PropTypes from 'prop-types';

function ContentPage() {
    const { user, bearerToken } = useAuth();
    const [workspace, setWorkspace] = useState(null);


    const createWorkspace = async (name, collaborators) => {
        const response = await api.createUserWorkspace(user.id, { "name": name }, bearerToken);
        if (response.ok) {
            const workspace = await response.json();
            if(collaborators){
                for (const user of collaborators) {
                    console.log(user);
                    api.inviteUserToWorkspace(user.id, workspace.id, bearerToken); // Todo: should be an email invite or something instead
                }
            }

            setWorkspace(workspace);
        }
    }

//  async function _testSetup() {
//      let workspaceId;
//
//      const response = await api.createUserWorkspace(user.id, { "name": "My Workspace" }, bearerToken);
//
//      if (response.ok) {
//          const json = await response.json();
//          workspaceId = json.id;
//      }
//
//      await api.createAppointment(workspaceId,
//          {
//              "dateTime": "2023-12-22T06:00:00",
//              "name": "My Appointment"
//          },
//          bearerToken
//      );
//
//      await api.createTimer(workspaceId,
//          {
//              "durationInDays": 7,
//              "name": "My Timer"
//          },
//          bearerToken
//      );
//
//      await api.createConsumable(workspaceId,
//          {
//              "dailyConsumptionRate": 6,
//              "quantity": 46,
//              "name": "My Consumable"
//          },
//          bearerToken
//      );
//
//      const res = await api.getWorkspace(workspaceId, bearerToken);
//
//      if (res.ok) {
//          // const workspace = await res.json();
//          //const workspaceTodos = [workspace.appointments, workspace.consumables, workspace.timers].flatMap(it => it);
//          //setTodos(workspaceTodos);
//      }
//  }

    useEffect(() => {
        if (user) {
            console.log("user found " + user.id)
            const workspace = (async () => {
                const result = await api.getUserWorkspaces(user.id, bearerToken);
                if (result.ok) {
                    const workspaces = await result.json();
                    return workspaces[0]; // Todo: should be filtered by primary
                }
            })();

            workspace.then((workspace) => {
                setWorkspace(workspace);
            })

            //_testSetup();
        }
    }, [user]);


    return (
        <>
            {workspace ? <Workspace workspace={workspace} /> : <EmptyWorkspace createWorkspace={createWorkspace} />}
        </>
    )
}


function fieldsByType(type){

    const fields = {
        appointment: [
            {
                name: "date",
                label: "Date",
                required: true,
                type: "date",
            },
        ],
        timer: [
            {
                name: "duration",
                label: "Duration (in days)",
                required: true,
                type: "number",
                min: 1
            },
        ],
        consumable: [
            {
                name: "quantity",
                label: "Quantity",
                required: true,
                type: "number",
                min: 0
            },
            {
                name: "dailyConsumptionRate",
                label: "Consumption per day",
                required: true,
                type: "number",
                min: 0
            },
        ],
    };
    
    return fields[type];
}    


function Workspace({ workspace }) {
    const [dialog, toggleDialog] = useState(false);
    const [todos, setTodos] = useState([workspace.appointments, workspace.consumables, workspace.timers].flatMap(it => it));
    const { user, bearerToken } = useAuth();
    const [ typeSelected, setTypeSelected ] = useState("");

    const createTodo = async (data) => {

        const response = await api.createAppointment(user.id, data, bearerToken);

        if (response.ok) {
            await response.json();
            setTodos([...todos, data]);
        }
    }

    const closeDialog = () => {
        toggleDialog(false);
    }

    const openDialog = () => {
        toggleDialog(true);
    }

    const toggleOptions = () => {
        const buttonGroup = document.getElementById('todoOption');

        if(buttonGroup.classList.contains('hidden')){
            buttonGroup.classList.remove('hidden');
            setTimeout(() => {
                buttonGroup.style.opacity = 100;
            }, 50);

        } else {
            buttonGroup.style.opacity = 0;
            setTimeout(() => {
                buttonGroup.classList.add('hidden');
            }, 300);
        }
    }

    const initCreationDialog = (type) => {
        if(type) {
            setTypeSelected(type);
        }
        openDialog();
    }

    const createTodoData = 
    [
        {
            name: "todoName",
            label: "Name",
            required: true,
            type: "text",
        },
    ]

    if(typeSelected){
        createTodoData.push(...fieldsByType(typeSelected));
    }
    

    return (
        <div className="md:mt-32 flex flex-col ">
            {dialog ? <CreationDialog onCloseDialog={closeDialog} onCreate={createTodo} fields={createTodoData} title="New Activity"></CreationDialog> :
                <div
                    id="workspace"
                    className="min-h-[762px] p-8 md:p-16  m-auto flex flex-col max-w-6xl justify-between bg-gray-50 shadow-2xl transition-opacity-300">
                    <h1 className="text-4xl font-thin">
                        Welcome to your workspace!
                    </h1>
                    <div className="flex justify-end">
                        <button
                            className="rounded-full mt-8 self-end w-16 h-16 text-secondary shadow-lg hover:ring-2 hover:opacity-90"
                            onClick={toggleOptions}
                        >
                            <svg xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 512 512"
                                height="64px"
                                width="64px"
                                fill="currentColor"

                                className="inline-block"

                            >
                                <path d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8zm144 276c0 6.6-5.4 12-12 12h-92v92c0 6.6-5.4 12-12 12h-56c-6.6 0-12-5.4-12-12v-92h-92c-6.6 0-12-5.4-12-12v-56c0-6.6 5.4-12 12-12h92v-92c0-6.6 5.4-12 12-12h56c6.6 0 12 5.4 12 12v92h92c6.6 0 12 5.4 12 12v56z" />
                            </svg>
                        </button>
                        <div id="todoOption" className="hidden absolute flex flex-col mr-20 border-r-primary border-r-2 w-40 opacity-0 transition-opacity-300">
                            <button className="rounded-sm p-2 text-white bg-secondary shadow-sm hover:ring-2"
                            onClick={() => initCreationDialog("appointment")}>Appointment</button>
                            <button className="rounded-sm mt-1 p-2 text-white bg-secondary shadow-sm hover:ring-2" 
                            onClick={() => initCreationDialog("consumable")}>Consumable</button>
                            <button className="rounded-sm mt-1 p-2 text-white bg-secondary shadow-sm hover:ring-2" 
                            onClick={() => initCreationDialog("timer")}>Timer</button>
                        </div>
                    </div>
                </div>
            }
        </div>
    )
}

Workspace.propTypes = {
    workspace: PropTypes.object.isRequired,
};


function EmptyWorkspace({ createWorkspace }) {
    const [dialog, toggleDialog] = useState(false);

    const closeDialog = () => {
        toggleDialog(false);
    }

    const openDialog = () => {
        toggleDialog(true);
    }

    const workspaceFromFormData = (formData) => {
        console.log(formData)
        createWorkspace(formData.workspaceName, formData?.workspaceInvite)
    }

    const createWorkspaceData = 
    [
        {
            name: "workspaceName",
            placeholder: "Workspace name",
            required: true,
            type: "text",
        },
        {
            name: "workspaceInvite",
            placeholder: "Invite collaboorator by email",
            type: "email"
        },        
    ];

    return (
        <div className="md:mt-32 flex flex-col ">
            {dialog ? <CreationDialog onCloseDialog={closeDialog} onCreate={ workspaceFromFormData } fields={ createWorkspaceData } title="Create workspace"></CreationDialog> :
                <div
                    id="emptyWorkspace"
                    className="min-h-[762px] p-8 md:p-16  m-auto flex flex-col max-w-6xl justify-between bg-gray-50 shadow-2xl transition-opacity-300">
                    <h1 className="text-4xl font-thin">
                        You do not have a workspace, create one!
                    </h1>
                    <button
                        className="rounded-full mt-8 self-end w-16 h-16 text-secondary shadow-lg hover:ring-2 hover:opacity-90"
                        onClick={openDialog}
                    >
                        <svg xmlns="http://www.w3.org/2000/svg"
                            viewBox="0 0 512 512"
                            height="64px"
                            width="64px"
                            fill="currentColor"

                            className="inline-block"

                        >
                            <path d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8zm144 276c0 6.6-5.4 12-12 12h-92v92c0 6.6-5.4 12-12 12h-56c-6.6 0-12-5.4-12-12v-92h-92c-6.6 0-12-5.4-12-12v-56c0-6.6 5.4-12 12-12h92v-92c0-6.6 5.4-12 12-12h56c6.6 0 12 5.4 12 12v92h92c6.6 0 12 5.4 12 12v56z" />
                        </svg>
                    </button>

                </div>
            }
        </div>
    )
}

EmptyWorkspace.propTypes = {
    createWorkspace: PropTypes.func.isRequired,
};

export default ContentPage;