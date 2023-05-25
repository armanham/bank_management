package com.exporter.fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import com.exporter.exceptions.InjuryFileException;
import com.exporter.model.Transaction;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFFile {
	
	public byte[] importTransactionPDF(String path) {
		
		byte[] pdfReaderResults = new byte[0];
		 	try {
	            PDDocument document = PDDocument.load(new File(path));
	            PDFTextStripper textStripper = new PDFTextStripper();
	            String text = textStripper.getText(document);
	            System.out.println(text);
	            pdfReaderResults = text.getBytes();
	            document.close();
		 		} catch (IOException e) {
	        	throw new InjuryFileException("Input/Output file wrong operation");
		 		}
		 return pdfReaderResults;
	}

	public List<Transaction> exportTransactionPDF(List<Transaction> transactionsList) {
		try {
	        Document document = new Document();
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File("transaction.pdf")));
	        document.open();
	        for (Transaction transaction : transactionsList) {
	            Paragraph paragraph = new Paragraph(transaction.toString());
	            document.add(paragraph);
	        }
	        document.close();
	        writer.close();
		}catch(DocumentException e) {
			e.getMessage();
		} catch (FileNotFoundException e) {
			throw new InjuryFileException("Input/Output file wrong operation");
		}
	       return transactionsList;

}

}
