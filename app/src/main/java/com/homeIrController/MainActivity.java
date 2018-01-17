package com.homeIrController;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.homeIrController.appliance.ElementTV;
import com.homeIrController.appliance.LaskoHeater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ConsumerIrManager consumerIrManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        consumerIrManager = (ConsumerIrManager) this.getSystemService(Context.CONSUMER_IR_SERVICE);
        startService(new Intent(this, RegistrationIntentService.class));
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void sendIr(View view) throws InterruptedException {

        if (consumerIrManager.hasIrEmitter())
        {
            Log.i(TAG, "Device has IR Emitter");

            int [] pattern = null;
            switch(view.getId())
            {
                case R.id.btnTVPower:
                    Log.i(TAG, "Button Pressed = TV POWER");
                    pattern = ElementTV.POWER;
                    break;
                case R.id.btnHeaterPower:
                    Log.i(TAG, "Button Pressed = TV POWER");
                    pattern = LaskoHeater.POWER;
                    break;
                case R.id.btnOscillate:
                    pattern = LaskoHeater.OSCILLATE;
                    break;
                case R.id.btnHeatUp:
                    pattern = LaskoHeater.HEAT_UP;
                    break;
                case R.id.btnHeatDown:
                    pattern = LaskoHeater.HEAT_DOWN;
                    break;
                case R.id.btnVolumeUp:
                    Log.i(TAG, "Button Pressed = VolumeUp");
                    pattern = ElementTV.VOLUME_UP;
                    break;
                case R.id.btnVolumeDown:
                    Log.i(TAG, "Button Pressed = VolumeDown");
                    pattern = ElementTV.VOLUME_DOWN;
                    break;
                default:
                    pattern = LaskoHeater.POWER;
            }

            for (int i = 0; i<3; i++)
            {
                Thread.sleep(5);
                consumerIrManager.transmit(CARRIER_FREQUENCY, pattern);
            }

            Log.i(TAG, "Successfully Sent IR Pattern");
        }
        else
        {
            Log.i(TAG, "Device DOES NOT have IR Emitter");
        }
    }



    protected String count2duration(String countPattern) {
        List<String> list = new ArrayList<String>(Arrays.asList(countPattern.split(",")));
        int frequency = Integer.parseInt(list.get(0));
        int pulses = 1000000/frequency;
        int count;
        int duration;

        list.remove(0);

        for (int i = 0; i < list.size(); i++) {
            count = Integer.parseInt(list.get(i));
            duration = count * pulses;
            list.set(i, Integer.toString(duration));
        }

        String durationPattern = "";
        for (String s : list) {
            durationPattern += s + ",";
        }

        Log.d(TAG, "Frequency: " + frequency);
        Log.d(TAG, "Duration Pattern: " + durationPattern);

        return durationPattern;
    }

    protected String hex2dec(String irData) {
        List<String> list = new ArrayList<String>(Arrays.asList(irData.split(" ")));
        list.remove(0); // dummy
        int frequency = Integer.parseInt(list.remove(0), 16); // frequency
        list.remove(0); // seq1
        list.remove(0); // seq2

        for (int i = 0; i < list.size(); i++) {
            list.set(i, Integer.toString(Integer.parseInt(list.get(i), 16)));
        }

        frequency = (int) (1000000 / (frequency * 0.241246));
        list.add(0, Integer.toString(frequency));

        irData = "";
        for (String s : list) {
            irData += s + ",";
        }
        return irData;
    }

    private static final int CARRIER_FREQUENCY = 38000;

    private static final String TAG = "HRISHI";
}
