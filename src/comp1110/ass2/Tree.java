package comp1110.ass2;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 14/10/2017.
 */
public class Tree<String> {
    public treeNode<String> root;

    public Tree(String rootData) {
        root = new treeNode<String>(null);
        root.setValue(rootData);
        root.children = new ArrayList<treeNode<String>>();
    }


}
