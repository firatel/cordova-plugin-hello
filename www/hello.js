/*global cordova, module*/

module.exports = {
    greet: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "greet", [name]);
    },

    info: function (printerName, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "info", [printerName]);
    },

    printerSetting: function (settings, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "setup", [alignment, leftIntent, lineSpace, fontSize]);
    },
};
