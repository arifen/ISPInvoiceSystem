package com.springapp.mvc.service;

import com.springapp.mvc.dao.RoleDao;
import com.springapp.mvc.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by arifen on 6/1/16.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;

    @Override
    @Transactional
    public Role findRoleByName(String name) {
         return roleDao.findByName(name);
    }

    @Override
    @Transactional
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
        //return null;
    }
}
