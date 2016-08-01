package com.springapp.mvc.service;

import com.springapp.mvc.model.Package;

import java.util.List;

/**
 * Created by arifen on 7/15/16.
 */
public interface PackageService {
    List<Package> findAllPackage();

    Package getPackageByName(String name);
}
