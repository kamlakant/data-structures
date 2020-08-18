package tree;

/**
 * Full - All nodes have 0 or 2 children. Min nodes = 2h + 1, max nodes = (2^h+1) - 1
 * 
 * Perfect - All internal nodes have 2 children and all leafs are at same level. For height h, nodes = 2^h - 1
 * 
 * Complete - All levels filled barring last one. In last level, all keys left aligned. Height O(log n)
 * 
 * Balanced - If height = O(log n) for n node tree
 * 
 * FIND INSERT DELETE O(log n)
 * 
 * Worst case - Degenerate Tree O(n)
 */

public class BinarySearchTree {

    private class Node {
        private int key;
        private String value;
        private Node left;
        private Node right;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Until left child is null, keep traversing left children
         * 
         * @return Node with min key
         */
        public Node min() {
            if (left == null) {
                return this;
            }
            return left.min();
        }
    }

    private Node root;

    public String find(int key) {
        Node node = findNode(root, key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    public void mirror() {
        root = mirror(root);
    }

    private Node mirror(Node node) {
        if (node == null) {
            return node;
        }

        Node left = mirror(node.left);
        Node right = mirror(node.right);

        node.left = right;
        node.right = left;

        return node;
    }

    /**
     * Start at root and check if node is null or matches the key then return it.
     * 
     * Else if key is smaller than node, traverse left.
     * 
     * Else if key is larger than node, traverse right.
     */
    private Node findNode(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        } else if (key < node.key) {
            return findNode(node.left, key);
        } else if (key > node.key) {
            return findNode(node.right, key);
        }
        return null;
    }

    public void insert(int key, String value) {
        root = insertNode(root, key, value);
    }

    private Node insertNode(Node node, int key, String value) {
        Node newNode = new Node(key, value);

        // If node is null, put the new node here
        if (node == null) {
            node = newNode;
            return node;
        } else if (key < node.key) {
            // Populate the left children of the node
            node.left = insertNode(node.left, key, value);
        } else if (key > node.key) {
            // Populate the right children of the node
            node.right = insertNode(node.right, key, value);
        }

        // Return full constructed tree
        return node;
    }

    public void delete(int key) {
        root = deleteNode(root, key);

    }

    private Node deleteNode(Node node, int key) {
        if (node == null) {
            return node;
        } else if (key < node.key) {
            // Traverse the left children of the node
            node.left = deleteNode(node.left, key);
        } else if (key > node.key) {
            // Traverse the right children of the node
            node.right = deleteNode(node.right, key);
        }

        // Reached the node which has to be deleted
        else {
            // Case 1. Node has no child
            // Just set it null
            if (node.left == null && node.right == null) {
                node = null;
            }

            // Case 2(a). Node has one child - LEFT
            // Replace the node with its child
            else if (node.right == null) {
                node = node.left;
            }

            // Case 2(b). Node has one child - RIGHT
            // Replace the node with its child
            else if (node.left == null) {
                node = node.right;
            }

            // Case 3. Node has two children
            // Replace the node with the min node of its right child
            else {
                // Find the min node of right child OR max node of left child
                Node minRight = findMin(node.right);
                // Node maxLeft = findMax(node.left);

                // Replace the node with the found min/max node
                node.key = minRight.key;
                node.value = minRight.value;
                // node.key = maxLeft.key;
                // node.value = maxLeft.value;

                // Delete the found min/max node
                node.right = deleteNode(node.right, node.key);
                // node.left = deleteNode(node.left, node.key);
            }
        }
        return node;
    }

    public void printInOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    /**
     * LEFT ROOT RIGHT - Root middle
     */
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.key + " ");
            inOrderTraversal(node.right);
        }
    }

    public void printPreOrderTraversal() {
        preOrderTraversal(root);
        System.out.println();
    }

    /**
     * ROOT LEFT RIGHT - Root 1st
     */
    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void printPostOrderTraversal() {
        postOrderTraversal(root);
        System.out.println();
    }

    /**
     * LEFT RIGHT ROOT - Root last
     */
    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.key + " ");
        }
    }

    /**
     * Get the height of tree. For each level, print the level order traversal
     */
    public void printLevelOrderTraversal() {
        int h = height(root);
        for (int i = 0; i < h; i++) {
            levelOrderTraversal(root, i);
        }
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private void levelOrderTraversal(Node node, int level) {
        if (node == null) {
            return;
        }
        if (level == 0) {
            System.out.print(node.key + " ");
        } else {
            levelOrderTraversal(node.left, level - 1);
            levelOrderTraversal(node.right, level - 1);
        }
    }

    public Node findMin(Node node) {
        return node.min();
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5, "a");
        bst.insert(3, "b");
        bst.insert(4, "c");
        bst.insert(2, "d");
        bst.insert(0, "e");
        bst.insert(-1, "f");
        bst.insert(1, "g");
        bst.insert(7, "h");
        bst.insert(6, "i");
        bst.insert(8, "j");
        bst.insert(9, "k");
        System.out.println(bst.find(3));
        bst.delete(7);
        bst.printInOrderTraversal();
        bst.printPreOrderTraversal();
        bst.printPostOrderTraversal();

        bst.printLevelOrderTraversal();

        bst.mirror();

        bst.printLevelOrderTraversal();
    }

}
