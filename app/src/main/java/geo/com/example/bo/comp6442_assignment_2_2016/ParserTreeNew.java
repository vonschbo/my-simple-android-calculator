package geo.com.example.bo.comp6442_assignment_2_2016;

/**
 * Created by Bo on 7/05/2016.
 */
public class ParserTreeNew extends ParserBase {

    private String term;		// term to evaluate
    private int currentTermPos;		// char position in term

    private enum TokenType {
        NUMBER, PLUS, MINUS, MULTIPY, DIVIDE, OPENING_BRACKET, CLOSING_BRACKET, EOT;
    }

    @Override
    public double evaluate(String s) throws ParserException {
        if(s.charAt(0)=='-'){
            String s1 = s.substring(1,s.length());
            return Double.parseDouble("-"+s1);
        }else {
            currentTermPos = 0;

            term = deleteSpaces(s);

            ParserTreeNode rootNode = parseAddSub();

            return evaluateParserTree(rootNode);
        }
    }

    /**
     * Get the next token of the string. In case it's a operator or a bracket, the token is already consumed,
     * that means the character of the string term is already processed and pointer is moved to the next character
     * of the string term. In case it's a number the number has to be retrieved explicitly by calling {@link "extractNextNumber" }
     * @return next token in string term
     * @throws ParserException
     */
    private TokenType getNextTokenType() throws ParserException
    {
        if (currentTermPos == term.length())
        {
            return TokenType.EOT;
        }
        else if (term.charAt(currentTermPos) >= '0' && term.charAt(currentTermPos) <= '9')
        {
            return TokenType.NUMBER;
        }
        else if (term.charAt(currentTermPos) == '+')
        {
            currentTermPos++;
            return TokenType.PLUS;
        }
        else if (term.charAt(currentTermPos) == '-')
        {
            currentTermPos++;
            return TokenType.MINUS;
        }
        else if (term.charAt(currentTermPos) == '*')
        {
            currentTermPos++;
            return TokenType.MULTIPY;
        }
        else if (term.charAt(currentTermPos) == '/')
        {
            currentTermPos++;
            return TokenType.DIVIDE;
        }
        else if (term.charAt(currentTermPos) == '(')
        {
            currentTermPos++;
            return TokenType.OPENING_BRACKET;
        }
        else if (term.charAt(currentTermPos) == ')')
        {
            currentTermPos++;
            return TokenType.CLOSING_BRACKET;
        }
        else
        {
            throw new ParserException(term.charAt(currentTermPos) + " is not a valid token");
        }
    }

    /**
     * Move back to previous token. Used in case the next retrieved token can currently not processed.
     */
    private void restoreLastTokenType()
    {
        currentTermPos--;
    }

    /**
     * Parse a term with multiplier or divider operator -> Operator priority 2.
     * @return
     * @throws ParserException
     */
    private ParserTreeNode parseMulDiv() throws ParserException
    {
        ParserTreeNode rootNode = parseSimpleTerm();
        TokenType nextToken = getNextTokenType();

        while (nextToken == TokenType.MULTIPY || nextToken == TokenType.DIVIDE)
        {
            ParserTreeNode newRootNode;
            if (nextToken == TokenType.MULTIPY)
            {
                newRootNode = new ParserTreeNode('*');
            }
            else
            {
                newRootNode = new ParserTreeNode('/');
            }

            // previous root node becomes new left tree
            newRootNode.leftTree = rootNode;
            newRootNode.rightTree = parseSimpleTerm();
            // set new root node active
            rootNode = newRootNode;

            nextToken = getNextTokenType();
        }
        restoreLastTokenType();
        return rootNode;
    }

    /**
     * Parse a term with addition or subtraction operator -> Operator priority 1.
     *
     * @return
     * @throws ParserException
     */
    private ParserTreeNode parseAddSub() throws ParserException
    {
        ParserTreeNode rootNode = parseMulDiv();
        TokenType nextToken = getNextTokenType();

        while (nextToken == TokenType.PLUS || nextToken == TokenType.MINUS)
        {
            ParserTreeNode newRootNode;
            if (nextToken == TokenType.PLUS)
            {
                newRootNode = new ParserTreeNode('+');
            }
            else
            {
                newRootNode = new ParserTreeNode('-');
            }
            // previous root node becomes new left tree
            newRootNode.leftTree = rootNode;
            newRootNode.rightTree = parseMulDiv();
            // set new root node active
            rootNode = newRootNode;

            nextToken = getNextTokenType();
        }
        restoreLastTokenType();
        return rootNode;
    }

    /**
     * Parse a single simple term, this is either a number or a nested term with brackets.
     * @return
     * @throws ParserException
     */
    private ParserTreeNode parseSimpleTerm() throws ParserException
    {
        TokenType nextToken = getNextTokenType();
        ParserTreeNode rootNode = null;
        if (nextToken == TokenType.NUMBER)
        {
            double num = extractNextNumber();
            rootNode = new ParserTreeNode(num);
        }
        else if (nextToken == TokenType.OPENING_BRACKET)
        {
            rootNode = parseAddSub();
            nextToken = getNextTokenType();
            if (nextToken != TokenType.CLOSING_BRACKET)
            {
                throw new ParserException("Missing closing bracket");
            }
        }
        return rootNode;
    }

    /**
     * Parses a single integer number.
     * @return
     * @throws ParserException
     */
    private double extractNextNumber() throws ParserException
    {
        int posNumEnd;
        for (posNumEnd = currentTermPos; posNumEnd < term.length(); posNumEnd++)
        {
            if ((term.charAt(posNumEnd) < '0' || term.charAt(posNumEnd) > '9') && term.charAt(posNumEnd) != '.')
                break;
        }

        if (posNumEnd > term.length())
            // given term does not start with a number
            throw new ParserException(term + " is not a number.");

        // extract the number string
        String sub = term.substring(currentTermPos, posNumEnd);
        // 'sub' contains now just the number
        double x;
        try {
            x = Double.parseDouble(sub);
        } catch (NumberFormatException ex) {
            throw new ParserException("String to number parsing exception: " + sub);
        }

        // move on to next token
        currentTermPos = currentTermPos + (posNumEnd - currentTermPos);
        return x;
    }



    /** This function evaluates a parsertree and returns the result of the expression.
     * If an error occurs, it returns Double.NaN.
     * Calls itself recursive. Throws NumberFormat exception.
     * @param tree: the root node of the parser tree
     */
    private double evaluateParserTree(ParserTreeNode tree)
    {
        if (tree == null)
            return Double.NaN;

        if (tree.value.toString().equals("+"))
            return evaluateParserTree(tree.leftTree) + evaluateParserTree(tree.rightTree);
        else if (tree.value.toString().equals("-"))
            return evaluateParserTree(tree.leftTree) - evaluateParserTree(tree.rightTree);
        else if (tree.value.toString().equals("*"))
            return evaluateParserTree(tree.leftTree) * evaluateParserTree(tree.rightTree);
        else if (tree.value.toString().equals("/"))
            return evaluateParserTree(tree.leftTree) / evaluateParserTree(tree.rightTree);
        else
            return Double.valueOf(tree.value.toString()).doubleValue();

    }

    public static void main(String[] args) throws ParserException {
        ParserTreeNew pt = new ParserTreeNew();
        System.out.println(pt.evaluate("(1+2)*6"));
        System.out.println(pt.evaluate("(2 + 3*4/5*(10*4))"));
        System.out.println(pt.evaluate("(1+2)*6"));
        System.out.println(pt.evaluate("1-12"));
        System.out.println(pt.evaluate("2.3+5.3"));
        System.out.println(pt.evaluate("-1"));
    }
}
