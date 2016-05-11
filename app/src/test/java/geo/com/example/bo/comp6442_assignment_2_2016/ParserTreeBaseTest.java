package geo.com.example.bo.comp6442_assignment_2_2016;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bo on 11/05/2016.
 */
public class ParserTreeBaseTest {

    @Test
    public void testSpace() throws Exception {
        ParserTreeNew t = new ParserTreeNew();
        String s1 = "1 +   1 * 55";
        String s2 = "1+1*55";
        //assertTrue(t.deleteSpaces(s1)==s2);
        assertEquals(t.deleteSpaces(s1),s2);
    }
}
