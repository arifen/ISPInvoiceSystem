package com.springapp.mvc.controller;

import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by arifen on 5/25/16.
 */
@Controller
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = { "/usercreate" })
    public String usercreate() {
        return "createuser";
    }

    @RequestMapping(value = { "/userregister" }, method = RequestMethod.POST)
    public String userregistration(@ModelAttribute("user") User user,BindingResult bnResult,ModelMap  modelMap) {
        if(bnResult.hasErrors()){
            modelMap.addAttribute("msg",bnResult.getAllErrors());
            return "createuser";
        }
        userService.saveUser(user);
        modelMap.addAttribute("msg","User has been saved successfully");
        return "createuser";
    }
}
