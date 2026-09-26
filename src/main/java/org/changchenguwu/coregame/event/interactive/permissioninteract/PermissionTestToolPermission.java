package org.changchenguwu.coregame.event.interactive.permissioninteract;

public class PermissionTestToolPermission extends Permission {
    public static final PermissionTestToolPermission INSTANCE = new PermissionTestToolPermission();
    private PermissionTestToolPermission() {
        super("coregame.permissiontesttool", 0);
    }
}
