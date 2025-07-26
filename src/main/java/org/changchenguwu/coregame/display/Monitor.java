package org.changchenguwu.coregame.display;

import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.changchenguwu.coregame.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Monitor implements Screen{

    private TextDisplay textDisplay;
    private final int id;
    private final Location location;
    private final String description;
    private final String text;
    private final int pitch;
    private final int yaw;
    private final String billboard;

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    private String strategy;

    public void setInterval(int interval) {
        this.interval = interval;
    }

    private int interval;

    private int i = 0;
    private int index = 0;

    BukkitTask task;
    List<String> textList;

    public void setSpecialTextIndex(int[] specialTextIndex) {
        this.specialTextIndex = specialTextIndex;
    }

    private int[] specialTextIndex;

    public Monitor(int id, Location location, String description, String text, int pitch, int yaw, String billboard, String strategy, int interval, List<String> textList,int[] specialTextIndex) {
        this.id = id;
        this.location = location;
        this.description = description;
        this.text = formatText(text);
        this.pitch = pitch;
        this.yaw = yaw;
        this.billboard = billboard;
        this.strategy = strategy;
        this.interval = interval;
        this.textList = textList;
        this.specialTextIndex = specialTextIndex;
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
    public TextDisplay spawn() {
        this.textDisplay = Objects.requireNonNull(location.getWorld()).spawn(location, TextDisplay.class, entity -> {
            entity.setText(text);
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
                        textDisplay.setText(formatText(next));
                        i = (i+1) % specialTextIndex.length;
                        break;
                    case "SINGLE":
                        next = text;
                        textDisplay.setText(formatText(next));
                        break;
                    case "ROUND_ROBIN":
                        index = (index + 1) % textList.size();
                        next = textList.get(index);
                        textDisplay.setText(formatText(next));
                        break;
                }
            }
        }.runTaskTimer(JavaPlugin.getPlugin(main.class), interval, interval);
        return textDisplay;
    }

    @Override
    public void update(String text,List<String> textList) {
        if(textList != null){
            this.textList = textList;
        }else{
            textDisplay.setText(formatText(text));
        }
    }

    @Override
    public void remove() {
        textDisplay.remove();
    }

    @Override
    public TextDisplay getTextDisplay() {
        return textDisplay;
    }

    public String formatText(String text) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return text.replace("<data>",formatter.format(date));
    }
}
