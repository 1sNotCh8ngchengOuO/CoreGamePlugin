package org.changchenguwu.coregame.display;

import org.changchenguwu.coregame.variable.CoreTemperature;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatText {
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static Date date = new Date();

    public static String replaceDisplayText(String text) {
        return text
                .replace("<data>",formatter.format(date))
                .replace("<coretemp>", String.valueOf(CoreTemperature.getCoreTemperature()));
    }
}
