module.exports = {
    exec: function(successCallback, errorCallback, action, args) {
        cordova.exec(successCallback, errorCallback, "Native", action, args);
    }
};