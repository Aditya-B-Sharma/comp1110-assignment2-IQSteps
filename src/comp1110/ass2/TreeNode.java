package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 14/10/2017.
 */
public class TreeNode {
        public String data;
        public TreeNode parent;
        public List<TreeNode> children;

        public TreeNode(String data) {
            this.data = data;
            parent = null;
            children = new ArrayList<TreeNode>();
        }

        public void setData (String data) {
            this.data = data;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }


        public void addChild(TreeNode child) {
            children.add(child);
            child.setParent(this);
        }

        public String toString() {
            return data;
        }
    }

