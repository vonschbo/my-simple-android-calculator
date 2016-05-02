package geo.com.example.bo.comp6442_assignment_2_2016;

/**
 * Created by Bo on 2/05/2016.
 */
public class ExpressionParser {
    public static void main (String[] argv)
    {
        String s = "((1)+(2))";
        test (s);

        s = "(((1)+(2))*(3))";
        test (s);

        s = "(((35)-((3)*((3)+(2))))/(4))";
        test (s);
    }


    static void test (String s)
    {
        ExpressionTree expTree = new ExpressionTree ();
        expTree.parseExpression (s);
        double v = expTree.computeValue ();
        System.out.println (s + " = " + v);
        String postStr = expTree.convertToPostfix ();
        PostfixEvaluator postfixEval = new PostfixEvaluator();
        int p = postfixEval.compute (postStr);
        System.out.println ("Postfix: " + postStr + "    postfix eval=" + p);
    }
}
