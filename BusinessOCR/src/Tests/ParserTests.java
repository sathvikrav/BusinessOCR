package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import BusinessParser.ContactInfo;

public class ParserTests {
	ContactInfo info;
	
	@Test
	//Example #1 from the challenge
	public void testGetInfo1() {
		info = new ContactInfo("ASYMMETRIK LTD\r\n"
				+ "Mike Smith\r\n"
				+ "Senior Software Engineer\r\n"
				+ "(410)555-1234\r\n"
				+ "msmith@asymmetrik.com");
		
		assertEquals("Name: Mike Smith\r\n"
				+ "Phone: 4105551234\r\n"
				+ "Email: msmith@asymmetrik.com", info.toString());
	}
	
	@Test
	//Example #2 from the challenge
	public void testGetInfo2() {
		info = new ContactInfo("Foobar Technologies\r\n"
				+ "Analytic Developer\r\n"
				+ "Lisa Haung\r\n"
				+ "1234 Sentry Road\r\n"
				+ "Columbia, MD 12345\r\n"
				+ "Phone: 410-555-1234\r\n"
				+ "Fax: 410-555-4321\r\n"
				+ "lisa.haung@foobartech.com");
		
		assertEquals("Name: Lisa Haung\r\n"
				+ "Phone: 4105551234\r\n"
				+ "Email: lisa.haung@foobartech.com", info.toString());
	}
	
	@Test
	//Example #3 from the challenge
	public void testGetInfo3() {
		info = new ContactInfo("Arthur Wilson\r\n"
				+ "Software Engineer\r\n"
				+ "Decision & Security Technologies\r\n"
				+ "ABC Technologies\r\n"
				+ "123 North 11th Street\r\n"
				+ "Suite 229\r\n"
				+ "Arlington, VA 22209\r\n"
				+ "Tel: +1 (703) 555-1259\r\n"
				+ "Fax: +1 (703) 555-1200\r\n"
				+ "awilson@abctech.com");
		
		assertEquals("Name: Arthur Wilson\r\n"
				+ "Phone: 17035551259\r\n"
				+ "Email: awilson@abctech.com", info.toString());
	}
	
	@Test
	//Here, we test a weird case where the company is named after the person's last name and 
	//our method has to distinguish between the person and the company name
	public void testGetInfo4() {
		info = new ContactInfo("Huang Enterprises\r\n"
				+ "Ray Huang\r\n"
				+ "Senior Software Engineer\r\n"
				+ "(410)555-1234\r\n"
				+ "rhuang@huang.com");
		
		assertEquals("Name: Ray Huang\r\n"
				+ "Phone: 4105551234\r\n"
				+ "Email: rhuang@huang.com", info.toString());
	}

}
