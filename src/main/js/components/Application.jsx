'use strict';

import React from "react";

const Application = props =>
    <div className="container">
        <div className="col-12">
            <h1>PoolMate</h1>
            <p>
                {props.message}
            </p>
        </div>
    </div>;

export default Application;
