const path = require( "path" );

module.exports = {
    mode: "development",
    entry: {
        "dist/table_definition": "./src/table_definition/index.js",
        "dist/select_definition": "./src/select_definition/index.js"
    },
    output: {
        path: __dirname,
        filename: "[name].bundle.js"
    },
    resolve: {
        modules: [
            path.join( __dirname, "src" ),
            "node_modules"
        ],
        extensions: [ ".js" ],
    },
    module: {
        rules: [
            {
                test: "/\.js$/",
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader"
                }
            }
        ]
    }
};