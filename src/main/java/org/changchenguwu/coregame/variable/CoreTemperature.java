package org.changchenguwu.coregame.variable;

import org.changchenguwu.coregame.corestate.TemperatureStatus;

public class CoreTemperature {
    private static int coreTemperature = 30;
    public static int heatUpTemp;

    public static void addTemperature(int temperature) {
        coreTemperature += temperature;
    }

    public static void reduceTemperature(int temperature) {
        coreTemperature -= temperature;
    }

    public static String getCoreTemperatureFormat() {
        return coreTemperature +"°C";
    }

    public static int getCoreTemperature() {
        return coreTemperature;
    }

    public static TemperatureStatus getTemperatureStatus() {
        if (coreTemperature <= -273) {
            return TemperatureStatus.FREEZE;
        } else if (coreTemperature <= 0) {
            return TemperatureStatus.SUPERCOOL;
        } else if (coreTemperature <= 3000) {
            return TemperatureStatus.NORMAL;
        } else if (coreTemperature <= 6000) {
            return TemperatureStatus.OVERHEAT;
        } else if (coreTemperature <= 10000) {
            return TemperatureStatus.CRITICAL;
        } else {
            return TemperatureStatus.MELTDOWN;
        }
    }
}
