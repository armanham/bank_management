package com.exporter.selector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exporter.enumerations.FileType;
import com.exporter.exceptions.FileTypeException;
import com.exporter.model.Transaction;
import com.exporter.service.TransactionServiceImpl;

@Component
public class Selector {
	
	private final TransactionServiceImpl transactionServiceImpl;
	
	@Autowired
	public Selector(TransactionServiceImpl transactionServiceImpl) {
		this.transactionServiceImpl = transactionServiceImpl;
	}
	
	public List<Transaction> exportTransactionChoose(FileType fileType,List<Transaction> transactionsList) {
		switch(fileType) {
		case XLSX:
			return transactionServiceImpl.exportTransactionXLSX(transactionsList);
		case PDF:
			return transactionServiceImpl.exportTransactionPDF(transactionsList);
		default:
			throw new FileTypeException("Input file type is wrong");
		}
	}
	
	public byte[] importTransactionChoose(FileType fileType,String path) {
		switch(fileType) {
		case XLSX:
			return transactionServiceImpl.importTransactionXLSX(path);
		case PDF:
			return transactionServiceImpl.importTransactionPDF(path);
		default:
			throw new FileTypeException("Input file type is wrong");
		}
	}

}
