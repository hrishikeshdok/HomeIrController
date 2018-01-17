package com.homeIrController.appliance;

/**
 * Created by hrishid on 1/8/18.
 */

public class ElementTV implements EntertainmentAppliance{
    @Override
    public int[] getPowerPattern() {
        return POWER;
    }

    @Override
    public int[] getVolumeUpPattern() {
        return VOLUME_UP;
    }

    @Override
    public int[] getVolumeDownPattern() {
        return VOLUME_DOWN;
    }

    @Override
    public int[] getMutePattern() {
        return MUTE;
    }

    @Override
    public String getName() {
        return ElementTV.class.getSimpleName();
    }

    private ElementTV()
    {}

    static ElementTV getInstance()
    {
        if (instance == null)
        {
            instance = new ElementTV();
        }
        return instance;
    }

    private static ElementTV instance;

    public static final int [] POWER = {8926, 4602, 486, 650, 486, 1754, 482, 650, 486, 650, 486, 646, 486, 650, 482, 650, 486, 650, 486, 1754, 486, 650, 482, 1758, 482, 1758, 486, 1758, 482, 1758, 486, 1754, 486, 650, 486, 650, 482, 1758, 486, 1758, 486, 646, 482, 650, 486, 650, 486, 1758, 482, 650, 482, 1758, 482, 654, 482, 650, 486, 1754, 486, 1754, 486, 1758, 486, 650, 482, 1758, 486};
    public static final int [] VOLUME_UP = {8918, 4610, 486, 650, 534, 1706, 486, 650, 482, 650, 486, 650, 482, 650, 538, 598, 482, 650, 482, 1758, 538, 598, 486, 1754, 486, 1758, 486, 1754, 538, 1706, 538, 1702, 542, 594, 534, 598, 534, 602, 482, 1758, 538, 1706, 534, 598, 538, 598, 486, 650, 534, 598, 538, 1702, 538, 1706, 534, 598, 538, 594, 542, 1702, 486, 1754, 538, 1702, 542, 1702, 538};
    public static final int [] VOLUME_DOWN = {8930, 4602, 538, 598, 538, 1702, 538, 598, 534, 602, 534, 598, 482, 654, 482, 650, 538, 598, 482, 1762, 482, 650, 482, 1758, 486, 1758, 482, 1758, 486, 1754, 538, 1706, 482, 654, 538, 1702, 538, 598, 534, 602, 482, 1758, 534, 1706, 486, 650, 538, 598, 482, 650, 486, 650, 482, 1758, 538, 1706, 538, 594, 538, 598, 486, 1758, 486, 1754, 486, 1758, 486};
    public static final int [] MUTE = {8926, 4602, 482, 650, 486, 1758, 482, 650, 486, 650, 486, 646, 486, 650, 482, 650, 486, 650, 482, 1758, 482, 650, 486, 1758, 482, 1758, 486, 1758, 482, 1762, 482, 1758, 482, 650, 486, 650, 482, 650, 482, 1762, 482, 1758, 482, 654, 482, 650, 486, 1758, 486, 646, 486, 1754, 486, 1758, 482, 650, 486, 650, 482, 1758, 486, 1754, 486, 650, 486, 1754, 486,
    };
}
