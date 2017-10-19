function Node(value){
    this.value = value;
    this.left = null;
    this.right = null;
}

Node.prototype.print = function(){
    console.log(this.value);
}

Node.prototype.printLeftRight = function(){
    this.print();
    if(this.left){
        this.left.print();
        this.left.printLeftRight();
    }
    if(this.right){
        this.right.print();
        this.right.printLeftRight();
    }
}

Object.defineProperty(Node.prototype, "setRight", {
     set: function (node) { this.right = node } 
});

Object.defineProperty(Node.prototype, "setLeft", {
    set: function (node) { this.left = node } 
});

function BinaryTree(node){
    this.head = node;
}

BinaryTree.prototype.print = function(){
    this.head.printLeftRight();

}

function BinaryTreeFromArray(arr, left, right) {
    var mid = Math.floor((right - left) / 2);
    if(mid > 0){
        var node = new Node(arr[mid]);
        var rightNode = BinaryTreeFromArray(arr.slice(mid + 1), mid + 1, right);
        var leftNode = BinaryTreeFromArray(arr.slice(0 , mid), left, mid - 1);
        node.right = rightNode;
        node.leftNode = leftNode;
        return node;
    }
    return null;
}


var arr = [1,2,3,4,5,6,7];

var Btree = new BinaryTree(BinaryTreeFromArray(arr,0, arr.length - 1));

Btree.print();

