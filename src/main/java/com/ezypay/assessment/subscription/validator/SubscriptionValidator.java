package com.ezypay.assessment.subscription.validator;

import java.time.LocalDate;

import com.ezypay.assessment.subscription.constants.SubscriptionConstants;
import com.ezypay.assessment.subscription.model.SubscriptionIn;
import com.ezypay.assessment.subscription.model.SubscriptionOut;
import com.ezypay.assessment.subscription.model.SubscriptionType;
import com.ezypay.assessment.subscription.util.CommonUtil;

public class SubscriptionValidator {

	public static SubscriptionOut validate(SubscriptionIn input, SubscriptionOut output) {
		
		if(input.getAmount() == null || input.getAmount() == 0) {
			output.getErrors().add("Subscription amount is empty");
		}
		
		if(input.getType() == null || input.getType().length() == 0) {
			output.getErrors().add("Subscription type is empty");
		} else if (!CommonUtil.isContainSubscriptionType(input.getType())) {
			output.getErrors().add("Subscription type is invalid");
		} else {
			// valid type
			// check for day value
			if(input.getType().equalsIgnoreCase(SubscriptionType.WEEKLY.toString())) {
				if(!CommonUtil.isDayOfWeek(input.getDay())) {
					output.getErrors().add("Day is invalid");
				}
			}
			
			if(input.getType().equalsIgnoreCase(SubscriptionType.MONTHLY.toString())) {
				if(!CommonUtil.isNumeric(input.getDay())) {
					output.getErrors().add("Day is not a number");
				} else {
					int day = Integer.parseInt(input.getDay());
					
					if( day < 1 || day > 31) {
						output.getErrors().add("Day is invalid");	
					}
				}
			}
		}
		
		if( input.getFromDate() != null && input.getFromDate().length() > 0 &&
			input.getToDate() != null && input.getToDate().length() > 0) {
			// to check if start date and end date 3 months or less apart
			LocalDate startDate = CommonUtil.convertStrToLocalDate(input.getFromDate(), SubscriptionConstants.DATE_FORMAT);
			LocalDate endDate = CommonUtil.convertStrToLocalDate(input.getToDate(), SubscriptionConstants.DATE_FORMAT);
		
			if(CommonUtil.monthsInBetween(startDate, endDate) > SubscriptionConstants.MAXIMUM_DURATION_MONTHS) {
				output.getErrors().add("Maximum duration of months exceeded");
			}
		} else {
			if(input.getFromDate() == null || input.getFromDate().length() == 0) {
				output.getErrors().add("Start date is empty");
			}
			
			if(input.getToDate() == null || input.getToDate().length() == 0) {
				output.getErrors().add("End date is empty");
			}
		}
		
		if(output.getErrors().size() > 0) {
			output.setStatus(false);
		}
		
		return output;
	}
	
	

}
