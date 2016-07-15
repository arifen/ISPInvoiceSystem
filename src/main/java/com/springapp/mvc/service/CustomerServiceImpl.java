package com.springapp.mvc.service;

import com.springapp.mvc.dao.CustomerDao;
import com.springapp.mvc.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by arifen on 7/5/16.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    @Qualifier("customerDao")
    CustomerDao customerDao;

    @Override
    @Transactional
    public boolean saveCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
        return true;
    }

    @Override
    @Transactional
    public boolean editCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
        return true;
    }

    @Override
    @Transactional
    public List<Customer> findCustomerByUserId(String userId) {
        return customerDao.findCustomerByUserId(userId);
    }

    @Override
    @Transactional
    public List<Customer> getAllCustomer() {
        System.out.print("customer service");
        return customerDao.findAllCustomer();
    }
}
