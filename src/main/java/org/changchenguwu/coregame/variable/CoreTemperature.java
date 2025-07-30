package org.changchenguwu.coregame.variable;

public class CoreTemperature {
    private static int coreTemperature = 30;

    public static void addTemperature(int temperature) {
        coreTemperature += temperature;
    }

    public static void reduceTemperature(int temperature) {
        coreTemperature -= temperature;
    }

    public static int getCoreTemperature() {
        return coreTemperature;
    }
}
