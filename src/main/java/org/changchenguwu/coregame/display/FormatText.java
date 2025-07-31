package org.changchenguwu.coregame.display;

import org.changchenguwu.coregame.corestate.Core;
import org.changchenguwu.coregame.variable.CoreTemperature;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatText {


    public static String replaceDisplayText(String text) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return text
                .replace("<data>",formatter.format(date))
                .replace("<coretemp>", String.valueOf(CoreTemperature.getCoreTemperature()))
                .replace("<corestartinfo>", Core.getCoreStartupInfo());
    }
}
