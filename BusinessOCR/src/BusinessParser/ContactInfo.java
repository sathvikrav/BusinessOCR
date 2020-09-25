package BusinessParser;

public class ContactInfo {

	private String document;
	
	public ContactInfo(String document) {
		this.document = document;
	}
	
	/* For the getName method, we are capitalizing on the fact that generally
	 * in business email ID's, there is at least the first initial and the last
	 * name that comes before the at. So, we split the email and just look at the 
	 * part before the @.*/
	public String getName() {
		String[] lines = this.document.split("\n");
		String name_email = this.getEmailAddress().toLowerCase().split("@")[0]; //we just want the part of the email before the @ that may give us some hint as to what the name is
		
		String name = ""; //initialize param to be returned
		for (String line: lines) {
			String[] words = line.strip().split(" ");//get rid of starting or trailing spaces and split each line by spaces
			for (String word: words) {
				//for each word, we see if it is inside the email id (which is generally the case for first or last names)
				//and then in the weird case where the founder of the company names the company after himself, which
				//might happen in the case of family-owned businesses, we compare the first letter of the email and first
				//letter of the first name just to make sure
				if (name_email.contains(word.toLowerCase()) && name_email.charAt(0) == line.strip().toLowerCase().charAt(0)) {
					name = line.strip();
				}
			}
		}
		
		return name;
	}

	/* In getPhone, we iterate through each line to find a line 
	 * that contains at least 10 digits and does not contain the word
	 * 'fax'*/
	public String getPhoneNumber() {
		String[] lines = this.document.split("\n");
		
		/* Here we record the number of digits and collect them using
		 * collectDigits. If the number of digits < 10, we set numDigits
		 * back to 0 and collectDigits to "" and keep iterating through the lines*/
		int numDigits = 0;
		String collectDigits = "", phoneNumber = "";
		for (String line: lines) {
			//Here we parse every character in the line
			for (int i = 0; i < line.length(); i++) {
				if (Character.isDigit(line.charAt(i))) {
					numDigits++;
					collectDigits += line.charAt(i);
				}
			}
			
			//Check if digits >= 10 and line does not contain fax
			if (numDigits >= 10 && !line.toLowerCase().contains("fax")) { //we lowercase the line to ignore the case of Fax in case it comes up
				phoneNumber = collectDigits;
			} else {
				numDigits = 0;
				collectDigits = "";
			}
		}
		
		return phoneNumber;
	}
	
	/* Gets the email address by parsing splitting the document with \n characters
	 * and searching for the line that contains an @ symbol.*/
	public String getEmailAddress() {
		//Splitting the document
		String[] lines = this.document.split("\n");
		
		String email_line = ""; //initializing return value
		for(String line: lines) {
			/* We split by words because in case the line is formatted as 'Email: <email_address>' 
			 * then we don't want the first part of the string, but just the part that is in <>*/
			String[] words = line.split(" "); 
			
			/* Here we iterate through every word to find the word with @ */
			for (String word: words) {
				if (word.contains("@")) {
					email_line = word;
				}
			}
		}
		
		return email_line;
	}
	
	public String toString() {
		return "Name: " + this.getName() + "\r\n" + "Phone: " + this.getPhoneNumber() + "\r\n" + "Email: " + this.getEmailAddress();
	}
}
