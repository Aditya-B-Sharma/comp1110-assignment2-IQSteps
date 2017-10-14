package comp1110.ass2;

import java.util.List;

/**
 * Created by Stefan on 14/10/2017.
 */
public class treeNode<String> {
        public String data;
        public  treeNode<String> parent;
        public List<treeNode<String>> children;

        public treeNode(treeNode<String> parent) {
            this.parent = parent;
        }

        public void setValue(String value) {
            data = value;
        }

        public List<treeNode<String>> getChildren() {
            return children;
        }

        public treeNode<String> getParent() {
            return parent;
        }

        public treeNode<String> addChild(treeNode<String> parent, String value) {
            treeNode node = new treeNode(parent);
            node.setValue(value);
            parent.getChildren().add(node);
            return node;
        }
    }

