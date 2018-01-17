package com.homeIrController.controller;

import android.hardware.ConsumerIrManager;

import org.json.JSONObject;

/**
 * Created by hrishid on 1/15/18.
 */
public interface ApplianceController {
    void handle(JSONObject directive, ConsumerIrManager irManager);
    String getName();
}
