package com.ezypay.assessment.subscription.model;

import java.util.HashMap;
import java.util.Map;

public enum SubscriptionType {

	DAILY("Daily"),
	WEEKLY("Weekly"),
	MONTHLY("Monthly");
	
	private static final Map<String, String> BY_KEY = new HashMap<>();
	
	private final String value;
	
	private SubscriptionType(String value) {
		this.value = value;
	}
	
	static {
		for (SubscriptionType e: values()) {
			BY_KEY.put(e.toString(), e.value);
		}
	}
	
	
	public String getByKey(String e) {
		return BY_KEY.get(e);
	}
	
}
