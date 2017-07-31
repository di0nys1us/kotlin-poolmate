'use strict';

import React from "react";
import {render} from "react-dom";
import Application from "./components/Application.jsx";

render(
    <Application message="Hello, world!"/>,
    document.getElementById('root')
);
