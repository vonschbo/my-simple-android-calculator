package geo.com.example.bo.comp6442_assignment_2_2016;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bo on 11/05/2016.
 */
public class ParserTreeNewTest {
    @Test
    public void testBasicOPEva() throws Exception{
        ParserTreeNew t = new ParserTreeNew();
        assertFalse(t.evaluate("-1")==1);
        assertTrue(t.evaluate("-1") == -1.0);
        assertTrue(t.evaluate("2") == 2.0);
        assertTrue(t.evaluate("2221")==2221.0);
    }

    @Test
    public void testPM() throws Exception{
        ParserTreeNew t = new ParserTreeNew();
        assertTrue(t.evaluate("2+2")==4.0);
        assertTrue(t.evaluate("99-18")==81.0);
        assertTrue(t.evaluate("55-0") == 55.0);
        assertTrue(t.evaluate("0-100") == -100.0);
        assertTrue(t.evaluate("1+(1+3)")==5.0);
        assertTrue(t.evaluate("2-5+7")==4.0);
    }

    @Test
    public void testM() throws Exception{
        ParserTreeNew t = new ParserTreeNew();
        assertTrue(t.evaluate("2*2")==4.0);
        assertTrue(t.evaluate("99/18")==5.5);
        assertTrue(t.evaluate("(2+3)/5")==1.0);
        assertTrue(t.evaluate("(2*3)/6")==1.0);
        assertTrue(t.evaluate("2*(6/6)")==2.0);
    }


}
