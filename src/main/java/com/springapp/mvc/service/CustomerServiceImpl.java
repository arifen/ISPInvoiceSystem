package com.springapp.mvc.service;

import com.springapp.mvc.dao.CustomerDao;
import com.springapp.mvc.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Customer> findCustomerByPackageId(long packageId) {
        return customerDao.findCustomerByPackageId(packageId);
    }

    @Override
    @Transactional
    public Customer editCustomer(Customer customer) {
        /*Customer customerUpdate = findCustomerById(customer.getId());
        Smartphone sPhoneToUpdate = get(sp.getId());
        if (sPhoneToUpdate == null)
            throw new SmartphoneNotFoundException(sp.getId().toString());
        sPhoneToUpdate.update(sp);*/
        customerDao.saveCustomer(customer);
        return customer;
    }

    @Override
    @Transactional
    public List<Customer> findCustomerByUserId(String userId) {
        return customerDao.findCustomerByUserId(userId);
    }

    @Override
    @Transactional
    public Map<String, Object> getAllCustomerPagination(int pageNumber, int pageSize) {
        int begin = (pageNumber - 1) * pageSize;
        Map<String, Object> customerMap = new HashMap<String, Object>();
        customerMap = customerDao.findAllCustomerPagination(begin, pageSize);
        return customerMap;
    }

    @Override
    @Transactional
    public List<Customer> getAllCustomer() {
        System.out.print("customer service");
        return customerDao.findAllCustomer();
    }

    @Override
    @Transactional
    public Customer findCustomerById(long Id) {
        return customerDao.findCustomerById(Id);
    }

    @Override
    @Transactional
    public Customer deactivate(long Id) {
        Customer customer = findCustomerById(Id);
        customer.setStatus("deactivate");
        customerDao.saveCustomer(customer);
        return customer;
    }

    @Override
    @Transactional
    public Customer deleteById(long Id) {
        Customer customer = findCustomerById(Id);
        customerDao.deleteCustomer(customer);
        return customer;
    }
}
