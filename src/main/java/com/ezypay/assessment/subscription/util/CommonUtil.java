package com.ezypay.assessment.subscription.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.ezypay.assessment.subscription.model.SubscriptionType;

public class CommonUtil {

	public static LocalDate convertStrToLocalDate(String dateStr, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDate.parse(dateStr, formatter);
	}
	
	public static String convertLocalDateToStr(LocalDate date, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return formatter.format(date);
	}
	
	public static boolean isContainSubscriptionType(String e) {
		for(SubscriptionType key: SubscriptionType.values()) {
			if(e.toUpperCase().equalsIgnoreCase(key.toString())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isDayOfWeek(String e) {
		for(DayOfWeek key: DayOfWeek.values()) {
			if(e.toUpperCase().equalsIgnoreCase(key.toString())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.chars().allMatch(Character::isDigit);
    }
	
	public static long monthsInBetween(LocalDate startdate, LocalDate enddate) {
		return ChronoUnit.MONTHS.between(startdate, enddate);
	}

	public static String getSubscriptionTypeValue(String e) {
		for(SubscriptionType key: SubscriptionType.values()) {
			if(e.toUpperCase().equalsIgnoreCase(key.toString())) {
				return key.getByKey(key.toString());
			}
		}
		
		return "";
	}

}
