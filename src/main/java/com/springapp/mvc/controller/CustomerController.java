package com.springapp.mvc.controller;

import com.springapp.mvc.model.Customer;
import com.springapp.mvc.model.Package;
import com.springapp.mvc.service.CustomerService;
import com.springapp.mvc.service.PackageService;
import com.springapp.mvc.validation.CustomerValidation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Qualifier("packageservice")
    private PackageService packageService;

    @Autowired
    @Qualifier("customerValidator")
    private CustomerValidation customerValidation;

    @ModelAttribute
    public void selectPackage(Model model) {
        System.out.print("selectpacke call");
        model.addAttribute("Package", packageService.findAllPackage());
    }

    @InitBinder("customer")
    protected void initBinder(WebDataBinder binder) throws Exception {
        // binder.setValidator(customerValidation);
       /*
        * registerCustomEditor mainly use for different purposes custom validation,conversion and so many things
        * class name,String field name(input field name),PropertyEditorSupport
        * */
        binder.registerCustomEditor(Package.class, "aPackage", new PropertyEditorSupport() {
            @Override
            public void setAsText(String name) {
                Package apackage = packageService.getPackageByName(name);
                setValue(apackage);
            }
        });
    }

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
        List<Customer> customerList = customerService.findCustomerByUserId(customer.getCustomerId());

        if ((customerList != null) && (customerList.size() > 0)) {
            logger.debug("error : Customer UserId Exist");
            modelMap.addAttribute("msg", "User Id  already exists.");
            return "customercreate";
            // errors.rejectValue("customerId", "UserId.exists");
        }
        logger.debug("customer object " + customer);
        customerService.saveCustomer(customer);
        logger.debug("user save successfully");
        modelMap.addAttribute("customer", customer);
        return "customercreatesuccessfull";
    }

    @RequestMapping(value = {"/customerlist"})
    public String showCustomerLists(Model modelMap) {
        modelMap.addAttribute("customerlists", customerService.getAllCustomer());
        modelMap.addAttribute("msg", "All Customer List");
        return "customerlist";
    }

    @RequestMapping(value = {"/customerlist/{pageNumber}"})
    public String showCustomerList(Model modelMap, @PathVariable int pageNumber) {
        Map<String, Object> customerServiceMap = new HashMap<String, Object>();
        int pageSize = 5;
        customerServiceMap = customerService.getAllCustomerPagination(pageNumber, pageSize);
        customerServiceMap.get("customeList");
        Long endRange = (Long) customerServiceMap.get("total");
        if ((endRange % pageSize) == 0) {
            endRange = endRange / pageSize;
        } else {
            endRange = (endRange / pageSize) + 1;
        }
        modelMap.addAttribute("customerlists", customerServiceMap.get("customeList"));
        modelMap.addAttribute("beginIndex", 1);
        modelMap.addAttribute("endIndex", endRange);
        modelMap.addAttribute("currentIndex", pageNumber);
        /*modelMap.addAttribute("customerlists", customerService.getAllCustomer());*/
        modelMap.addAttribute("msg", "All Customer List");
        return "customerlistpage";
    }

    @RequestMapping(value = {"/customerbypackage"})
    public String selectCustomerByPackage(Model modelMap) {
        modelMap.addAttribute("msg", "select Customer Package");
        return "allpackage";
    }

    @RequestMapping(value = {"/packagecustomer"}, method = RequestMethod.POST)
    public String showcustomerbypackage(@RequestParam(value = "selectpackage", required = true) long packageId, Model modelMap) {
        logger.debug("package id " + packageId);
        modelMap.addAttribute("customerlists", customerService.findCustomerByPackageId(packageId));
        modelMap.addAttribute("msg", "All Customer List");
        return "customerlist";
    }

    @RequestMapping(value = "/customer/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Customer deleteCustomer(@PathVariable long id) {
        System.out.print("come to delete method");
        return customerService.deleteById(id);
    }

    @RequestMapping(value = "/customeredit/{id}", method = RequestMethod.GET)
    public String editCustomerPage(@PathVariable long id, Model modelMap) {
        Customer customer = customerService.findCustomerById(id);
        modelMap.addAttribute("customer", customer);
        return "customereditpage";
    }

    @RequestMapping(value = "/customer/editajax/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Customer editCustomer(@PathVariable long id, @RequestBody Customer customer) {
        logger.debug("Customer Id  " + id);
        customer.setId(id);
        return customerService.editCustomer(customer);
        //return new Customer();
    }
}
