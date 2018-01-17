package com.homeIrController;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.JsonReader;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.homeIrController.controller.ApplianceController;
import com.homeIrController.controller.ControllerFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * Created by hrishid on 1/13/18.
 */
public class MyGcmListenerService extends GcmListenerService {
    @Override
    public void onCreate() {
        super.onCreate();
        consumerIrManager = (ConsumerIrManager) this.getSystemService(Context.CONSUMER_IR_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String directive = data.getString("default");
        Log.d(TAG, "Received Directive : " + directive);
        handleDirective(directive);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void handleDirective(String message)
    {
        try {
            JSONObject messageObject = new JSONObject(message);
            Log.d(TAG, "Message Object = " + messageObject.toString());

            String dataString = messageObject.getString("data");
            Log.d(TAG, "Data String = " + dataString);

            JSONObject dataObject = new JSONObject(dataString);
            Log.d(TAG, "Data Object = " + dataObject.toString());

            JSONObject headerObject = dataObject.getJSONObject("header");

            ApplianceController controller = ControllerFactory.getController(headerObject);
            Log.d(TAG, "Delegating request to {} : " + controller.getName());
            controller.handle(dataObject, consumerIrManager);
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON!", e);
        }
    }

    private ConsumerIrManager consumerIrManager;
    private static final String TAG = "MyGcmListenerService";
}
