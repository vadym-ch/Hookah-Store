package com.kpi.internetshop.service.impl;

import com.kpi.internetshop.entity.Role;
import com.kpi.internetshop.repository.RoleRepository;
import com.kpi.internetshop.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void add(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.getByRoleName(Role.of(roleName).getRoleName());
    }
}
