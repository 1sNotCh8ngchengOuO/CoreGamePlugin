package org.changchenguwu.coregame.display;

import org.changchenguwu.coregame.corestate.Core;
import org.changchenguwu.coregame.variable.CoreTemperature;
import org.changchenguwu.coregame.corestate.TemperatureStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatText {


    public static String replaceDisplayText(String text) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return text
                .replace("<date>",formatter.format(date))
                .replace("<coretemp>", getColoredCoreTemperature())
                .replace("<corestartinfo>", Core.getCoreStartupInfo());
    }

    private static String getColoredCoreTemperature() {
        TemperatureStatus status = CoreTemperature.getTemperatureStatusAndCallEvent();
        String temperature = CoreTemperature.getCoreTemperatureFormat();
        return switch (status) {
            case FREEZE -> "§9" + temperature; // 蓝色
            case SUPERCOOL -> "§b" + temperature; // 深蓝色
            case NORMAL -> "§a" + temperature; // 绿色
            case OVERHEAT -> "§6" + temperature; // 金色/橙色
            case CRITICAL -> "§c" + temperature; // 红色
            case MELTDOWN -> "§4" + temperature; // 深红色
        };
    }
}