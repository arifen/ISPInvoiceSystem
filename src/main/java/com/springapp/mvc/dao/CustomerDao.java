package com.springapp.mvc.dao;

import com.springapp.mvc.model.Customer;

import java.util.List;

/**
 * Created by arifen on 7/5/16.
 */
public interface CustomerDao {
    void saveCustomer(Customer customer);

    List<Customer> findCustomerByUserId(String userId);

    List<Customer> findCustomerByPackageId(long packageId);

    List<Customer> findAllCustomer();

    Customer findCustomerById(long id);

    void deleteCustomer(Customer customer);
}
