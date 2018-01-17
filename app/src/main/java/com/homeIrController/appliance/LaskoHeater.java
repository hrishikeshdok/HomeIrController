package com.homeIrController.appliance;

/**
 * Created by hrishid on 1/8/18.
 */

public class LaskoHeater implements HeatAppliance{

    @Override
    public int[] getPowerPattern() {
        return POWER;
    }

    @Override
    public int[] getOscillatePattern() {
        return OSCILLATE;
    }

    @Override
    public int[] getHeatUpPattern() {
        return HEAT_UP;
    }

    @Override
    public int[] getHeatDownPattern() {
        return HEAT_DOWN;
    }

    @Override
    public String getName() {
        return LaskoHeater.class.getSimpleName();
    }

    private LaskoHeater()
    {}

    public static LaskoHeater getInstance()
    {
        if (instance == null)
        {
            instance = new LaskoHeater();
        }
        return instance;
    }

    private static LaskoHeater instance;
    

    public static final int [] POWER_1 = {1242,454,390,1302,1242,458,1238,454,394,1298,394,1302,394,1298,398,1298,394,1298,398,1298,1242,7386,1242,458,1238,454,394,1298,1242,454,1242,454,394,1298,394,1298,394,1302,394,1298,394,1302,394,1298,1242,7386,1242,458,1238,454,394,1298,1242,454,1242,454,394,1298,394,1298,398,1298,394,1298,398,1298,394,1298,1246,7378,1246,454,1242,454,394,1294,1246,454,1242,450,398,1294,398,1294,398,1298,398,1294,398,1298,394,1298,1246,7378,1246,454};

    public static final int [] POWER = {1230, 470, 1170, 522, 382, 1310, 1226, 470, 1226, 466, 382, 1314, 382, 1310, 382, 1310, 386, 1310, 382, 1310, 382, 1310, 1174, 7450, 1226, 470, 1230, 462, 382, 1310, 1226, 470, 1226, 470, 378, 1314, 378, 1314, 382, 1314, 378, 1314, 378, 1314, 378, 1314, 1230, 7390, 1230, 470, 1170, 522, 326, 1366, 1226, 470, 1174, 518, 382, 1310, 382, 1310, 386, 1310, 378, 1314, 378, 1314, 382, 1314, 1174, 7446, 1222, 470, 1230, 466, 378, 1314, 1174, 518, 1226, 470, 378, 1314, 378, 1314, 326, 1366, 382, 1310, 382, 1310, 326, 1370, 1170, 7450, 1174, 518, 1178,};

    public static final int [] OSCILLATE = {1230, 466, 1170, 526, 322, 1366, 1174, 526, 1166, 526, 322, 1366, 350, 1342, 326, 1366, 1174, 526, 318, 1370, 326, 1366, 326};

    public static final int [] HEAT_UP = {1198, 502, 1194, 498, 350, 1342, 1198, 498, 1194, 498, 326, 1366, 350, 1342, 1198, 498, 350, 1342, 350, 1342, 326, 1366, 354};

    public static final int [] HEAT_DOWN = {1174, 522, 1170, 522, 330, 1362, 1226, 470, 1174, 518, 330, 1366, 1174, 518, 326, 1366, 378, 1314, 382, 1310, 326, 1366, 382};
}
