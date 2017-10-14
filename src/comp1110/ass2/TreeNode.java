package comp1110.ass2;

import java.util.List;

/**
 * Created by Stefan on 14/10/2017.
 */
public class TreeNode<String> {
        public String data;
        public TreeNode<String> parent;
        public List<TreeNode<String>> children;

        public TreeNode(TreeNode<String> parent) {
            this.parent = parent;
        }

        public void setValue(String value) {
            data = value;
        }

        public List<TreeNode<String>> getChildren() {
            return children;
        }

        public TreeNode<String> getParent() {
            return parent;
        }

        public TreeNode<String> addChild(TreeNode<String> parent, String value) {
            TreeNode node = new TreeNode(parent);
            node.setValue(value);
            parent.getChildren().add(node);
            return node;
        }
    }

