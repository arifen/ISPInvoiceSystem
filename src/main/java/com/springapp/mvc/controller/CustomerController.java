package com.springapp.mvc.controller;

import com.springapp.mvc.model.Customer;
import com.springapp.mvc.service.CustomerService;
import com.springapp.mvc.validation.CustomerValidation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by arifen on 7/5/16.
 */
@Controller
public class CustomerController {
    final static Logger logger = Logger.getLogger(CustomerController.class);

    @Autowired
    @Qualifier("customerService")
    CustomerService customerService;

    @Autowired
    @Qualifier("customerValidator")
    private CustomerValidation customerValidation;

    /*@InitBinder("customer")
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.setValidator(customerValidation);
    }*/

    @RequestMapping(value = {"/customerhome"})
    public String customerhome() {
        return "customerhome";
    }

    @RequestMapping(value = {"/customercreate"})
    public String usercreate(Model modelMap) {
        modelMap.addAttribute("customer", new Customer());
        logger.debug("customer model add");
        return "customercreate";
    }

    @RequestMapping(value = {"/customerregister"}, method = RequestMethod.POST)
    public String userregistration(@Valid @ModelAttribute("customer") Customer customer, BindingResult bnResult, ModelMap modelMap) {
        if (bnResult.hasErrors()) {
            logger.debug("error in customer model attribute");
            modelMap.addAttribute("msg", "User has not been saved");
            return "customercreate";
        }
        logger.debug("customer object " + customer);
        customerService.saveCustomer(customer);
        logger.debug("user save successfully");
        modelMap.addAttribute("customer", customer);
        return "customercreatesuccessfull";
    }
}
