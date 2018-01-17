package com.homeIrController.appliance;

/**
 * Created by hrishid on 1/15/18.
 */

public interface HeatAppliance extends Appliance{
    int [] getOscillatePattern();
    int [] getHeatUpPattern();
    int [] getHeatDownPattern();
}
