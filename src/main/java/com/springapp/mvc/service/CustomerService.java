package com.springapp.mvc.service;

import com.springapp.mvc.model.Customer;

import java.util.List;

/**
 * Created by arifen on 7/5/16.
 */
public interface CustomerService {
    boolean saveCustomer(Customer customer);

    boolean editCustomer(Customer customer);

    List<Customer> findCustomerByUserId(String userId);

    List<Customer> getAllCustomer();
}
