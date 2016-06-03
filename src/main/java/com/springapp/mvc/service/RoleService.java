package com.springapp.mvc.service;

import com.springapp.mvc.model.Role;

import java.util.List;

/**
 * Created by arifen on 6/1/16.
 */
public interface RoleService {
    Role findRoleByName(String name);
    List<Role> findAllRole();
}
