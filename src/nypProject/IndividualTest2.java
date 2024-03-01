package nypProject;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class IndividualTest2 {

	
		private Individual individual = new Individual("John Doe", "123 Main St", "1234-5678-9012-3456", 12, 2023, 123);
	 @Test
	    public void testGetExpireYear() {
	        assertEquals(2023, individual.getExpireYear());
	    }

	    @Test
	    public void testSetExpireYear() {
	        individual.setExpireYear(2025);
	        assertEquals(2025, individual.getExpireYear());
	    }

	    @Test
	    public void testGetBillingInformation() {
	        assertEquals("Individual", individual.getBillingInformation());
	    }

	    @Test
	    public void testToString() {
	        String expectedToString = "Individual [creaditCardNr=1234-5678-9012-3456, expireeMonth=12, expireYear=2023, CCV=123]";
	        assertEquals(expectedToString, individual.toString());
	    }

}
