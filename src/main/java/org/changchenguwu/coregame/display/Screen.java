package org.changchenguwu.coregame.display;

import org.bukkit.Location;

public interface Screen {
    String getType();
    String getDescription();
    int getId();
    String getText();
    Location getLocation();
    void setText(String text);
    void spawn();
    void update();
    void remove();
}
