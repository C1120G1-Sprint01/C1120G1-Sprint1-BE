package com.c1120g1.adweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "`role`",
        uniqueConstraints = {
                @UniqueConstraint(name = "ROLE_UK", columnNames = "role_id")
        })
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name", columnDefinition = "VARCHAR(50)")
    private String roleName;

    public Role() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
