package geo.com.example.bo.comp6442_assignment_2_2016;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Bo on 2/05/2016.
 */
public class PostfixEvaluator {
    public double compute (String postfixExpr)
    {
        // Create a stack: all our operands are integers.
        Stack<Double> stack = new Stack<Double>();

        // Use the Scanner class to help us extract numbers or operators:
        Scanner scanner = new Scanner (postfixExpr);

        while (scanner.hasNext()) {

            if (scanner.hasNextDouble()) {
                // It's an operand => push it on the stack.
                double N = scanner.nextDouble();
                stack.push (N);
            }
            else {
                // It's an operator => apply the operator to the top two operands
                String opStr = scanner.next();
                double b = stack.pop(); // Note: b is popped first.
                double a = stack.pop();
                if (opStr.equals("+")) {
                    stack.push (a+b);
                }
                else if (opStr.equals("-")) {
                    stack.push (a-b);
                }
                else if (opStr.equals("*")) {
                    stack.push (a*b);
                }
                else if (opStr.equals("/")) {
                    stack.push (a/b);
                }
            }

        } // end-while

        // Result is on top.
        return stack.pop();
    }

}
