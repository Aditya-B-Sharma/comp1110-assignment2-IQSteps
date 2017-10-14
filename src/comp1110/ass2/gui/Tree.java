package comp1110.ass2.gui;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 14/10/2017.
 */
public class Tree<T> {
    private treeNode<T> root;

    public Tree(T rootData) {
        root = new treeNode<T>(null);
        root.setValue(rootData);
        root.children = new ArrayList<treeNode<T>>();
    }


    class treeNode<T> {
        private T data;
        private treeNode<T> parent;
        private List<treeNode<T>> children;

        public treeNode(treeNode<T> parent) {
            this.parent = parent;
        }

        public void setValue(T value) {
            data = value;
        }

        public List<treeNode<T>> getChildren() {
            return children;
        }

        public treeNode<T> getParent() {
            return parent;
        }

        public treeNode<T> addChild(treeNode<T> parent, T value) {
            treeNode node = new treeNode(parent);
            node.setValue(value);
            parent.getChildren().add(node);
            return node;
        }
    }
}
