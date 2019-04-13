package cn.yq.oa.pojo;

import java.util.List;

public class SysRole {
    private Integer roeId;

    private String name;

    private String remark;

    private Integer available;
    
    private Integer[] permissionIds;

    public Integer[] getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(Integer[] permissionIds) {
		this.permissionIds = permissionIds;
	}

	public Integer getRoeId() {
        return roeId;
    }

    public void setRoeId(Integer roeId) {
        this.roeId = roeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}