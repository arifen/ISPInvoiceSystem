package com.springapp.mvc.dao;

import com.springapp.mvc.model.Package;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arifen on 7/15/16.
 */
@Repository("packageDao")
public class PackageDaoImpl implements PackageDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Package> getAllPackage() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Package");
        List<Package> list = query.list();
        return (list == null) ? new ArrayList<Package>() : list;
    }

    @Override
    public Package getPackageByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.print("package name " + name);
        Query query = session.createQuery("from Package where packageName = :name ");
        query.setParameter("name", name);
        List<Package> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
