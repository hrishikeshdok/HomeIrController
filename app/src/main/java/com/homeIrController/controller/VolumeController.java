package com.homeIrController.controller;

import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.homeIrController.IrBlaster;
import com.homeIrController.appliance.ApplianceFactory;
import com.homeIrController.appliance.EntertainmentAppliance;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hrishid on 1/15/18.
 */

public class VolumeController implements ApplianceController {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void handle(JSONObject directive, ConsumerIrManager irManager) {
        Log.d(TAG, "Received request to handle VOLUME " + directive.toString());
        try {
            JSONObject header = directive.getJSONObject("header");
            JSONObject endpointObject = directive.getJSONObject("endpoint");
            JSONObject payloadObject = directive.getJSONObject("payload");

            String applianceId = endpointObject.getString("endpointId");
            EntertainmentAppliance appliance = (EntertainmentAppliance) ApplianceFactory.getAppliance(applianceId);
            if (appliance == null)
            {
                Log.e(TAG, "No Appliance received");
                return;
            }
            Log.d(TAG, "Received Appliance " + appliance.getName());

            String directiveName = header.getString("name");
            Log.d(TAG, "DirectiveName = " + directiveName);
            if ("SetVolume".equals(directiveName))
            {
                //Set volume to 0
                Log.d(TAG, "Lowering Volume to 0");
                int [] volumeDownpattern = appliance.getVolumeDownPattern();
                int [] volumeUpPattern = appliance.getVolumeDownPattern();
                for (int i = 0; i<20; i++)
                {
                    Thread.sleep(10);
                    irBlaster.sendIr(irManager, volumeDownpattern);
                }

                //Set volume to desired value
                int desiredVolume = payloadObject.getInt("volume");
                Log.d(TAG, "Raising to DesiredVolume = " + desiredVolume);
                for (int i = 0; i<Math.abs(desiredVolume); i++)
                {
                    Thread.sleep(10);
                    irBlaster.sendIr(irManager, volumeUpPattern);
                }
            }
            else if ("SetMute".equals(directiveName))
            {
                Log.d(TAG, "Sending Mute");
                int [] mutePattern = appliance.getMutePattern();
                irBlaster.sendIr(irManager, mutePattern);
            }
            else if ("AdjustVolume".equals(directiveName))
            {
                int volumeSteps = payloadObject.getInt("volume");
                boolean volumeDefault = payloadObject.getBoolean("volumeDefault");
                Log.d(TAG, "Volume Steps = " + volumeSteps);
                Log.d(TAG, "DefaultVolume? = " + volumeDefault);
                int [] volumePattern = null;

                if (volumeSteps < 0) {
                    volumePattern = appliance.getVolumeDownPattern();
                } else
                {
                    volumePattern = appliance.getVolumeUpPattern();
                }

                if (volumeDefault)
                {
                    Log.d(TAG, "Changing volume by default value " + volumeSteps);
                    for (int i = 0; i<3; i++)
                    {
                        Thread.sleep(5);
                        irBlaster.sendIr(irManager, volumePattern);
                    }
                }
                else {
                    for (int i = 0; i<Math.abs(volumeSteps); i++)
                    {
                        Thread.sleep(5);
                        irBlaster.sendIr(irManager, volumePattern);
                    }
                }
            }
        } catch (JSONException e) {
            Log.e(TAG,"Error parsing JSON " + directive.toString());
        } catch (InterruptedException e) {
            Log.e(TAG, "Thread Exception", e);
        }
    }

    static VolumeController getInstance()
    {
        if (instance == null)
        {
            instance = new VolumeController();
            irBlaster = IrBlaster.newInstance();
        }
        return instance;
    }

    @Override
    public String getName() {
        return VolumeController.class.getSimpleName();
    }

    private VolumeController()
    {}

    private static VolumeController instance;
    private static IrBlaster irBlaster;
    private static final String TAG = "VolumeController";
}
