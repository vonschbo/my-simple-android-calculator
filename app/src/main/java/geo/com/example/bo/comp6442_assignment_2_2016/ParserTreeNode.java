package geo.com.example.bo.comp6442_assignment_2_2016;

/**
 * Created by Bo on 7/05/2016.
 *
 * This class is to create the basic tree structure.
 *
 * @author Bo
 *
 */

public class ParserTreeNode {

    public ParserTreeNode   leftTree;
    public ParserTreeNode   rightTree;
    public Object           value;

    public ParserTreeNode(ParserTreeNode l, ParserTreeNode r, Object v)
    {
        leftTree = l;
        rightTree = r;
        value = v;
    }

    public ParserTreeNode(Object l, Object r, Object v)
    {
        leftTree = new ParserTreeNode(l);
        rightTree = new ParserTreeNode(r);
        value = v;
    }

    public ParserTreeNode()
    {
        leftTree = rightTree = null;
        value = null;
    }

    public ParserTreeNode(Object v)
    {
        leftTree = rightTree = null;
        value = v;
    }

    public void printTreePostOrder()
    {
        printTreePostOrder(this);
        System.out.println();
    }

    private void printTreePostOrder(ParserTreeNode tree)
    {
        if (tree == null)
            return;
        printTreePostOrder(tree.leftTree);
        printTreePostOrder(tree.rightTree);
        System.out.print(tree.value.toString() + " ");
    }

}