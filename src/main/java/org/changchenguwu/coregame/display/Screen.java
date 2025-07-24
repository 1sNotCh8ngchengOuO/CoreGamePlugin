package org.changchenguwu.coregame.display;

import org.bukkit.entity.TextDisplay;

public interface Screen {
    String getDescription();               // 显示内容描述
    String getId();                         // 唯一标识
    void spawn(TextDisplay entity);             // 在世界中创建/display 实体
    void update();                          // 定时或事件驱动更新显示内容
    void remove();                          // 卸载时移除实体
}
