package org.changchenguwu.coregame.screen;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.changchenguwu.coregame.core.Core;
import org.changchenguwu.coregame.util.Locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PowerOnLightingAnimation {

    private static final List<Block> BLOCK1 = new ArrayList<>();
    private static final List<Block> BLOCK2 = new ArrayList<>();
    private static final List<Block> BLOCK3 = new ArrayList<>();
    private static final List<Block> BLOCK4 = new ArrayList<>();
    private static final List<Block> BLOCK5 = new ArrayList<>();
    private static final List<Block> BLOCK6 = new ArrayList<>();
    private static final List<Block> BLOCK7 = new ArrayList<>();
    private static final List<Block> BLOCK8 = new ArrayList<>();

    public static void init() {
        setBlock1();
        setBlock2();
        setBlock3();
        setBlock4();
        setBlock5();
        setBlock6();
        setBlock7();
        setBlock8();
    }

    public static void setBlock1() {
        BLOCK1.add(Locations.newLocation("flat",-1366.0,-56.0,251.0).getBlock());
        BLOCK1.add(Locations.newLocation("flat",-1364.0,-56.0,251.0).getBlock());
        BLOCK1.add(Locations.newLocation("flat",-1362.0,-56.0,251.0).getBlock());
        BLOCK1.add(Locations.newLocation("flat",-1360.0,-56.0,251.0).getBlock());

        BLOCK1.add(Locations.newLocation("flat",-1366.0,-36.0,251.0).getBlock());
        BLOCK1.add(Locations.newLocation("flat",-1364.0,-36.0,251.0).getBlock());
        BLOCK1.add(Locations.newLocation("flat",-1362.0,-36.0,251.0).getBlock());
        BLOCK1.add(Locations.newLocation("flat",-1360.0,-36.0,251.0).getBlock());
    }

    public static void setBlock2() {
        BLOCK2.add(Locations.newLocation("flat",-1367.0,-56.0,250.0).getBlock());
        BLOCK2.add(Locations.newLocation("flat", -1365.0, -56.0, 248.0).getBlock());
        BLOCK2.add(Locations.newLocation("flat", -1363.0, -56.0, 246.0).getBlock());

        BLOCK2.add(Locations.newLocation("flat",-1367.0,-36.0,250.0).getBlock());
        BLOCK2.add(Locations.newLocation("flat", -1365.0, -36.0, 248.0).getBlock());
        BLOCK2.add(Locations.newLocation("flat", -1363.0, -36.0, 246.0).getBlock());
    }

    public static void setBlock3() {
        BLOCK3.add(Locations.newLocation("flat", -1368.0, -56.0, 249.0).getBlock());
        BLOCK3.add(Locations.newLocation("flat", -1368.0, -56.0, 247.0).getBlock());
        BLOCK3.add(Locations.newLocation("flat", -1368.0, -56.0, 245.0).getBlock());
        BLOCK3.add(Locations.newLocation("flat", -1368.0, -56.0, 243.0).getBlock());

        BLOCK3.add(Locations.newLocation("flat", -1368.0, -36.0, 249.0).getBlock());
        BLOCK3.add(Locations.newLocation("flat", -1368.0, -36.0, 247.0).getBlock());
        BLOCK3.add(Locations.newLocation("flat", -1368.0, -36.0, 245.0).getBlock());
        BLOCK3.add(Locations.newLocation("flat", -1368.0, -36.0, 243.0).getBlock());
    }

    public static void setBlock4() {
        BLOCK4.add(Locations.newLocation("flat",-1373.0,-56.0,246.0).getBlock());
        BLOCK4.add(Locations.newLocation("flat",-1369.0,-56.0,250.0).getBlock());
        BLOCK4.add(Locations.newLocation("flat",-1371.0,-56.0,248.0).getBlock());

        BLOCK4.add(Locations.newLocation("flat",-1373.0,-36.0,246.0).getBlock());
        BLOCK4.add(Locations.newLocation("flat",-1369.0,-36.0,250.0).getBlock());
        BLOCK4.add(Locations.newLocation("flat",-1371.0,-36.0,248.0).getBlock());
    }

    public static void setBlock5() {
        BLOCK5.add(Locations.newLocation("flat", -1370.0, -56.0, 251.0).getBlock());
        BLOCK5.add(Locations.newLocation("flat",-1372.0,-56.0,251.0).getBlock());
        BLOCK5.add(Locations.newLocation("flat",-1374.0,-56.0,251.0).getBlock());
        BLOCK5.add(Locations.newLocation("flat",-1376.0,-56.0,251.0).getBlock());

        BLOCK5.add(Locations.newLocation("flat", -1370.0, -36.0, 251.0).getBlock());
        BLOCK5.add(Locations.newLocation("flat",-1372.0,-36.0,251.0).getBlock());
        BLOCK5.add(Locations.newLocation("flat",-1374.0,-36.0,251.0).getBlock());
        BLOCK5.add(Locations.newLocation("flat",-1376.0,-36.0,251.0).getBlock());
    }

    public static void setBlock6() {
        BLOCK6.add(Locations.newLocation("flat",-1373.0,-56.0,256.0).getBlock());
        BLOCK6.add(Locations.newLocation("flat",-1371.0,-56.0,254.0).getBlock());
        BLOCK6.add(Locations.newLocation("flat",-1369.0,-56.0,252.0).getBlock());

        BLOCK6.add(Locations.newLocation("flat",-1373.0,-36.0,256.0).getBlock());
        BLOCK6.add(Locations.newLocation("flat",-1371.0,-36.0,254.0).getBlock());
        BLOCK6.add(Locations.newLocation("flat",-1369.0,-36.0,252.0).getBlock());
    }

    public static void setBlock7() {
        BLOCK7.add(Locations.newLocation("flat",-1368.0,-56.0,259.0).getBlock());
        BLOCK7.add(Locations.newLocation("flat",-1368.0,-56.0,257.0).getBlock());
        BLOCK7.add(Locations.newLocation("flat",-1368.0,-56.0,255.0).getBlock());
        BLOCK7.add(Locations.newLocation("flat",-1368.0,-56.0,253.0).getBlock());

        BLOCK7.add(Locations.newLocation("flat",-1368.0,-36.0,259.0).getBlock());
        BLOCK7.add(Locations.newLocation("flat",-1368.0,-36.0,257.0).getBlock());
        BLOCK7.add(Locations.newLocation("flat",-1368.0,-36.0,255.0).getBlock());
        BLOCK7.add(Locations.newLocation("flat",-1368.0,-36.0,253.0).getBlock());
    }

    public static void setBlock8() {
        BLOCK8.add(Locations.newLocation("flat",-1363.0,-56.0,256.0).getBlock());
        BLOCK8.add(Locations.newLocation("flat",-1365.0,-56.0,254.0).getBlock());
        BLOCK8.add(Locations.newLocation("flat",-1367.0,-56.0,252.0).getBlock());

        BLOCK8.add(Locations.newLocation("flat",-1363.0,-36.0,256.0).getBlock());
        BLOCK8.add(Locations.newLocation("flat",-1365.0,-36.0,254.0).getBlock());
        BLOCK8.add(Locations.newLocation("flat",-1367.0,-36.0,252.0).getBlock());
    }

    public static void animation(JavaPlugin plugin) {
        List<List<Block>> allBlocks = new ArrayList<>();
        allBlocks.add(BLOCK1);
        allBlocks.add(BLOCK2);
        allBlocks.add(BLOCK3);
        allBlocks.add(BLOCK4);
        allBlocks.add(BLOCK5);
        allBlocks.add(BLOCK6);
        allBlocks.add(BLOCK7);
        allBlocks.add(BLOCK8);

        new BukkitRunnable(){
            @Override
            public void run() {

                new BukkitRunnable() {
                    int currentBlockListIndex = 0;
                    @Override
                    public void run() {
                        if (currentBlockListIndex >= allBlocks.size()) {
                            Objects.requireNonNull(Core.getCoreLocation().getWorld()).playSound(Core.getCoreLocation(), Sound.BLOCK_NOTE_BLOCK_BELL,3,7);
                            Core.setCouldStart(true);
                            this.cancel();
                            return;
                        }

                        List<Block> blocksToLight = allBlocks.get(currentBlockListIndex);
                        for (Block block : blocksToLight) {
                            block.setType(Material.SEA_LANTERN);
                        }
                        currentBlockListIndex++;
                        Objects.requireNonNull(Core.getCoreLocation().getWorld()).playSound(Core.getCoreLocation(), Sound.BLOCK_PISTON_EXTEND,3,1);
                    }
                }.runTaskTimer(plugin, 0L, 10L);

            }
        }.runTaskLater(plugin,20L);
    }

    public static void restoreAnimation(JavaPlugin plugin) {
        List<List<Block>> allBlocks = new ArrayList<>();
        allBlocks.add(BLOCK1);
        allBlocks.add(BLOCK2);
        allBlocks.add(BLOCK3);
        allBlocks.add(BLOCK4);
        allBlocks.add(BLOCK5);
        allBlocks.add(BLOCK6);
        allBlocks.add(BLOCK7);
        allBlocks.add(BLOCK8);

        new BukkitRunnable() {
            int currentBlockListIndex = 0;

            @Override
            public void run() {
                if (currentBlockListIndex >= allBlocks.size()) {
                    this.cancel();
                    return;
                }

                List<Block> blocksToRestore = allBlocks.get(currentBlockListIndex);
                for (Block block : blocksToRestore) {
                    block.setType(Material.LIGHT_BLUE_TERRACOTTA);
                }
                currentBlockListIndex++;
            }
        }.runTaskTimer(plugin, 0L, 10L);
    }
}
