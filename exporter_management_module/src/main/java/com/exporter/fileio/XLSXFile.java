package com.exporter.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.exporter.exceptions.InjuryFileException;
import com.exporter.model.Transaction;

@Component
public class XLSXFile {
	
	public byte[] importTransactionXLSX(String path) {

		StringBuilder stringBuilder = new StringBuilder();
		try {
			FileInputStream file = new FileInputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch(cell.getCellType()) {
					case NUMERIC:
						stringBuilder.append(" ");
						stringBuilder.append(cell.getNumericCellValue());
						break;
					case STRING:
						stringBuilder.append(" ");
						stringBuilder.append(cell.getStringCellValue());
						break;
					default:
						throw new IllegalArgumentException("Entered cell type can't find");
					}
				}
			}

		}catch (FileNotFoundException e) {
			throw new InjuryFileException("File is Injured or not found");
		} catch (IOException e) {
			throw new InjuryFileException("Input/Output file wrong operation");
		}
		return stringBuilder.toString().getBytes();

	}


	public List<Transaction> exportTransactionXLSX(List<Transaction> transactionsList) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Transactions");

		int rowNumber = 0;
		for(Transaction transaction : transactionsList) {
			Row row = sheet.createRow(rowNumber++);
			row.createCell(0).setCellValue(transaction.getId());
			row.createCell(1).setCellValue(transaction.getTransferType().toString());
			row.createCell(2).setCellValue(transaction.getFrom());
			row.createCell(3).setCellValue(transaction.getTo());
			row.createCell(4).setCellValue(transaction.getDate());
		}

		try (FileOutputStream outputStream = new FileOutputStream(new File("transaction.xlsx"))) {
			workbook.write(outputStream);
			workbook.close();
		}catch (IOException e) {
			throw new InjuryFileException("File not found");
		}
		return transactionsList;
	}

}
