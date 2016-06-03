package com.springapp.mvc.validation;

import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by arifen on 6/3/16.
 */
@Component("userValidator")
public class UserValidator implements Validator {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target instanceof User){
            User user = (User) target;
            List<User> userList = userService.findUserByloginId(user.getLoginId());

            if(( userList!= null) && (userList.size()>0)){
                errors.rejectValue("loginId","loginId.exists");
            }
        }
    }
}
