package top.yms.recent.code1;

public enum RoleEnum {
    /**
     * 超级管理员
     */
    SUPERADMIN("a_admin"),
    /**
     * 控制台管理员
     */
    ADMIN("b_admin"),
    /**
     * 系统管理员
     */
    SYSADMIN("c_admin"),
    /**
     * 组管理员
     */
    GROUPADMIN("d_admin"),
    /**
     * 普通用户
     */
    COMMON("user");

    private String roleCode;

    RoleEnum(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

}
