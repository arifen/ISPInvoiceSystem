package com.springapp.mvc.model;

import javax.persistence.*;

/**
 * Created by arifen on 5/25/16.
 */
@Entity
@Table(name="Role")
public class Role {
    @Id
    @GeneratedValue
    @Column(name="Id")
    private long roleId;

    @Column(name="roleName")
    private String roleName;

    public long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
