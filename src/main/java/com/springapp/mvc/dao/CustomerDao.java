package com.springapp.mvc.dao;

import com.springapp.mvc.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * Created by arifen on 7/5/16.
 */
public interface CustomerDao {
    void saveCustomer(Customer customer);

    List<Customer> findCustomerByUserId(String userId);

    List<Customer> findCustomerByPackageId(long packageId);

    Long findActiveCustomersByPackageId(long packageId);

    List<Customer> findAllCustomer();

    Long findAllActiveCustomer();

    Customer findCustomerById(long id);

    void deleteCustomer(Customer customer);

    Map<String, Object> findAllCustomerPagination(int begin, int pageSize);
}
