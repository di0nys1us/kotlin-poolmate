'use strict';

import path from "path";

export default {
    entry: path.resolve(__dirname, './src/main/js/index.jsx'),
    output: {
        path: path.resolve(__dirname, './src/main/resources/public/build'),
        publicPath: "build",
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader'
            },
        ]
    }
};
