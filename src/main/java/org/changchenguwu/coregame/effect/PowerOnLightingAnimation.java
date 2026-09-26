package org.changchenguwu.coregame.effect;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.changchenguwu.coregame.core.Core;

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

    private static final int[][] BLOCK1_OFFSETS = {
            {2, -10, 0}, {4, -10, 0}, {6, -10, 0}, {8, -10, 0},
            {2, 10, 0}, {4, 10, 0}, {6, 10, 0}, {8, 10, 0}
    };

    private static final int[][] BLOCK2_OFFSETS = {
            {1, -10, -1}, {3, -10, -3}, {5, -10, -5},
            {1, 10, -1}, {3, 10, -3}, {5, 10, -5}
    };

    private static final int[][] BLOCK3_OFFSETS = {
            {0, -10, -2}, {0, -10, -4}, {0, -10, -6}, {0, -10, -8},
            {0, 10, -2}, {0, 10, -4}, {0, 10, -6}, {0, 10, -8}
    };

    private static final int[][] BLOCK4_OFFSETS = {
            {-5, -10, -5}, {-1, -10, -1}, {-3, -10, -3},
            {-5, 10, -5}, {-1, 10, -1}, {-3, 10, -3}
    };

    private static final int[][] BLOCK5_OFFSETS = {
            {-2, -10, 0}, {-4, -10, 0}, {-6, -10, 0}, {-8, -10, 0},
            {-2, 10, 0}, {-4, 10, 0}, {-6, 10, 0}, {-8, 10, 0}
    };

    private static final int[][] BLOCK6_OFFSETS = {
            {-5, -10, 5}, {-3, -10, 3}, {-1, -10, 1},
            {-5, 10, 5}, {-3, 10, 3}, {-1, 10, 1}
    };

    private static final int[][] BLOCK7_OFFSETS = {
            {0, -10, 8}, {0, -10, 6}, {0, -10, 4}, {0, -10, 2},
            {0, 10, 8}, {0, 10, 6}, {0, 10, 4}, {0, 10, 2}
    };

    private static final int[][] BLOCK8_OFFSETS = {
            {5, -10, 5}, {3, -10, 3}, {1, -10, 1},
            {5, 10, 5}, {3, 10, 3}, {1, 10, 1}
    };

    public static void setBlock1() {
        addBlocks(BLOCK1, BLOCK1_OFFSETS);
    }

    public static void setBlock2() {
        addBlocks(BLOCK2, BLOCK2_OFFSETS);
    }

    public static void setBlock3() {
        addBlocks(BLOCK3, BLOCK3_OFFSETS);
    }

    public static void setBlock4() {
        addBlocks(BLOCK4, BLOCK4_OFFSETS);
    }

    public static void setBlock5() {
        addBlocks(BLOCK5, BLOCK5_OFFSETS);
    }

    public static void setBlock6() {
        addBlocks(BLOCK6, BLOCK6_OFFSETS);
    }

    public static void setBlock7() {
        addBlocks(BLOCK7, BLOCK7_OFFSETS);
    }

    public static void setBlock8() {
        addBlocks(BLOCK8, BLOCK8_OFFSETS);
    }

    private static void addBlocks(List<Block> blocks, int[][] offsets) {
        Block coreBlock = Core.getCoreLocation().getBlock();
        for (int[] offset : offsets) {
            blocks.add(coreBlock.getRelative(offset[0], offset[1], offset[2]));
        }
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
                        Objects.requireNonNull(Core.getCoreLocation().getWorld()).playSound(Core.getCoreLocation(), Sound.BLOCK_PISTON_EXTEND,10,1);
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
