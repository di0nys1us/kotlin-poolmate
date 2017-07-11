'use strict';

import path from "path";

module.exports = {
    entry: path.resolve(__dirname, './src/main/js/index.jsx'),
    output: {
        path: path.resolve(__dirname, './src/main/resources/public/build'),
        publicPath: 'build',
        filename: 'bundle.js'
    }
};
