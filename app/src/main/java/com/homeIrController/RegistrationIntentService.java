package com.homeIrController;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by hrishid on 1/13/18.
 */
public class RegistrationIntentService extends IntentService {

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try {
        // [START get_token]
        InstanceID instanceID = InstanceID.getInstance(this);
        String token = null;

            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

        // [END get_token]
        Log.i(TAG, "GCM Registration Token: " + token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String TAG = "MyIntentService";
}
