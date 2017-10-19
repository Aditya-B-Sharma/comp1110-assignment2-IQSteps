package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;

// Authorship: Class written by Stefan Louie

// Reference: https://stackoverflow.com/questions/16229732/cant-wrap-my-head-around-populating-an-n-ary-tree
public class TreeNode {

        // Each instance of a TreeNode has data as its value, a TreeNode parent and possibly children of TreeNodes
        public String data;
        public TreeNode parent;
        public List<TreeNode> children;


        // Constructor for TreeNode
        public TreeNode(String data) {
            this.data = data;
            parent = null;
            children = new ArrayList<TreeNode>();
        }

        // Sets the argument as the node's parent
        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        // Add a child node to current node
        public void addChild(TreeNode child) {
            children.add(child);
            child.setParent(this);
        }

        // String representation of the node
        public String toString() {
            return data;
        }
    }

