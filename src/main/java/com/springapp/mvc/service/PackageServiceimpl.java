package com.springapp.mvc.service;

import com.springapp.mvc.dao.PackageDao;
import com.springapp.mvc.model.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by arifen on 7/15/16.
 */
@Service("packageservice")
public class PackageServiceimpl implements PackageService {

    @Autowired
    @Qualifier("packageDao")
    PackageDao packageDao;

    @Override
    @Transactional
    public List<Package> findAllPackage() {
        return packageDao.getAllPackage();
    }

    @Override
    @Transactional
    public Package getPackageByName(String name) {
        return packageDao.getPackageByName(name);
    }
}
