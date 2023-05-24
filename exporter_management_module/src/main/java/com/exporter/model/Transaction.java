package com.exporter.model;

import com.exporter.enumerations.TransferType;

public class Transaction {
	
	private long id;
	private TransferType transferType;
	private String from;
	private String to;
	private String date;
	
	/**
	 * Default Constructor
	 */
	public Transaction() {
		
	}

	/**
	 * @param id
	 * @param transferType
	 * @param from
	 * @param to
	 * @param date
	 */
	public Transaction(long id, TransferType transferType, String from, String to, String date) {
		this.id = id;
		this.transferType = transferType;
		this.from = from;
		this.to = to;
		this.date = date;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the transferType
	 */
	public TransferType getTransferType() {
		return transferType;
	}

	/**
	 * @param transferType the transferType to set
	 */
	public void setTransferType(TransferType transferType) {
		this.transferType = transferType;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", transferType=" + transferType + ", from=" + from + ", to=" + to + ", date="
				+ date + "]";
	}
	
	
	

}
