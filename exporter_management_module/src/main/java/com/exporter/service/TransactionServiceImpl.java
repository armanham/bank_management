package com.exporter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exporter.fileio.PDFFile;
import com.exporter.fileio.XLSXFile;
import com.exporter.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	private final PDFFile pdfFile;
	private final XLSXFile xlsxFile;
	
	@Autowired
	public TransactionServiceImpl(PDFFile pdfFile,XLSXFile xlsxFile) {
		this.pdfFile = pdfFile;
		this.xlsxFile = xlsxFile;
	}

	@Override
	public byte[] importTransactionXLSX(String path) {
	        return xlsxFile.importTransactionXLSX(path);
	    }
	

	@Override
	public List<Transaction> exportTransactionXLSX(List<Transaction> transactionsList) {
	
        return xlsxFile.exportTransactionXLSX(transactionsList);
	}

	@Override
	public byte[] importTransactionPDF(String path) {
		
		 return pdfFile.importTransactionPDF(path);
		 
	}

	@Override
	public List<Transaction> exportTransactionPDF(List<Transaction> transactionsList) {
		
		       return pdfFile.exportTransactionPDF(transactionsList);

	}

}
