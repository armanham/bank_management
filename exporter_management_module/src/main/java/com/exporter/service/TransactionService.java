package com.exporter.service;

import java.util.List;

import com.exporter.model.Transaction;

public interface TransactionService {
	
	public byte[] importTransactionXLSX(String path);
	public List<Transaction> exportTransactionXLSX(List<Transaction> transactionsList);
	public byte[] importTransactionPDF(String path);
	public List<Transaction> exportTransactionPDF(List<Transaction> transactionsList);

}
