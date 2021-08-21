/*global cordova, module*/

module.exports = {
    greet: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "greet", [name]);
    },

    info: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "info", [printerName]);
    }
};
