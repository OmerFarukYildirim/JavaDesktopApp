package nypProject;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class IndividualTest1 {

    private Individual individual = new Individual("John Doe", "123 Main St", "1234-5678-9012-3456", 12, 2023, 123);


    @Test
    public void testGetCreaditCardNr() {
        assertEquals("1234-5678-9012-3456", individual.getCreaditCardNr());
    }

    @Test
    public void testSetCreaditCardNr() {
        individual.setCreaditCardNr("9876-5432-1098-7654");
        assertEquals("9876-5432-1098-7654", individual.getCreaditCardNr());
    }

    @Test
    public void testGetExpireeMonth() {
        assertEquals(12, individual.getExpireeMonth());
    }

    @Test
    public void testSetExpireeMonth() {
        individual.setExpireeMonth(6);
        assertEquals(6, individual.getExpireeMonth());
    }

   
}
