package org.changchenguwu.coregame.events.interactive.permission;

public class PermissionTestToolPermission extends Permission {
    public static final PermissionTestToolPermission INSTANCE = new PermissionTestToolPermission();
    private PermissionTestToolPermission() {
        super("coregame.permissiontesttool", 0);
    }
}
