package tree;

/**
 * Re-balance after each insert/delete. Great for read-heavy, choose RED-BLACK for write-heavy.
 * 
 * 4 types of imbalance (Z imbalanced node, Y child of Z in direction of insertion, X child of Y in direction of insertion) -
 * 
 * LEFT LEFT (rotate Z right)
 * 
 * RIGHT RIGHT (rotate Z left)
 * 
 * LEFT RIGHT (rotate Y left then rotate Z right)
 * 
 * RIGHT LEFT (rotate Y right then rotate Z left)
 * 
 * Difference between heights of left and right subtrees cannot be more than 1 for all nodes
 */
public class AVLTree {

    class Node {
        int key;
        int height;

        Node left;
        Node right;

        Node(int data) {
            this.key = data;
            this.height = 1;
        }

        public Node max() {
            if (right == null) {
                return this;
            }
            return right.max();
        }

        @Override
        public String toString() {
            return this.key + "[" + this.height + "]";
        }
    }

    private Node root;

    public Node findMax(Node node) {
        return node.max();
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {

        /* 1. Perform the normal BST insertion */
        if (node == null)
            return (new Node(key));

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            // Duplicate keys not allowed
            return node;
        }

        /* 2. Update height of this node */
        node.height = 1 + Math.max(height(node.left), height(node.right));

        /*
         * 3. Get the balance factor of this ancestor node to check whether this node became unbalanced
         */
        int balance = getBalanceFactor(node);

        /* If balance factor is < 1 or > 1, then rebalancing is required */

        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node node, int key) {
        if (node == null) {
            return node;
        }

        if (key < node.key) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.key) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                Node maxLeft = findMax(node.left);

                node.key = maxLeft.key;
                node.left = deleteNode(node.left, node.key);
            }
        }
        if (node == null) {
            return node;
        }

        /* 2. Update height of this node */
        node.height = 1 + Math.max(height(node.left), height(node.right));

        /*
         * 3. Get the balance factor of this ancestor node to check whether this node became unbalanced
         */
        int balance = getBalanceFactor(node);

        /* If balance factor is < 1 or > 1, then rebalancing is required */

        // Left Left Case
        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalanceFactor(node.right) < 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && getBalanceFactor(node.left) <= 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    public void printInOrderTraversal() {
        inOrderTravsersal(root);
        System.out.println();
    }

    private void inOrderTravsersal(Node node) {
        if (node != null) {
            inOrderTravsersal(node.left);
            System.out.print(node.key + " ");
            inOrderTravsersal(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(40);
        tree.insert(20);
        tree.insert(10);
        tree.insert(25);
        tree.insert(30);
        tree.insert(22);
        tree.insert(50);
        tree.insert(5);

        tree.printInOrderTraversal();

        tree.delete(30);
        tree.delete(40);

        tree.printInOrderTraversal();

    }

}
