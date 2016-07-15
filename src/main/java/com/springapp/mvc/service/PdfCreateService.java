package com.springapp.mvc.service;

import com.itextpdf.text.Document;
import com.springapp.mvc.model.Customer;

import java.util.List;

/**
 * Created by arifen on 7/9/16.
 */
public interface PdfCreateService {
    Document createPdf(String file, List<Customer> customerList);
}
