package org.changchenguwu.coregame.display;

import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;

public interface Screen {
    String getType();
    String getDescription();
    int getId();
    String getText();
    Location getLocation();
    void setText(String text);
    TextDisplay spawn();
    void update();
    void remove();
    TextDisplay getTextDisplay();
}
