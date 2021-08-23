/*global cordova, module*/

module.exports = {
    greet: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "greet", [name]);
    },

    info: function (printerName, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "info", [printerName]);
    },

    alignment: function (align, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "alignment", [align]);
    },

    leftIndent: function (indent, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "leftIndent", [indent]);
    },

    lineSpace: function (line, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "lineSpace", [line]);
    },

    fontSize: function (font, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "fontSize", [font]);
    }




};
