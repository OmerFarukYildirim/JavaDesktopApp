package nypProject;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class JournalTest1 {

    private Journal journal = new Journal("Journal Name", "123-456-789", 12, 9.99);
    
    @Test
    public void testSetName() {
        journal.setName("New Journal Name");
        assertEquals("New Journal Name", journal.getName());
    }
    @Test
    public void testSetIssn() {
        journal.setIssn("987-654-321");
        assertEquals("987-654-321", journal.getIssn());
    }
}
