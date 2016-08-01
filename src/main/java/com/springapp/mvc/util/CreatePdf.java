package com.springapp.mvc.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.springapp.mvc.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by arifen on 7/7/16.
 */
@Component("pdfcreator")
public class CreatePdf {

    private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    @Autowired
    ServletContext servletContext;

    /**
     * @param
     */
    /*public static Document createPDF(String file) {

        Document document = null;

        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            addMetaData(document);

            addTitlePage(document);

            createTable(document);

            document.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;

    }*/
    public static void addMetaData(Document document) {
        document.addTitle("Generate PDF invoice");
        document.addSubject("Generate PDF invoice");
        document.addAuthor("Admin");
        document.addCreator("Admin");
    }

    public static void addTitlePage(Document document)
            throws DocumentException {
        PdfPCell cell = null;//""
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        try {
            final DefaultResourceLoader loader = new DefaultResourceLoader();
            /*
            * Read file from classpath
            * */
            Resource resource = loader.getResource("classpath:image/ps_kaleidescape_logo.jpeg");
            Image image = Image.getInstance(resource.getFile().getCanonicalPath());
            image.scaleAbsolute(50f, 50f);
            cell = new PdfPCell(image);
            cell.setPadding(0);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.addCell(getCell("Be Coonected with the World", PdfPCell.ALIGN_CENTER));
        table.addCell(getCell("111/3 North Mugdapara, Opposite wapda Colony (Water Pump), Dhaka-1214", PdfPCell.ALIGN_CENTER));
        cell = getCell("01819935379,01911287002", PdfPCell.ALIGN_CENTER);
        cell.setPaddingBottom(2);
        table.addCell(cell);
        table.addCell(getCellwithcolor("Cash Receipt", PdfPCell.ALIGN_CENTER));
        document.add(table);

    }

    public static void creteEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public static void createTable(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        creteEmptyLine(paragraph, 2);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("First Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Last Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Test"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (int i = 0; i < 5; i++) {
            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell("Java");
            table.addCell("Honk");
            table.addCell("Success");
        }
        document.add(table);
    }

    public static void invoiceCreation(Document document, Customer customer) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        creteEmptyLine(paragraph, 1);
        document.add(paragraph);
        Chunk glue = new Chunk(new VerticalPositionMark());
        Calendar cal = Calendar.getInstance();
        paragraph = new Paragraph("PackageName : " + customer.getaPackage().getPackageName(), TIME_ROMAN);
        paragraph.add(new Chunk(glue));
        paragraph.add("Monthly Internet Bill : " + new SimpleDateFormat("MMMM").format(cal.getTime()) + "," + new SimpleDateFormat("YYYY").format(cal.getTime()));
        document.add(paragraph);
        document.add(invoiceFormat(customer));
        //document.newPage();
    }

    public static Paragraph invoiceFormat(Customer customer) {
        /*Chunk glue = new Chunk(new VerticalPositionMark());
        Calendar cal = Calendar.getInstance();
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Paragraph("PackageName : " + customer.getaPackage().getPackageName(), TIME_ROMAN));
        paragraph.add(new Chunk(glue));
        paragraph.add(new Paragraph("Monthly Internet Bill : " + new SimpleDateFormat("MMMM").format(cal.getTime()) + "," + new SimpleDateFormat("YYYY").format(cal.getTime()), TIME_ROMAN));*/
        Paragraph paragraph = new Paragraph();
        creteEmptyLine(paragraph, 2);
        paragraph.add(new Paragraph("UserId : " + customer.getCustomerId(), TIME_ROMAN_SMALL));
        paragraph.add(new Paragraph("Amount : " + customer.getaPackage().getAmount(), TIME_ROMAN_SMALL));
        paragraph.add(new Paragraph("Name : " + customer.getName(), TIME_ROMAN_SMALL));
        paragraph.add(new Paragraph("Phone Number : " + customer.getMobileNumber(), TIME_ROMAN_SMALL));
        paragraph.add(new Paragraph("Address : " + customer.getAddress(), TIME_ROMAN_SMALL));
        creteEmptyLine(paragraph, 4);
        return paragraph;
    }

    public static PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getCellwithcolor(String text, int alignment) {
        PdfPCell cell = getCell(text, alignment);
        cell.setBackgroundColor(BaseColor.GRAY);
        return cell;
    }

    public static void encrytpPdf(PdfWriter writer, String userPassword, String ownerPassword, int pdfPermission, int encryptionTechnique) throws DocumentException {
        writer.setEncryption(userPassword.getBytes(),
                ownerPassword.getBytes(), pdfPermission,
                encryptionTechnique);
    }
}
