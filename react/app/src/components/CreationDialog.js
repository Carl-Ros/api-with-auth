import React, { useEffect, useState } from "react";
import PropTypes from 'prop-types';

function CreationDialog({ onCloseDialog, onCreate, title, fields }) {
    const [formData, setFormData] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const submit = (e) => {
        e.preventDefault();
        onCreate(formData);
        onCloseDialog();
    }

    const closeDialog = () => {
        onCloseDialog();
    }

    useEffect(() => {
        setTimeout(() => {
            const container = document.getElementById("creationDialog");
            container.style.opacity = 100;
        }, 100);
    }, [])

    return (
        <div id="creationDialog" className="z-10 opacity-0 absolute mt-16 flex flex-col w-full backdrop-blur-[1px] transition-opacity-300">
            <div className="w-full min-h-[560px] md:w-[560px] m-auto flex flex-col max-w-6xl bg-gray-50 shadow-2xl">
                <div className="h-24 flex bg-secondary w-full border-b-black border-b-2 rounded-lg">
                    <h1 className="text-2xl text-white self-center m-auto">{title}</h1>

                    <button
                        className="rounded mr-1 w-12 h-12 text-white"
                        onClick={closeDialog}
                    >
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            fill="currentColor"
                            width="48px"
                            height="48px"
                            className=""
                            viewBox="0 0 512 512">
                            <path d="M464 32H48C21.5 32 0 53.5 0 80v352c0 26.5 21.5 48 48 48h416c26.5 0 48-21.5 48-48V80c0-26.5-21.5-48-48-48zm-83.6 290.5c4.8 4.8 4.8 12.6 0 17.4l-40.5 40.5c-4.8 4.8-12.6 4.8-17.4 0L256 313.3l-66.5 67.1c-4.8 4.8-12.6 4.8-17.4 0l-40.5-40.5c-4.8-4.8-4.8-12.6 0-17.4l67.1-66.5-67.1-66.5c-4.8-4.8-4.8-12.6 0-17.4l40.5-40.5c4.8-4.8 12.6-4.8 17.4 0l66.5 67.1 66.5-67.1c4.8-4.8 12.6-4.8 17.4 0l40.5 40.5c4.8 4.8 4.8 12.6 0 17.4L313.3 256l67.1 66.5z" />
                        </svg>
                    </button>
                </div>
                <form onSubmit={submit} className=" flex flex-col justify-around items-center w-3/4 h-4/6 m-auto pb-8" >
                    {
                    fields.map((field) => (
                        <>
                            {field.label ? 
                            <label 
                                htmlFor={field.name}
                                className="mt-12 mb-2 self-start text antialiased pl-2 font-semibold"
                            > {field.label} </label> : null}
                            <input
                                key={field.name}
                                id={field.name}
                                className={`border-b-2 w-full h-8 rounded-lg hover:ring-1 p-6 text-md ${field.label ? '' : "mt-12"}`} 
                                name={field.name}
                                type={field.type}
                                required={field.required}
                                min={field.min}
                                {...(field.placeholder ? { placeholder: field.placeholder } : {})}
                                onChange={handleChange}
                            />
                        </>
                    ))
                    }
                    <button
                        className="mt-16 w-full h-12 rounded shadow-md hover:bg-opacity-80 bg-primary text-white text-xl"
                        type="submit"
                    >
                        Create
                    </button>
                </form>
            </div>
        </div>
    )
}

CreationDialog.propTypes = {
    onCloseDialog: PropTypes.func.isRequired,
    onCreate: PropTypes.func.isRequired,
    fields: PropTypes.array.isRequired,
    title: PropTypes.string.isRequired,
    todoType: PropTypes.string
};

export default CreationDialog;