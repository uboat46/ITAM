/**
 * Class Node
 * with left and right Node
 * attributes
 */
class Node{
    /**
     * 
     * @param {int} value - The Node´s value 
     */
    constructor(value = null) {
        this.value = value;
    }
    
    /**
    * Prints out Node value
    */
    print() {
        console.log(this.value);
    }

    /**
     * Print out a Node,
     * first itself then left 
     * Node then right Node
     * recursively
     */
    printLeftRight() {
        this.print();
        if(this.left){
            this.left.printLeftRight();
        }
        if(this.right){
            this.right.printLeftRight();
        }
    }

    /**
     * Sets rightNode to Node
     * @param {Node} node - A Node Object 
     */
    setRight(node) {
        this.right = node;
    }

    /**
     * Sets leftNode to Node
     * @param {Node} node - A Node Object 
     */
    setLeft(node) {
        this.left = node;
    }
}

/**
 * Class BinaryTree
 * just a Head Node
 * attribute
 */
class BinaryTree {
    /**
     * 
     * @param {Node} node - The Tree´s Head Node
     */
    constructor(node = null) {
        this.head = node;
    }

    /**
     * Prints out Node value
     */
    print() {
        this.head.printLeftRight();
    }
}

/**
 * Takes an Array Object and converts it into
 * a new BinaryTree
 * @param {Array} arr - Ordered Array of int´s to be converted into Binary Tree 
 * @param {int} left - left index of the array from which to make Binary Tree
 * @param {int} right - right index of the array from which to make Binary Tree
 * @returns {Node} - The Head Node of the Binary Tree obtained from arr
 */
function BinaryTreeFromArray(arr = null, left = 0, right = 0) {
    if(right >= left){
        var mid = Math.floor((right + left) / 2);
        var node = new Node(arr[mid]);
        var rightNode = BinaryTreeFromArray(arr, mid + 1, right);
        var leftNode = BinaryTreeFromArray(arr, left, mid - 1);
        node.setRight(rightNode);
        node.setLeft(leftNode);
        return node;
    }
    return null;
}


var arr = [1,2,3,4,5,6,7];

var Btree = new BinaryTree(BinaryTreeFromArray(arr,0, arr.length - 1));

Btree.print();

