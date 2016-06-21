package com.springapp.mvc.controller;

import com.springapp.mvc.model.FileBucket;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import com.springapp.mvc.validation.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by arifen on 6/21/16.
 */
@Controller
public class FileController {
    private static String UPLOAD_LOCATION="/home/arifen/fileUpload/";

    @Autowired
    @Qualifier("fileValidator")
    FileValidator fileValidator;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @ModelAttribute
    public void selectRole(Model model){
        model.addAttribute("fileBucket",new FileBucket());
    }
    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.setValidator(fileValidator);
    }
    @RequestMapping(value = { "/uploadfile" })
    public String getFileUploader() {
        return "uploadfile";
    }
    @RequestMapping(value = { "/fileupload" }, method = RequestMethod.POST)
    public String fileUplaod(@Validated FileBucket filebucket,BindingResult result,ModelMap modelMap,@RequestParam("userId") String userId) throws IOException {
        if(result.hasErrors()){
            System.out.println("file validation error");
            return "uploadfile";
        }
        String fileName = UPLOAD_LOCATION +userId+"_"+new Date().getTime();
        FileCopyUtils.copy(filebucket.getFile().getBytes(),new File(fileName));
        User user = userService.findByUserId(Long.valueOf(userId));
        user.setImageLocation(fileName);
        userService.saveUser(user);
        return "redirect:login";
    }
}
