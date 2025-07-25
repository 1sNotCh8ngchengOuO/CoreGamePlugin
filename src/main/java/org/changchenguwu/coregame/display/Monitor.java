package org.changchenguwu.coregame.display;

import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;

import java.util.Objects;

public class Monitor implements Screen{

    private TextDisplay textDisplay;
    private final int id;
    private final Location location;
    private final String description;
    private String text;

    public Monitor(int id, Location location, String description, String text) {
        this.id = id;
        this.location = location;
        this.description = description;
        this.text = text;
    }

    @Override
    public String getType() {
        return "Monitor";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public TextDisplay spawn() {
        this.textDisplay = Objects.requireNonNull(location.getWorld()).spawn(location, TextDisplay.class, entity -> {
            entity.setText(text);
            entity.setBillboard(Display.Billboard.FIXED);
            entity.setShadowed(false);
        });
        return textDisplay;
    }

    @Override
    public void update() {
        textDisplay.setText(text);
    }

    @Override
    public void remove() {
        textDisplay.remove();
    }

    @Override
    public TextDisplay getTextDisplay() {
        return textDisplay;
    }
}
