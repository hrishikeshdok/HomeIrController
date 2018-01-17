package com.homeIrController.controller;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hrishid on 1/15/18.
 */

public class ControllerFactory {

    public static ApplianceController getController(JSONObject headerObject) throws JSONException {
        String namespace = headerObject.getString("namespace");
        Log.d(TAG, "ControllerFactory received request with namespace " + namespace);
        if ("Alexa.PowerController".equals(namespace))
        {
            return PowerController.getInstance();
        }
        else if ("Alexa.StepSpeaker".equals(namespace))
        {
            return VolumeStepController.getInstance();
        }
        else if ("Alexa.Speaker".equals(namespace))
        {
            return VolumeController.getInstance();
        }
        Log.d(TAG, "Unknown namespace provided. Returning null " + namespace);
        return null;
    }

    private static final String TAG = "ControllerFactory";
}
