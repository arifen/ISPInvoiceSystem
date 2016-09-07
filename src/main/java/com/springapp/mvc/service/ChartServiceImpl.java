package com.springapp.mvc.service;

import com.springapp.mvc.dao.CustomerDao;
import com.springapp.mvc.dao.PackageDao;
import com.springapp.mvc.model.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arifen on 8/30/16.
 */
@Service("chartService")
public class ChartServiceImpl implements ChartService {
    @Autowired
    @Qualifier("customerDao")
    CustomerDao customerDao;

    @Autowired
    @Qualifier("packageDao")
    PackageDao packageDao;

    @Override
    @Transactional
    public Map<String, Long> makePieChart() {
        Map<String, Long> dataMap = new HashMap<String, Long>();
        //dataMap.put("totalactivecustomer", customerDao.findAllActiveCustomer());
        List<Package> packages = new ArrayList<Package>();
        packages = packageDao.getAllPackage();
        for (Package aPackage : packages) {
            dataMap.put(aPackage.getPackageName(), customerDao.findActiveCustomersByPackageId(aPackage.getId()));
        }

        return dataMap;
    }
}
