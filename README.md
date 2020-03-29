# MaskContact

This project is to mask contact field input.

### Assumptions
I am using following assumptions to check if the given input is phone number or email id. 

	// 10 digits are only allowed phone number
	TELEPHONE_REGEX = "\\d{10}";
	
	// Email part should starts with alphanumeric characters. It can have '.', '-' and '_'
	// Domain will have two part separated by '.' each part can have only alphanumeric characters.
    EMAIL_REGEX = "^[a-z0-9][a-z0-9_-]+(\\.[a-z0-9][a-z0-9_-]+)*@[a-z0-9]+(\\.[a-z0-9]+)$";
    
If input is more than 80 characters or empty string then it is invalid string and method would not mask the input.

### Requirments
• The contact field on the website has the label "Email or Phone" with a character length of 80.  

• Bill has said the email domain does not have to be masked, but the customer's email must be masked except for the first and last character.  
 
• Bill said for phone numbers we need to mask the middle three digits and leave everything else in the clear.  
 
• Bill says he wants a simple method he can pass this data too and get a masked result back.  

• Bill also needs to see some test proof that this method works.  
