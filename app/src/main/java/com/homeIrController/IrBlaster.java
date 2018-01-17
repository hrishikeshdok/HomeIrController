package com.homeIrController;

import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by hrishid on 1/14/18.
 */

public class IrBlaster {

    private IrBlaster()
    {}

    public static IrBlaster newInstance()
    {
        if (instance == null)
        {
            Log.i(TAG, "Creating new instanceof IrBlaster");
            instance = new IrBlaster();
        }

        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean sendIr(ConsumerIrManager irManager, int [] pattern)
    {
        if (irManager != null && irManager.hasIrEmitter()) {
            Log.i(TAG, "Device has IR Emitter");
            irManager.transmit(CARRIER_FREQUENCY, pattern);
            Log.i(TAG, "Successfully Sent IR Pattern");
            return true;
        }
        else {
            Log.i(TAG, "Device does not have IR");
            return false;
        }
    }

    private static IrBlaster instance;
    private static final String TAG = "IRBLASTER";
    private static final int CARRIER_FREQUENCY = 38000;
}
