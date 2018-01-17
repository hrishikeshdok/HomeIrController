package com.homeIrController.controller;

import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.homeIrController.IrBlaster;
import com.homeIrController.appliance.Appliance;
import com.homeIrController.appliance.ApplianceFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hrishid on 1/15/18.
 */
public class PowerController implements ApplianceController {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void handle(JSONObject directive, ConsumerIrManager irManager) {
        Log.d(TAG, "Received request to handle POWER " + directive.toString());
        try {
            JSONObject endpointObject = directive.getJSONObject("endpoint");
            String applianceId = endpointObject.getString("endpointId");
            Appliance appliance = ApplianceFactory.getAppliance(applianceId);
            if (appliance == null)
            {
                Log.e(TAG, "No Appliance received");
                return;
            }
            Log.d(TAG, "Received Appliance " + appliance.getName());
            int [] powerPatternForAppliance = appliance.getPowerPattern();
            irBlaster.sendIr(irManager, powerPatternForAppliance);
        } catch (JSONException e) {
            Log.e(TAG,"Error parsing JSON " + directive.toString());
        }
    }

    static PowerController getInstance()
    {
        if (instance == null)
        {
            instance = new PowerController();
            irBlaster = IrBlaster.newInstance();
        }
        return instance;
    }

    @Override
    public String getName() {
        return PowerController.class.getSimpleName();
    }

    private PowerController()
    {}

    private static PowerController instance;
    private static IrBlaster irBlaster;
    private static final String TAG = "PowerController";
}
