package dataStructure.BinaryTree;

/**
 * 二叉树节点
 * */
public class TreeNode<T> {
    T value;
    TreeNode<T> leftChild;
    TreeNode<T> rightChild;

    public TreeNode() {
    }

    public TreeNode(T value) {
        this.value = value;
    }

    /**
     * 增加左子节点
     */
    public void addLeft(T value){
        TreeNode<T> leftChild = new TreeNode<T>(value);
        this.leftChild = leftChild;
    }

    /**
     * 增加右子节点
     */
    public void addRight(T value){
        TreeNode<T> rightChild = new TreeNode<T>(value);
        this.rightChild = rightChild;
    }

    /**
     * 重载equals方法
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TreeNode){
            return this.value.equals(((TreeNode<?>)obj).value);
        }
        return false;
    }

    /**
     * 重载hashCode方法
     */
    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    /**
     * 重载toString方法
     */
    @Override
    public String toString(){
        return this.value == null? "" : this.value.toString();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }
}
