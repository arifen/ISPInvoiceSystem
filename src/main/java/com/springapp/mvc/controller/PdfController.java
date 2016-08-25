package com.springapp.mvc.controller;

import com.springapp.mvc.model.Customer;
import com.springapp.mvc.service.CustomerService;
import com.springapp.mvc.service.PdfCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by arifen on 7/7/16.
 */
@Controller
public class PdfController {

    @Autowired
    @Qualifier("customerService")
    CustomerService customerService;

    @Autowired
    @Qualifier("pdfcreateservice")
    PdfCreateService pdfCreateService;


    @RequestMapping(value = "/downloadPDF")
    public void downloadPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Customer> customerServiceList = customerService.getAllCustomer();
        Collections.sort(customerServiceList, Customer.customerpackageComparator);

        final ServletContext servletContext = request.getSession().getServletContext();
        final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        final String temperotyFilePath = tempDirectory.getAbsolutePath();

        String fileName = "Internet_Bill_Invoice.pdf";
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);

        try {
            pdfCreateService.createPdf(temperotyFilePath + "\\" + fileName, customerServiceList);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos = convertPDFToByteArrayOutputStream(temperotyFilePath + "\\" + fileName);
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @RequestMapping(value = "/downloadPDFperCustomer/{packageId}")
    public void downloadPDFByPackage(HttpServletRequest request, HttpServletResponse response, @PathVariable long packageId) throws IOException {
        System.out.println("package id " + packageId);
        List<Customer> customerServiceList = customerService.findCustomerByPackageId(packageId);
        Collections.sort(customerServiceList, Customer.customerpackageComparator);

        final ServletContext servletContext = request.getSession().getServletContext();
        final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        final String temperotyFilePath = tempDirectory.getAbsolutePath();

        String fileName = "Internet_Bill_Invoice.pdf";
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);

        try {
            pdfCreateService.createPdf(temperotyFilePath + "\\" + fileName, customerServiceList);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos = convertPDFToByteArrayOutputStream(temperotyFilePath + "\\" + fileName);
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @RequestMapping(value = "/downloadPDF/{userId}")
    public void downloadPDFperUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String userId) throws IOException {
        System.out.print("user Id " + userId);
        List<Customer> customerServiceList = customerService.findCustomerByUserId(userId);
        Collections.sort(customerServiceList, Customer.customerpackageComparator);

        final ServletContext servletContext = request.getSession().getServletContext();
        final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        final String temperotyFilePath = tempDirectory.getAbsolutePath();

        String fileName = "Internet_Bill_Invoice.pdf";
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);

        try {
            pdfCreateService.createPdf(temperotyFilePath + "\\" + fileName, customerServiceList);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos = convertPDFToByteArrayOutputStream(temperotyFilePath + "\\" + fileName);
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    private ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {

        InputStream inputStream = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            inputStream = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos;
    }
}
