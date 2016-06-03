package com.springapp.mvc.controller;

import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.RoleService;
import com.springapp.mvc.service.UserService;
import com.springapp.mvc.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.beans.PropertyEditorSupport;

/**
 * Created by arifen on 5/25/16.
 */
@Controller
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("userValidator")
    private UserValidator userValidator;

    @ModelAttribute
    public void selectRole(Model model){
        model.addAttribute("Role",roleService.findAllRole());
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.setValidator(userValidator);
        binder.registerCustomEditor(Role.class, "role", new PropertyEditorSupport() {
            @Override
            public void setAsText(String name) {
                Role role = roleService.findRoleByName(name);
                setValue(role);
            }
        });
    }
    @RequestMapping(value = { "/usercreate" })
    public String usercreate(Model modelMap) {
       /* System.out.println("Number of role "+roleService.findAllRole().size());
        modelMap.addAttribute();*/
        modelMap.addAttribute("user",new User());
        return "createuser";
    }

    @RequestMapping(value = { "/userregister" }, method = RequestMethod.POST)
    public String userregistration(@Validated @ModelAttribute("user") User user,BindingResult bnResult,ModelMap  modelMap) {
        if(bnResult.hasErrors()){
            modelMap.addAttribute("msg","User has not been saved");
            return "createuser";
        }
        userService.saveUser(user);
        modelMap.addAttribute("msg","User has been saved successfully");
        return "login";
    }
}
