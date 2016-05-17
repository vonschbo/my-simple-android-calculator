package geo.com.example.bo.comp6442_assignment_2_2016;

/**
 * Created by Bo on 7/05/2016.
 *
 * This class is to create the basic tree structure.
 *
 * @author Bo
 *
 */

public class TreeNode {

    public TreeNode leftTree;
    public TreeNode rightTree;
    public Object           value;

    public TreeNode(TreeNode l, TreeNode r, Object v)
    {
        leftTree = l;
        rightTree = r;
        value = v;
    }

    public TreeNode(Object l, Object r, Object v)
    {
        leftTree = new TreeNode(l);
        rightTree = new TreeNode(r);
        value = v;
    }

    public TreeNode()
    {
        leftTree = rightTree = null;
        value = null;
    }

    public TreeNode(Object v)
    {
        leftTree = rightTree = null;
        value = v;
    }

    public void printTreePostOrder()
    {
        printTreePostOrder(this);
        System.out.println();
    }

    private void printTreePostOrder(TreeNode tree)
    {
        if (tree == null)
            return;
        printTreePostOrder(tree.leftTree);
        printTreePostOrder(tree.rightTree);
        System.out.print(tree.value.toString() + " ");
    }

}