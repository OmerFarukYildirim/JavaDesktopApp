package nypProject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DateInfoTest1 {

	DateInfo dateInfo = new DateInfo(1, 12, 2022);

    @Test
    public void testGetStartMonth() {
        assertEquals(1, dateInfo.getStartMonth());
    }

    @Test
    public void testSetStartMonth() {
        dateInfo.setStartMonth(5);
        assertEquals(5, dateInfo.getStartMonth());
    }

    @Test
    public void testGetEndMonth() {
        assertEquals(12, dateInfo.getEndMonth());
    }

    @Test
    public void testSetEndMonth() {
        dateInfo.setEndMonth(8);
        assertEquals(8, dateInfo.getEndMonth());
    }

    
}
