package geo.com.example.bo.comp6442_assignment_2_2016;

/**
 * Created by Bo on 7/05/2016.
 */


/**
 * This is the base class for the tree parser - they must provide the implementation
 * for evaluate(). Also provides a helper functions.
 *
 * @author Bo
 */
public abstract class ParserBase {

    public abstract double evaluate(String s) throws ParserException;

    public String deleteSpaces(String s) {
        return s.replaceAll("\\s+", "");
    }

}