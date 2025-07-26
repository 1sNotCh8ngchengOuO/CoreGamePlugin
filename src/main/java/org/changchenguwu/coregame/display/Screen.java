package org.changchenguwu.coregame.display;

import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;

import java.util.List;

public interface Screen {
    String getType();
    String getDescription();
    int getId();
    String getText();
    Location getLocation();
    void setText(String text);
    TextDisplay spawn();
    void update(String text, List<String> textList);
    void remove();
    TextDisplay getTextDisplay();
}
