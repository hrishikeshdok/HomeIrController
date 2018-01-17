package com.homeIrController.appliance;

/**
 * Created by hrishid on 1/15/18.
 */

public interface EntertainmentAppliance extends Appliance{
    int [] getVolumeUpPattern();
    int [] getVolumeDownPattern();
    int [] getMutePattern();
}
