/*global cordova, module*/

module.exports = {
    greet: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "greet", [name]);
    },

    info: function (printerName, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "info", [printerName]);
    },

    alignment: function (alignment, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "alignment", [alignment]);
    },
};
