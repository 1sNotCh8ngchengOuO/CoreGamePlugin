package org.changchenguwu.coregame.effect;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ScreenShakeEffect {

    private static final Map<UUID, BukkitTask> ACTIVE_SHAKES = new HashMap<>();

    public static void startScreenShake(JavaPlugin plugin, Player player, double intensity, long durationTicks) {
        stopScreenShake(player);

        UUID playerId = player.getUniqueId();

        BukkitTask task = new BukkitRunnable() {
            private final long startTime = System.currentTimeMillis();
            private final long endTime = startTime + (durationTicks * 50); // Convert ticks to milliseconds

            @Override
            public void run() {
                if (!player.isOnline() || System.currentTimeMillis() > endTime) {
                    stopScreenShake(player);
                    return;
                }

                Location originalLoc = player.getLocation();
                ThreadLocalRandom random = ThreadLocalRandom.current();

                // 计算新的视角偏移
                double offsetPitch = (random.nextDouble() * 2 - 1) * intensity * 5; // 增加抖动幅度
                double offsetYaw = (random.nextDouble() * 2 - 1) * intensity * 5; // 增加抖动幅度

                PacketContainer positionPacket = new PacketContainer(PacketType.Play.Server.POSITION);


                boolean rotationOnly = false;
                Set relativeSet = positionPacket.getSpecificModifier(Set.class).readSafely(0);
                if (relativeSet != null && RelativeMovement.isResolved()) {
                    try {
                        relativeSet.clear();
                        relativeSet.add(RelativeMovement.X);
                        relativeSet.add(RelativeMovement.Y);
                        relativeSet.add(RelativeMovement.Z);
                        rotationOnly = true;
                    } catch (Exception ignored) {
                    }
                }

                if (rotationOnly) {
                    positionPacket.getDoubles().write(0, 0.0);
                    positionPacket.getDoubles().write(1, 0.0);
                    positionPacket.getDoubles().write(2, 0.0);
                } else {
                    positionPacket.getDoubles().write(0, originalLoc.getX());
                    positionPacket.getDoubles().write(1, originalLoc.getY());
                    positionPacket.getDoubles().write(2, originalLoc.getZ());
                }

                positionPacket.getFloat().write(0, (float) (originalLoc.getYaw() + offsetYaw));
                positionPacket.getFloat().write(1, (float) (originalLoc.getPitch() + offsetPitch));

                try {
                    ProtocolLibrary.getProtocolManager().sendServerPacket(player, positionPacket);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.runTaskTimer(plugin, 0L, 1L); // Run every tick

        ACTIVE_SHAKES.put(playerId, task);
    }

    public static void stopScreenShake(Player player) {
        BukkitTask task = ACTIVE_SHAKES.remove(player.getUniqueId());
        if (task != null) {
            task.cancel();
            // 抖动停止后，将玩家视角重置为正常状态
            // 发送一个不带任何偏移的POSITION数据包来重置视角
            PacketContainer resetPacket = new PacketContainer(PacketType.Play.Server.POSITION);
            resetPacket.getDoubles().write(0, player.getLocation().getX());
            resetPacket.getDoubles().write(1, player.getLocation().getY());
            resetPacket.getDoubles().write(2, player.getLocation().getZ());
            resetPacket.getFloat().write(0, player.getLocation().getYaw());
            resetPacket.getFloat().write(1, player.getLocation().getPitch());

            try {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, resetPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final class RelativeMovement {

        private static final Object X;
        private static final Object Y;
        private static final Object Z;

        static {
            Object x = null;
            Object y = null;
            Object z = null;

            Class<?> enumClass = null;
            try {
                PacketContainer probe = new PacketContainer(PacketType.Play.Server.POSITION);
                Set<?> probeSet = probe.getSpecificModifier(Set.class).readSafely(0);
                if (probeSet != null && !probeSet.isEmpty()) {
                    enumClass = ((Enum<?>) probeSet.iterator().next()).getDeclaringClass();
                }
            } catch (Throwable ignored) {
            }

            if (enumClass != null && enumClass.isEnum()) {
                for (Method method : enumClass.getDeclaredMethods()) {
                    if (method.getParameterCount() != 0 || method.getReturnType() != int.class
                            || Modifier.isStatic(method.getModifiers())) {
                        continue;
                    }
                    try {
                        method.setAccessible(true);
                        Object candidateX = null;
                        Object candidateY = null;
                        Object candidateZ = null;
                        for (Object constant : enumClass.getEnumConstants()) {
                            int mask = (Integer) method.invoke(constant);
                            if (mask == 0x1) {
                                candidateX = constant;
                            } else if (mask == 0x2) {
                                candidateY = constant;
                            } else if (mask == 0x4) {
                                candidateZ = constant;
                            }
                        }
                        if (candidateX != null && candidateY != null && candidateZ != null) {
                            x = candidateX;
                            y = candidateY;
                            z = candidateZ;
                            break;
                        }
                    } catch (Throwable ignored) {
                    }
                }
            }

            X = x;
            Y = y;
            Z = z;
        }

        private static boolean isResolved() {
            return X != null && Y != null && Z != null;
        }
    }
}
