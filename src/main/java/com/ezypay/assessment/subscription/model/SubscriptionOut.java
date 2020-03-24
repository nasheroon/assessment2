package com.ezypay.assessment.subscription.model;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionOut {

	private Double amount;
	private String type;
	private String invoiceDates;
	private boolean status;
	private String message;
	private List<String> errors = new ArrayList<String>();
	
	public SubscriptionOut(Double amount, String type, String invoiceDates) {
		super();
		this.amount = amount;
		this.type = type;
		this.invoiceDates = invoiceDates;
	}

	public SubscriptionOut() {
		super();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInvoiceDates() {
		return invoiceDates;
	}

	public void setInvoiceDates(String invoiceDates) {
		this.invoiceDates = invoiceDates;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
