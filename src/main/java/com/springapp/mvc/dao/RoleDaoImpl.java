package com.springapp.mvc.dao;

import com.springapp.mvc.model.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arifen on 6/1/16.
 */
@Repository("roleDao")
public class RoleDaoImpl implements  RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.print("role name "+name);
        Query query = session.createQuery("from Role where roleName = :name ");
        query.setParameter("name", name);
        List<Role> list = query.list();
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Role> findAllRole() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Role");
        List<Role> list = query.list();
        return (list == null) ? new ArrayList<Role>():list;
    }
}
