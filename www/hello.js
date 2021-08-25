/*global cordova, module*/

module.exports = {
    greet: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "greet", [name]);
    },

    info: function (printerName, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "info", [printerName]);
    },

    startPrinter: function (start, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "startPrinter", [start]);
    },

    resetPrinter: function (reset, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "resetPrinter", [reset]);
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
    },

    addString: function (text, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "addString", [text]);
    },

    printString: function (print, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "printString", [print]);
    },

    printBitmap: function (base64Data, width, height, resolve, reject) {
        cordova.exec(successCallback, errorCallback, "Hello", "printBitmap", [pictureBase64, width, height]);
    },

    walkPaper: function (num, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello", "walkPaper", [num]);
    }






};
