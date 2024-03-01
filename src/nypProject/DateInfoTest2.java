package nypProject;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class DateInfoTest2 {

	DateInfo dateInfo = new DateInfo(1, 12, 2022);
	@Test
    public void testSetEndMonthZero() {
        dateInfo.setEndMonth(0);
        assertEquals(12, dateInfo.getEndMonth());
    }

    @Test
    public void testGetStartYear() {
        assertEquals(2022, dateInfo.getStartYear());
    }

    @Test
    public void testSetStartYear() {
        dateInfo.setStartYear(2023);
        assertEquals(2023, dateInfo.getStartYear());
    }

    @Test
    public void testToString() {
        String expectedToString = "DateInfo [startMonth=1, endMonth=12, startYear=2022]";
        assertEquals(expectedToString, dateInfo.toString());
    }
}
