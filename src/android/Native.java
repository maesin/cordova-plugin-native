package com.mycompany.cordova;

import android.content.Intent;
import android.os.Bundle;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public final class Native extends CordovaPlugin {

    public interface Executor {
        boolean execute(CordovaPlugin plugin, String action, JSONArray data, CallbackContext callbackContext);
        void onActivityResult(CordovaPlugin plugin, int requestCode, int resultCode, Intent intent);
    }

    private Executor executor = new Executor() {
        @Override
        public boolean execute(CordovaPlugin plugin, String action, JSONArray data, CallbackContext callbackContext) {
            return false;
        }

        @Override
        public void onActivityResult(CordovaPlugin plugin, int requestCode, int resultCode, Intent intent) {
        }
    };

    public void set(Executor executor) {
        this.executor = executor;
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        cordova.setActivityResultCallback(this);
        return executor.execute(this, action, data, callbackContext);
    }

    @Override
    public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        executor.onActivityResult(this, requestCode, resultCode, intent);
    }
}
