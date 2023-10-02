import React, { useState } from "react";
import PropTypes from 'prop-types';

function ContentPage({ something }) {
    something; useState;
    return (
        <p>Welcome!</p>
    )
}

ContentPage.propTypes = {
    something : PropTypes.func.isRequired
};

export default ContentPage;