package com.springapp.mvc.validation;

import com.springapp.mvc.model.Customer;
import com.springapp.mvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by arifen on 7/5/16.
 */
@Component("customerValidator")
public class CustomerValidation implements Validator {

    @Autowired
    @Qualifier("customerService")
    private CustomerService customerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Customer.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof Customer) {
            Customer customer = (Customer) target;
            List<Customer> customerList = customerService.findCustomerByUserId(customer.getCustomerId());

            if ((customerList != null) && (customerList.size() > 0)) {
                errors.rejectValue("customerId", "UserId.exists");
            }
        }
    }
}
