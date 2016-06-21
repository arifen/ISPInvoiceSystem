package com.springapp.mvc.model;
import org.springframework.web.multipart.MultipartFile;
/**
 * Created by arifen on 6/21/16.
 */
public class FileBucket {
    MultipartFile file;
    public  MultipartFile getFile(){
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
