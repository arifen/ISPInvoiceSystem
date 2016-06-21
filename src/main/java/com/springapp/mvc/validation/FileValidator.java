package com.springapp.mvc.validation;
import com.springapp.mvc.model.FileBucket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by arifen on 6/21/16.
 */
@Component("fileValidator")
public class FileValidator  implements Validator{
    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        if(obj instanceof FileBucket){
            FileBucket file = (FileBucket) obj;

            if(file.getFile()!=null){
                if (file.getFile().getSize() == 0) {
                    errors.rejectValue("file", "missing.file");
                }
            }
        }

    }
}
