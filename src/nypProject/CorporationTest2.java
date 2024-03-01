package nypProject;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class CorporationTest2 {
	// yeni corporation oluşturup, kendi atadığım issueDay ile assertEquals metodu ile karşılaştırıyorum  
	private Corporation corporation = new Corporation("ABC Corp", "123 Main St");
	  @Test
	    public void testGetIssueDay() {
	    	corporation.setIssueDay(1);
	        assertEquals(1, corporation.getIssueDay());
	    }

	    @Test
	    public void testGetIssueMonth() {
	    	corporation.setIssueMonth(1);
	        assertEquals(1, corporation.getIssueMonth());
	    }

	    @Test
	    public void testGetIssueYear() {
	    	corporation.setIssueYear(2022);
	        assertEquals(2022, corporation.getIssueYear());
	    }

	    @Test
	    public void testGetAccountNumber() {
	    	corporation.setAccountNumber(456789);
	        assertEquals(456789, corporation.getAccountNumber());
	    }
}
