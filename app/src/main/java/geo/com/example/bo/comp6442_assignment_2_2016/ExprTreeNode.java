package geo.com.example.bo.comp6442_assignment_2_2016;

/**
 * Created by Bo on 2/05/2016.
 */
public class ExprTreeNode {
    ExprTreeNode left,right;    //the left and right pointer of tree
    boolean isLeaf;             //is this a leaf?
    double value;                  //If is a leaf, store the number here
    char op;                    //if not, store the operater.
}
