package com.springapp.mvc.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.springapp.mvc.model.Customer;
import com.springapp.mvc.util.CreatePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by arifen on 7/9/16.
 */
@Service("pdfcreateservice")
public class PdfCreateServiceImpl implements PdfCreateService {

    private static String USER_PASSWORD;
    private static String OWNER_PASSWORD;
    @Autowired
    @Qualifier("pdfcreator")
    CreatePdf createPdf;

    /*
    * this is method used to set value in static field from Properties file
    * */
    public static void setUserPasswordAndOwnerPassword(String userPassword, String ownerPassword) {
        System.out.print("called static block");
        USER_PASSWORD = userPassword;
        OWNER_PASSWORD = ownerPassword;
    }

    @Override
    public Document createPdf(String file, List<Customer> customerList) {
        Document document = null;

        try {
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            System.out.print("userpass " + USER_PASSWORD + "ownerpass " + OWNER_PASSWORD);
            writer.setEncryption(USER_PASSWORD.getBytes(),
                    OWNER_PASSWORD.getBytes(), PdfWriter.ALLOW_PRINTING,
                    PdfWriter.ENCRYPTION_AES_128);
            /*createPdf.encrytpPdf(writer,USER_PASSWORD,OWNER_PASSWORD,PdfWriter.ALLOW_PRINTING,PdfWriter.ENCRYPTION_AES_128);*/
            document.open();
            for (Customer customer : customerList) {
                CreatePdf.addMetaData(document);
                CreatePdf.addTitlePage(document);
                CreatePdf.invoiceCreation(document, customer);
                Paragraph paragraph = new Paragraph();
                paragraph.add("------------------");
                document.add(paragraph);
                paragraph = new Paragraph();
                paragraph.add("Customer Signature");
                CreatePdf.creteEmptyLine(paragraph, 3);
                document.add(paragraph);
                CreatePdf.addTitlePage(document);
                CreatePdf.invoiceCreation(document, customer);
                paragraph = new Paragraph();
                paragraph.add("------------------");
                document.add(paragraph);
                paragraph = new Paragraph();
                paragraph.add("Recipent Signature");
                document.add(paragraph);
                document.newPage();
            }
            document.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
