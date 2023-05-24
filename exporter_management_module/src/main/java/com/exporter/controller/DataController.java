package com.exporter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exporter.enumerations.FileType;
import com.exporter.model.Transaction;
import com.exporter.selector.Selector;


@Controller
@RequestMapping("/data")
public class DataController {
	
	private final Selector selector;
	
	@Autowired
	public DataController(Selector selector) {
		this.selector = selector;
	}
	
	@PostMapping("/import")
	@ResponseBody
	public ResponseEntity<byte[]> importUserCSV(@RequestParam(value = "filetype") FileType fileType, @RequestParam(value = "path") String path) {
		return ResponseEntity.ok().body(selector.importTransactionChoose(fileType,path));
	}
	
	@PostMapping("/export")
	@ResponseBody
	public ResponseEntity <List<Transaction>> exportUserCSV(@RequestParam(value = "filetype") FileType fileType, @RequestBody List<Transaction> transactionsList) {
		return ResponseEntity.ok().body(selector.exportTransactionChoose(fileType, transactionsList));
	}
	

}
