package org.changchenguwu.coregame.events.interactive.permission;

public abstract class Permission {
    private final String PERMISSION_FIELD;
    private final int PERMISSION_LEVEL;

    protected Permission(String permissionField, int permissionLevel) {
        this.PERMISSION_FIELD = permissionField;
        this.PERMISSION_LEVEL = permissionLevel;
    }
    public String getPermissionField() {
        return PERMISSION_FIELD;
    }
    public int getPermissionLevel() {
        return PERMISSION_LEVEL;
    }

//    public boolean hasPermission(Player player) {
//        return player.hasPermission(permissionField);
//    }
}
