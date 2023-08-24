package com.Codenera.auth.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGenerationService {

    public byte[] generatePdf(String code) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            System.out.println("Code to be added to PDF: " + code); // Print the code

            Font font = FontFactory.getFont(FontFactory.COURIER, 12);
            Paragraph paragraph = new Paragraph("Generated PDF of User's Code:", font);
            document.add(paragraph);

            Paragraph codeParagraph = new Paragraph(code, font);
            document.add(codeParagraph);
        } finally {
            document.close();
        }
        return outputStream.toByteArray();
    }
}
