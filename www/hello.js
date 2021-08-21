/*global cordova, module*/

module.exports = {
    greet: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Hello from Android", "greet", [name]);
    }
};
