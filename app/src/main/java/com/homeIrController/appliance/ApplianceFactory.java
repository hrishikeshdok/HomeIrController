package com.homeIrController.appliance;

/**
 * Created by hrishid on 1/15/18.
 */

public class ApplianceFactory {
    public static Appliance getAppliance(String endpointId)
    {
        if ("appliance_001".equals(endpointId))
        {
            return LaskoHeater.getInstance();
        }
        else if ("appliance_002".equals(endpointId))
        {
            return ElementTV.getInstance();
        }
        return null;
    }
}
