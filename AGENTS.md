# CoreGame

Minecraft Spigot 服务端插件。核心玩法围绕一个"核心"方块展开：核心有温度系统与状态机，围绕它实现屏幕显示、灯光动画、队伍选择、权限卡与名字牌。
(以上仅仅代表当前进度不代表最终玩法)

对话结束后总结修改该AGENTS.md 以确保其他对话能够同步项目进度。
## 技术栈

| 项 | 值 |
|---|---|
| 语言 / JDK | Java 21（JDK 21.0.12） |
| 构建 | Maven 3.9.16 —— `C:\Program Files\apache-maven-3.9.16\bin\mvn.cmd` |
| 坐标 | `org.changchenguwu:coregame:1.0`，packaging `jar` |
| 服务端 API | `spigot-api` `1.21.1-R0.1-SNAPSHOT`（provided） |
| 硬依赖 | ProtocolLib `5.3.0`（provided，`plugin.yml` 的 `depend`） |
| 软依赖 | PlaceholderAPI `2.11.6`（provided，`plugin.yml` 的 `softdepend`） |
| 打包进 jar | SignGUI `2.5.3`（由 shade 插件打入） |
| `api-version` | `plugin.yml` 声明为 `'1.20'`，而编译目标是 1.21.1 API —— 这是现状，不要当成笔误去改 |
| 源码编码 | UTF-8 **无 BOM** + CRLF，含中文注释。请保持原样 |

## 包结构 → 职责

根包 `org.changchenguwu.coregame`，共 55 个源文件 / 17 个包。

| 包 | 文件数 | 职责 |
|---|---|---|
| （根） | 1 | `CoreGame` —— 插件主类 |
| `command` | 13 | 全部指令：温度、队伍、屏幕、灯光动画、名字牌、音效、物品 |
| `core` | 5 | 核心本体：`Core`、`CoreTemperature`、`TemperatureStatus`、启动与就绪监听 |
| `effect` | 3 | 视觉效果：核心外壳变化、开机灯光动画、屏幕震动 |
| `event` | 1 | `CoreShutdownEvent` |
| `event.commonevent` | 3 | 通用保护监听：防破坏、防交互、防 PVP |
| `event.coreevent` | 1 | `CoreStartupEvent` |
| `event.interactive.permissioninteract` | 2 | 交互权限：`Permission` 与 `PermissionTestToolPermission` |
| `event.temperature` | 6 | 温度事件：临界 / 冻结 / 熔毁 / 正常 / 过热 / 过冷 |
| `gui.guibuilder` | 1 | `AllPluginItemsMenu` —— 全物品菜单构建 |
| `gui.guilistener` | 2 | GUI 交互监听：管理屏幕、管理物品堆 |
| `item` | 2 | 物品定义：`PermissionCard`、`ScreenToolItems` |
| `nametag` | 2 | 名字牌数据与 `NameTagManager` |
| `placeholder` | 1 | PlaceholderAPI 变量扩展 |
| `screen` | 5 | 屏幕系统：`Screen`、`Monitor`、`ScreenManager`、`ScreenSpawner`、`ScreenTextFormatter` |
| `team` | 6 | 队伍：`Engineer` / `Scientist` / `Security` 三个职业 + 选择/注册监听 + 工具类 |
| `util` | 1 | `Locations` 坐标工具 |

## 构建与验证

```powershell
# 构建（pom.xml 的 defaultGoal 已是 clean package，所以裸跑 mvn 等价）
& "C:\Program Files\apache-maven-3.9.16\bin\mvn.cmd" -o clean package

# 通过标准：退出码 0
```

`-o` 是离线模式，依赖已缓存时用它更快更稳；需要拉新依赖时去掉 `-o`。

产物：

| 位置 | 说明 |
|---|---|
| `D:\MinecraftServer\plugins\coregame-1.0.jar` | 最终部署产物 |
| `target\coregame-1.0-shaded.jar` | shade 之后、与部署产物同尺寸 |
| `target\original-coregame-1.0.jar` | 未 shade 的原始 jar |

⚠️ **`pom.xml` 第 27 行把 jar 输出目录写死为 `D:\MinecraftServer\plugins`** —— 这是开发机专有路径，换机器必须改这一行。多人协作时，这里是固定冲突点。

## 已知陷阱

1. **`plugin.yml` 的 `main:` 必须用点号**，即 `org.changchenguwu.coregame.CoreGame`。
   写成斜杠形式（`org/changchenguwu/coregame/CoreGame`）会让插件直接加载失败，而且报错信息不直观。这是本项目真实发生过的事故。

2. **8 个自定义事件类里的 `public static HandlerList getHandlerList()` 不能删。**
   IDE 会报"方法未使用"，但 Bukkit 是通过**反射**调用它的。删掉会破坏 `Event` 注册机制。**这个警告必须无视。**

3. **服务端进程占用 `target/` 时 `mvn clean` 会失败**（Windows 文件锁）。
   此时改用影子副本构建：把 `pom.xml` 与 `src/` 复制到一个独立目录再构建，**不要动原仓库**。

4. **PowerShell 5.1 写文件会带 BOM。**
   `Set-Content -Encoding utf8` 产生 UTF-8 BOM，`javac` 会报 `非法字符: '\ufeff'`。需要无 BOM 时用
   `[System.IO.File]::WriteAllText($path, $text, [System.Text.UTF8Encoding]::new($false))`。

5. **不要用全局字符串替换改类名。**
   子串替换会误伤：改 `FormatText` 会波及 `unFormatText`，改 `Placeholder` 会波及字符串 `"PlaceholderAPI"`。必须用词边界正则或 IDE 的符号级 Move Class。

6. **`.gitignore` 已忽略、不要提交**：`.idea/`、`target/`、`*.iml`、`dependency-reduced-pom.xml`。

## 约定

- **代码内不写注释。** 现有源码只有 `plugin.yml` 里的颜色码对照表这类中文注释，Java 代码内没有解释性注释。
- **改动最小化。** 只改完成当前任务必需的地方，不顺手重构、不顺手格式化无关文件。
- 源码保持 **UTF-8 无 BOM + CRLF**，不要改动编码与行尾。

## 已解决的坑（改前必读）

- **屏幕震动的实现方式**（`effect/ScreenShakeEffect`）必须走"相对坐标 + 视角"的数据包写法：
  `PacketType.Play.Server.POSITION`（1.21.1 的 `PacketPlayOutPosition`：`double x,y,z` + `float yaw,pitch` + `Set` 相对标志 + `int 传送id`）
  把它当纯视角包用，前提是**把 x/y/z 三个相对标志填满并把坐标写成 0**。
  否则客户端会按"服务端传送"处理：每 tick 把玩家拽回服务端记录的坐标、清零动量，表现为震动期间**无法移动**（相对标志集合字段位于 `getModifier()` 索引 5，用 `getSpecificModifier(Set.class).readSafely(0)` 取出后原地增删即可，ProtocolLib 未内置该枚举）。

## 已知问题（未修复 —— 只提示，勿擅自扩改）

> 下面两项已核对源码；其余为静态分析所报、未经运行时复现。按项目惯例：发现问题只提醒并提出精简方案，不擅自修改。

- `screen/ScreenManager.java:34` —— `loadScreens()` 的 for 循环内部用了 `return` 而不是 `continue`。
  只要有一个屏幕项读取失败（`getConfigurationSection(key)` 返回 null），整轮加载就会中断，后面的屏幕全部不加载。
- 世界名 `"flat"` 在 Java 源码中硬编码 **56 处**，疑似应改为配置项。
- 以下为此前报告过但本次未复核：`SelectTeamListener` 的槽位判定、温度事件由渲染循环驱动、`PermissionCard.PERMISSION_CARD_0` 与 `Monitor.setStrategy/setInterval/setSpecialTextIndex` 未被任何地方调用。
