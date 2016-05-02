package geo.com.example.bo.comp6442_assignment_2_2016;

import java.util.Stack;

/**
 * Created by Bo on 2/05/2016.
 */
public class ExpressionTree {

    ExprTreeNode root;
    public void parseExpression (String expr)
    {
        root = parse (expr);
    }


    ExprTreeNode parse (String expr)
    {
        ExprTreeNode node = new ExprTreeNode ();

        // Note: expr.charAt(0) is a left paren.
        // First, find the matching right paren.
        int m = findMatchingRightParen (expr, 1);
        String leftExpr = expr.substring (1, m+1);

        // Bottom-out condition:
        if (m == expr.length()-1) {
            // It's at the other end => this is a leaf.
            String operandStr = expr.substring (1, expr.length()-1);
            node.isLeaf = true;
            node.value = getValue (operandStr);
            return node;
        }

        // Otherwise, there's a second operand and an operator.

        // Find the left paren to match the rightmost right paren.
        int n = findMatchingLeftParen (expr, expr.length()-2);
        String rightExpr = expr.substring (n, expr.length()-1);

        // Recursively parse the left and right substrings.
        node.left = parse (leftExpr);
        node.right = parse (rightExpr);
        node.op = expr.charAt (m+1);

        return node;
    }


    int findMatchingRightParen (String s, int leftPos)
    {
        Stack<Character> stack = new Stack<Character>();
        stack.push (s.charAt(leftPos));
        for (int i=leftPos+1; i<s.length(); i++) {
            char ch = s.charAt (i);
            if (ch == '(') {
                stack.push (ch);
            }
            else if (ch == ')') {
                stack.pop ();
                if ( stack.isEmpty() ) {
                    // This is the one.
                    return i;
                }
            }
        }
        // If we reach here, there's an error.
        System.out.println ("ERROR: findRight: s=" + s + " left=" + leftPos);
        return -1;
    }


    int findMatchingLeftParen (String s, int rightPos)
    {
        Stack<Character> stack = new Stack<Character>();
        stack.push (s.charAt(rightPos));
        for (int i=rightPos-1; i>=0; i--) {
            char ch = s.charAt (i);
            if (ch == ')') {
                stack.push (ch);
            }
            else if (ch == '(') {
                stack.pop ();
                if ( stack.isEmpty() ) {
                    // This is the one.
                    return i;
                }
            }
        }
        // If we reach here, there's an error.
        System.out.println ("ERROR: findLeft: s=" + s + " right=" + rightPos);
        return -1;
    }

    double getValue (String s)
    {
        try {
            double k = Double.parseDouble (s);
            return k;
        }
        catch (NumberFormatException e) {
            return -1;
        }

    }


    public double computeValue ()
    {
        return compute (root);
    }

    double compute (ExprTreeNode node)
    {
        if (node.isLeaf) {
            return node.value;
        }

        // Otherwise do left and right, and add.
        double leftValue = compute (node.left);
        double rightValue = compute (node.right);

        if (node.op == '+') {
            return leftValue + rightValue;
        }
        else if (node.op == '-') {
            return leftValue - rightValue;
        }
        else if (node.op == '*') {
            return leftValue * rightValue;
        }
        else {
            return leftValue / rightValue;
        }

    }


    public String convertToPostfix ()
    {
        String str = postOrder (root);
        return str;
    }

    String postOrder (ExprTreeNode node)
    {
        String result = "";
        if (node.left != null) {
            result += postOrder (node.left);
        }
        if (node.right != null) {
            result += " " + postOrder (node.right);
        }
        if (node.isLeaf) {
            result += " " + node.value;
        }
        else {
            result += " " + node.op;
        }
        return result;
    }
}