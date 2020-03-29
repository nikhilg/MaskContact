package com.initech.homedepot;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeDepotProblem {

	// 10 digits are only allowed phone number
	private static final String TELEPHONE_REGEX = "\\d{10}";
	
	// Email part should starts with alphanumeric characters. It can have '.', '-' and '_'
	// Domain will have two part separated by '.' each part can have only alphanumeric characters.
	private static final String EMAIL_REGEX = "^[a-z0-9][a-z0-9_-]+(\\.[a-z0-9][a-z0-9_-]+)*@[a-z0-9]+(\\.[a-z0-9]+)$";
//	private static final String EMAIL_REGEX = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9]+(\\.[a-z0-9]+)$";
//	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static void main(String[] args) {
		
	}

	public static String maskEmailOrPhone(String contactStr) {
		String output = "";
		boolean validLength = isValidContactFieldInputLength(contactStr);
		if (validLength) {
			boolean isContactPhoneNum = isContactPhoneNumber(contactStr);
			if (isContactPhoneNum) {
				output = maskTelephoneNumber(contactStr);
			} else {
				boolean isContactEmailId = isContactEmailId(contactStr);
				if (isContactEmailId) {
					output = maskEmailId(contactStr);
				}else{
					output = "Contact string is neither email nor phone number. It can not be masked.";
				}
			}
		}else{
			output = "Contact string is invalid. Either it is beyond max length 80 or empty.It can not be masked.";
		}
		return output;
	}

	public static boolean isValidContactFieldInputLength(String contactStr) {
		boolean valid = false;
		if(contactStr.length() > 80 ){
			System.out.println("Contact entry can not be more than 50 characters.");
		}else if(contactStr.isEmpty()){
			System.out.println("Contact field can not be empty.");
		}else{
			valid = true;
		}
		return valid;
	}

	public static boolean isContactPhoneNumber(String contactStr) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(TELEPHONE_REGEX);
		Matcher matcher = pattern.matcher(contactStr);
		if(matcher.matches()){
//			System.out.println("Given contact is valid phone number.");
			valid = true;
		}
		return valid;
	}
	
	public static boolean isContactEmailId(String contactStr) {
		boolean valid = false;
		Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(contactStr);
		if(matcher.matches()){
//			System.out.println("Given contact is valid email id.");
			valid = true;
		}
		return valid;
	}

	public static String maskEmailId(String emailStr) {
		String preDomainPart = emailStr.substring(0, emailStr.indexOf('@'));
		String domainPart = emailStr.substring(emailStr.indexOf('@'));

        char[] maskChars = new char[preDomainPart.length()-2]  ;
        Arrays.fill(maskChars,'*');
        String maskedPortion = new String(maskChars);
		
		StringBuilder sb = new StringBuilder();
		sb.append(preDomainPart.substring(0,1));
		sb.append(maskedPortion);
		sb.append(preDomainPart.substring(preDomainPart.length()-1));
		sb.append(domainPart);
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static String maskTelephoneNumber(String phoneStr) {
//		if(phoneStr.length() !=10){
//			System.out.println("Phone number does not have 10 digits");
//			return "Invalid Phone number";
//		}		
		StringBuilder sb = new StringBuilder("***");
		String maskedPhoneNumberStr = phoneStr.substring(0,3) + sb.toString() + phoneStr.substring(6, phoneStr.length());
		System.out.println(maskedPhoneNumberStr);
		return maskedPhoneNumberStr;
	}
}
