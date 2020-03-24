package com.ezypay.assessment.subscription.model;

public class SubscriptionIn {

    private String type;
    private Double amount;
    private String day;
    private String fromDate;
    private String toDate;
    
	public SubscriptionIn(String type, Double amount, String day, String fromDate, String toDate) {
		super();
		this.type = type;
		this.amount = amount;
		this.day = day;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	public SubscriptionIn() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubscriptionIn [type=");
		builder.append(type);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", day=");
		builder.append(day);
		builder.append(", fromDate=");
		builder.append(fromDate);
		builder.append(", toDate=");
		builder.append(toDate);
		builder.append("]");
		return builder.toString();
	}

}
