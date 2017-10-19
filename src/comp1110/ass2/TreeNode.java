package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

/* Authorship: Class written by Stefan Louie*/

// Reference: https://stackoverflow.com/questions/16229732/cant-wrap-my-head-around-populating-an-n-ary-tree
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

