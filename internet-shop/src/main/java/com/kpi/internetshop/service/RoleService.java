package com.kpi.internetshop.service;

import com.kpi.internetshop.entity.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
