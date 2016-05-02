package geo.com.example.bo.comp6442_assignment_2_2016;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Bo on 2/05/2016.
 */
public class PostfixEvaluator {
    public int compute (String postfixExpr)
    {
        // Create a stack: all our operands are integers.
        Stack<Integer> stack = new Stack<Integer>();

        // Use the Scanner class to help us extract numbers or operators:
        Scanner scanner = new Scanner (postfixExpr);

        while (scanner.hasNext()) {

            if (scanner.hasNextInt()) {
                // It's an operand => push it on the stack.
                int N = scanner.nextInt();
                stack.push (N);
            }
            else {
                // It's an operator => apply the operator to the top two operands
                String opStr = scanner.next();
                int b = stack.pop(); // Note: b is popped first.
                int a = stack.pop();
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
