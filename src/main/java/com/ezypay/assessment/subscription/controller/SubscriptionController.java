package com.ezypay.assessment.subscription.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezypay.assessment.subscription.constants.SubscriptionConstants;
import com.ezypay.assessment.subscription.model.SubscriptionIn;
import com.ezypay.assessment.subscription.model.SubscriptionOut;
import com.ezypay.assessment.subscription.model.SubscriptionType;
import com.ezypay.assessment.subscription.util.CommonUtil;
import com.ezypay.assessment.subscription.validator.SubscriptionValidator;

@RestController
@RequestMapping("/api/subscription")
@CrossOrigin(origins = "http://localhost:4200")
public class SubscriptionController {

	@PostMapping("/")
	public @ResponseBody SubscriptionOut subscribe(@RequestBody SubscriptionIn input) {
		SubscriptionOut output = new SubscriptionOut();
		output.setStatus(true);
		
		SubscriptionValidator.validate(input, output);
		
		if(output.isStatus()) {
			BigDecimal bdAmount = new BigDecimal(input.getAmount());
			bdAmount.setScale(2);
			
			output.setAmount(bdAmount.doubleValue());
			output.setType(CommonUtil.getSubscriptionTypeValue(input.getType()));
			
			getInvoiceDates(input, output);
		}
		
		if(output.isStatus()) {
			output.setMessage("Success");
		} else {
			output.setMessage("Failed");
		}
		
		return output;
	}

	private SubscriptionOut getInvoiceDates(SubscriptionIn input, SubscriptionOut output) {
		List<String> invoiceDateList = new ArrayList<String>();
		
		LocalDate startDate = CommonUtil.convertStrToLocalDate(
				input.getFromDate(), SubscriptionConstants.DATE_FORMAT);
		
		LocalDate endDate = CommonUtil.convertStrToLocalDate(
				input.getToDate(), SubscriptionConstants.DATE_FORMAT);
		
		LocalDate date = startDate;
		
		boolean isFound = false;
		String startDay = input.getDay();
		String invoiceDate = input.getFromDate();
		
		if(input.getType().equalsIgnoreCase(SubscriptionType.DAILY.toString())) {
			isFound = true;
			invoiceDateList.add(invoiceDate);
			
			while ( date.plusDays(1).isBefore(endDate) || 
					date.plusDays(1).equals(endDate)) {
				date = date.plusDays(1);
				invoiceDate = CommonUtil.convertLocalDateToStr(
						date, SubscriptionConstants.DATE_FORMAT);
				invoiceDateList.add(invoiceDate);
			}
			
		} else if(input.getType().equalsIgnoreCase(SubscriptionType.WEEKLY.toString())) {
			if(startDay.equalsIgnoreCase(date.getDayOfWeek().toString())){
				isFound = true;
			} else {
				// get the next day
				while ( date.plusDays(1).isBefore(endDate) || 
						date.plusDays(1).equals(endDate)) {
					date = date.plusDays(1);
					if(startDay.equalsIgnoreCase(date.getDayOfWeek().toString())){
						isFound = true;
						invoiceDate = CommonUtil.convertLocalDateToStr(
								date, SubscriptionConstants.DATE_FORMAT);
						break;
					}
				}
			}
			
			if(isFound) {
				invoiceDateList.add(invoiceDate);
				
				while ( date.plusDays(7).isBefore(endDate) || 
						date.plusDays(7).equals(endDate)) {
					date = date.plusDays(7);
					invoiceDate = CommonUtil.convertLocalDateToStr(
							date, SubscriptionConstants.DATE_FORMAT);
					invoiceDateList.add(invoiceDate);
				}
			}
		} else if(input.getType().equalsIgnoreCase(SubscriptionType.MONTHLY.toString())) {
			if(date.getDayOfMonth() == Integer.parseInt(startDay)) {
				isFound = true;
			} else {
				// get the next day
				while ( date.plusDays(1).isBefore(endDate) || 
						date.plusDays(1).equals(endDate)) {
					date = date.plusDays(1);
					if(date.getDayOfMonth() == Integer.parseInt(startDay)) {
						isFound = true;
						invoiceDate = CommonUtil.convertLocalDateToStr(
								date, SubscriptionConstants.DATE_FORMAT);
						break;
					}
				}
			}
			
			if(isFound) {
				invoiceDateList.add(invoiceDate);
				startDate = date;
				int increment = 1;
				while ( startDate.plusMonths(increment).isBefore(endDate) || 
						startDate.plusMonths(increment).equals(endDate)) {
					date = startDate.plusMonths(increment);
					invoiceDate = CommonUtil.convertLocalDateToStr(
							date, SubscriptionConstants.DATE_FORMAT);
					invoiceDateList.add(invoiceDate);
					increment++;
				}
			}
		}
		
		if(invoiceDateList.size() > 0) {
			String invoiceDates = "[";
			for(int i = 0; i < invoiceDateList.size(); i++) {
				invoiceDates += "\"";
				String invDate = invoiceDateList.get(i);
				invoiceDates += invDate;
				invoiceDates += "\"";
				
				if(i < invoiceDateList.size() - 1)
					invoiceDates += ", ";
			}
			invoiceDates += "]";
			output.setInvoiceDates(invoiceDates);
		}
		
		return output;
	}
}
