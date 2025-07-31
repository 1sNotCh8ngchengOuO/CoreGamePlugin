package org.changchenguwu.coregame.display;

import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;

import java.util.List;

public interface Screen {
    String getType();
    String getDescription();
    int getId();
    String getUnFormatText();
    Location getLocation();
    TextDisplay spawn();
    void updateText(String text, List<String> textList);
    void remove();
    TextDisplay getTextDisplay();
}
