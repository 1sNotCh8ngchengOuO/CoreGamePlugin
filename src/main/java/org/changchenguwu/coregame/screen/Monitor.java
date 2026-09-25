package org.changchenguwu.coregame.screen;

import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.changchenguwu.coregame.CoreGame;

import java.util.List;
import java.util.Objects;

public class Monitor implements Screen{

    private TextDisplay textDisplay;
    private final int id;
    private final Location location;
    private final String description;
    private String unFormatText;
    private final int pitch;
    private final int yaw;
    private final String billboard;
    private final boolean state;
    private String strategy;
    private int interval;

    public boolean isState() {
        return state;
    }


    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }


    public void setInterval(int interval) {
        this.interval = interval;
    }


    private int i = 0;
    private int index = 0;

    BukkitTask task;
    List<String> textList;

    public void setSpecialTextIndex(int[] specialTextIndex) {
        this.specialTextIndex = specialTextIndex;
    }

    private int[] specialTextIndex;

    public Monitor(int id, Location location, String description, String unFormatText, int pitch, int yaw, String billboard, String strategy, int interval, List<String> textList, int[] specialTextIndex ,boolean state) {
        this.id = id;
        this.location = location;
        this.description = description;
        this.unFormatText = unFormatText;
        this.pitch = pitch;
        this.yaw = yaw;
        this.billboard = billboard;
        this.strategy = strategy;
        this.interval = interval;
        this.textList = textList;
        this.specialTextIndex = specialTextIndex;
        this.state = state;
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
    public String getUnFormatText() {
        return unFormatText;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public TextDisplay spawn() {
        this.textDisplay = Objects.requireNonNull(location.getWorld()).spawn(location, TextDisplay.class, entity -> {
            entity.setText(ScreenTextFormatter.replaceDisplayText(unFormatText));
            entity.setBillboard(Display.Billboard.valueOf(billboard));
            entity.setShadowed(false);
            entity.setRotation(yaw,pitch);
            entity.setLineWidth(1000000000);
        });
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (textDisplay == null || textDisplay.isDead()) {
                    this.cancel();
                    return;
                }
                String next;
                switch (strategy.toUpperCase()) {
                    case "SPECIAL":
                        next = ScreenManager.specialText.get(i);
                        textDisplay.setText(ScreenTextFormatter.replaceDisplayText(next));
                        i = (i+1) % specialTextIndex.length;
                        break;
                    case "SINGLE":
                        next = unFormatText;
                        textDisplay.setText(ScreenTextFormatter.replaceDisplayText(next));
                        break;
                    case "ROUND_ROBIN":
                        index = (index + 1) % textList.size();
                        next = textList.get(index);
                        textDisplay.setText(ScreenTextFormatter.replaceDisplayText(next));
                        break;
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(CoreGame.class), interval, interval);
        return textDisplay;
    }

    @Override
    public void updateText(String text, List<String> textList) {
        if(textList != null){
            this.textList = textList;
        }else{
            this.unFormatText = text;
        }
    }

    @Override
    public void remove() {
        if (textDisplay != null && !textDisplay.isDead()) {
            textDisplay.remove();
        }

        if (task != null && !task.isCancelled()) {
            task.cancel();
        }
    }

    @Override
    public TextDisplay getTextDisplay() {
        return textDisplay;
    }
}

