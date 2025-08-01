package org.changchenguwu.coregame.tools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationBuilder {

    public static Location newLocation(String world,double x, double y, double z) {
        World world1 = Bukkit.getWorld(world);
        if (world1 == null) {
            throw new IllegalArgumentException("World not found");
        }
        return new Location(world1,x,y,z);
    }
}
