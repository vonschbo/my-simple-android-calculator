package geo.com.example.bo.comp6442_assignment_2_2016;

/**
 * Created by Bo on 2/05/2016.
 */
public class ExpressionTree {

    ExprTreeNode root;

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