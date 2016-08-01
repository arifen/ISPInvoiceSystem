package com.springapp.mvc.dao;

import com.springapp.mvc.model.Package;

import java.util.List;

/**
 * Created by arifen on 7/15/16.
 */
public interface PackageDao {
    List<Package> getAllPackage();

    Package getPackageByName(String name);
}
