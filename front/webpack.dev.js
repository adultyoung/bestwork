const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
    mode: 'development',
    devtool: 'source-map',
    devServer: {
        contentBase: './dist',
        compress: true,
        port: 8080,
        allowedHosts: [
            'localhost:8091'
        ],
        stats: 'errors-only',
        clientLogLevel: 'error',
    },
});