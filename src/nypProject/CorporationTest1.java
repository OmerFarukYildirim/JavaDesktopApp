package nypProject;
import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class CorporationTest1 {

    private Corporation corporation = new Corporation("ABC Corp", "123 Main St");

    @Test
    public void testGetBillingInformation() {
        assertEquals("Corporation", corporation.getBillingInformation());
    }

    @Test
    public void testGetBankCode() {
    	corporation.setBankCode(123);
        assertEquals(123, corporation.getBankCode());
    }

    @Test
    public void testGetBankName() {
    	corporation.setBankName("Example Bank");
        assertEquals("Example Bank", corporation.getBankName());
    }

}
