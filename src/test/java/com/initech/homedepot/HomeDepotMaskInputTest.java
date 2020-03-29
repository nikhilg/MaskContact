package com.initech.homedepot;

import static org.junit.Assert.*;

import org.junit.Test;

public class HomeDepotMaskInputTest {
	
	private String[] validEmails = new String[]{"abc-YHA-123@yahoo.com",
			"abc-6121@gmail.com", "abc.100.def@modis.net123",
			"abc111@hotmail.com", "abcdef.100@hotmail.org", "nikhil@1.com",
			"vamsi-hannie.modi@modis.com", "ng_hn-uy.gupa.test@myemail.com"};
	
	private String[] inValidEmails = new String[]{
			"nkg@gmail.com.net", "nkg+100@gmail.com",
			"hags-300.61@yahoo-test.com", "2482047553", 
			"_hannie@modiscom", ".vamsi@modis.com", "_vamsi@modis.com" };

	@Test
	public void testIsValidContactFieldInputLength() {
		String invalidLengthContactStr = "abcdefgasjaaabcdefgasjaaabcdefgasjaaabcdefgasjaaabcdefgasjaaabcdefgasjaaabcdefgasjaaabcdefgasja";
		String emptyContactStr = "";
		String validLengthContactStr = "82931912";
		
		assertFalse(HomeDepotProblem.isValidContactFieldInputLength(invalidLengthContactStr));
		assertFalse(HomeDepotProblem.isValidContactFieldInputLength(emptyContactStr));
		assertTrue(HomeDepotProblem.isValidContactFieldInputLength(validLengthContactStr));
//		fail("Not yet implemented");
	}

	@Test
	public void testIsContactPhoneNumber() {
		String invalidPhoneNumber1 = "829319125";
		String invalidPhoneNumber2 = "asa7222121";
		String validPhoneNumber = "2482047553";
		
		assertFalse(HomeDepotProblem.isContactPhoneNumber(invalidPhoneNumber1));
		assertFalse(HomeDepotProblem.isContactPhoneNumber(invalidPhoneNumber2));
		assertTrue(HomeDepotProblem.isContactPhoneNumber(validPhoneNumber));
	}
	
	
	@Test
	public void testIsContactEmailId() {
		for (String emailId : validEmails) {
			assertTrue(emailId, HomeDepotProblem.isContactEmailId(emailId));
		}
		
		for (String emailId : inValidEmails) {
			assertFalse(emailId, HomeDepotProblem.isContactEmailId(emailId));
		}
	}
	
	@Test
	public void testMaskTelephoneNumber(){
		String phoneNumStr = "2482047553";
		assertEquals("248***7553", HomeDepotProblem.maskTelephoneNumber(phoneNumStr));
	}
	
	@Test
	public void testMaskEmailId(){
		String emailStr = "Hannie@modis.com";
		assertEquals("H****e@modis.com", HomeDepotProblem.maskEmailId(emailStr));
	}
	
	@Test
	public void testMaskEmailOrPhoneWithValidInput(){
		String contactStr = "Hannie123@homedepot.com";
		
		String maskedContactInput = HomeDepotProblem.maskEmailOrPhone(contactStr);
		assertEquals("H*******3@homedepot.com", maskedContactInput);
		
		contactStr = "5131234567";
		maskedContactInput = HomeDepotProblem.maskEmailOrPhone(contactStr);
		assertEquals("513***4567", maskedContactInput);
	}
	
	@Test
	public void testMaskEmailOrPhoneWithInValidInput(){
		String contactStr = "";
		String invalidMsg1 = "Contact string is invalid. Either it is beyond max length 80 or empty.It can not be masked.";
		String invalidMsg2 = "Contact string is neither email nor phone number. It can not be masked.";

		String maskedContactInput = HomeDepotProblem.maskEmailOrPhone(contactStr);
		assertEquals(invalidMsg1, maskedContactInput);
		
		contactStr = "abcadafas";
		maskedContactInput = HomeDepotProblem.maskEmailOrPhone(contactStr);
		assertEquals(invalidMsg2, maskedContactInput);
	}
}
