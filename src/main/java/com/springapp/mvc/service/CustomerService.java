package com.springapp.mvc.service;

import com.springapp.mvc.model.Customer;

import java.util.List;

/**
 * Created by arifen on 7/5/16.
 */
public interface CustomerService {
    boolean saveCustomer(Customer customer);

    Customer editCustomer(Customer customer);

    List<Customer> findCustomerByUserId(String userId);

    List<Customer> findCustomerByPackageId(long packageId);

    List<Customer> getAllCustomer();

    Customer findCustomerById(long Id);

    Customer deleteById(long Id);
}
