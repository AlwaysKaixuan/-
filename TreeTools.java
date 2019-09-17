package dataStructure.BinaryTree;

import java.util.*;

public class TreeTools {
    /**
     * getNodeNum: 树中节点个数
     * @param root 根节点
     */
    public static <T> int getNodeNum(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        return getNodeNum(root.leftChild) + getNodeNum(root.rightChild) + 1;
    }

    /**
     * getTreeDepth: 树的深度
     * @param root 根节点
     */
    public static <T> int getTreeDepth(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getTreeDepth(root.leftChild) + 1;
        int rightDepth = getTreeDepth(root.rightChild) + 1;
        return Math.max(leftDepth, rightDepth);
    }

    /**
     * preOrderTravel: 前序遍历
     * @param root 根节点
     */
    public static <T> void preOrderTravelRecursive(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        visitNode(root);
        preOrderTravelRecursive(root.leftChild);
        preOrderTravelRecursive(root.rightChild);
    }

    /**
     * preOrderTravel: 前序遍历(非递归)
     * @param root 根节点
     */
    public static <T> void preOrderTravel(TreeNode<T> root) {
        if (root == null) {
            return;
        }

        ArrayDeque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> temp = root;

        while (temp != null || !stack.isEmpty()){
            while (temp != null){
                visitNode(temp);   //访问该节点
                stack.push(temp);   //该节点入栈
                temp = temp.leftChild;   //指向左孩子
            }
            temp = stack.pop();   //while结束，不存在左孩子或左子树访问结束，取出栈顶元素
            temp = temp.rightChild;   //指向右孩子，按相同的规则访问右子树
        }
    }

    /**
     * midOrderTravel: 中序遍历
     * @param root 根节点
     */
    public static <T> void midOrderTravelRecursive(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        midOrderTravelRecursive(root.leftChild);
        visitNode(root);
        midOrderTravelRecursive(root.rightChild);
    }

    /**
     * midOrderTravel: 中序遍历（非递归）
     * @param root 根节点
     */
    public static <T> void midOrderTravel(TreeNode<T> root) {
        if (root == null) {
            return;
        }

        ArrayDeque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> temp = root;   //首先指向根节点

        //每访问一棵树，先访问其靠左的孩子，然后访问节点本身，再访问其右子树
        while (temp != null || !stack.isEmpty()){
            while (temp != null){   //节点不空，节点入栈，并指向其左孩子，直至向左找到其左孩子为空的节点
                stack.push(temp);
                temp = temp.leftChild;
            }

            temp = stack.pop();   //栈顶元素出栈（该元素左孩子为空，或其左子树已经访问）
            visitNode(temp);   //访问栈顶元素，即某棵子树的根节点
            temp = temp.rightChild;   //指向右孩子，按相同的规则访问右子树
        }

    }

    /**
     * backOrderTravel: 后序遍历
     * @param root 根节点
     */
    public static <T> void backOrderTravelRecursive(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        backOrderTravelRecursive(root.leftChild);
        backOrderTravelRecursive(root.rightChild);
        visitNode(root);
    }

    /**
     * backOrderTravel: 后序遍历（非递归）
     * @param root 根节点
     */
/*    public static <T> void backOrderTravel(TreeNode<T> root) {
        if (root == null) {
            return;
        }



    }*/

    /**
     * visitNode: 访问node节点
     * @param node
     */
    private static <T> void visitNode(TreeNode<T> node) {
        System.out.print(node.value + "\t");
    }

    /**
     * levelTravel: 分层遍历
     * @param root 根节点
     */
    public static <T> void levelTravel(TreeNode<T> root) {
        Queue<TreeNode<T>> q = new LinkedList<TreeNode<T>>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode<T> temp = q.poll();
            visitNode(temp);
            if (temp.leftChild != null) {
                q.offer(temp.leftChild);
            }
            if (temp.rightChild != null) {
                q.offer(temp.rightChild);
            }
        }
    }

    /**
     * getNumForKLevel: 第k层节点个数
     * @param root 根节点
     * @param k 第k层
     */
    public static <T> int getNumForKLevel(TreeNode<T> root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int leftNum = getNumForKLevel(root.leftChild, k - 1);
        int rightNum = getNumForKLevel(root.rightChild, k - 1);
        return leftNum + rightNum;
    }

    /**
     * getLeafNum: 二叉树中叶子节点的个数
     * @param root 根节点
     */
    public static <T> int getLeafNum(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        if (root.leftChild == null && root.rightChild == null) {
            return 1;
        }
        int leftNum = getLeafNum(root.leftChild);
        int rightNum = getLeafNum(root.rightChild);
        return leftNum + rightNum;
    }

    /**
     * exchange: 交换左右子树
     * @param root 根节点
     */
    public static <T> TreeNode<T> exchange(TreeNode<T> root) {
        if (root == null) {
            return null;
        }
        TreeNode<T> left = exchange(root.leftChild);
        TreeNode<T> right = exchange(root.rightChild);
        root.leftChild = right;
        root.rightChild = left;
        return root;
    }

    /**
     * nodeIsChild: 查看node是否是root的节点
     * @param root 根节点
     * @param node 目标节点
     */
    public static <T> boolean nodeIsChild(TreeNode<T> root, TreeNode<T> node) {
        if (root == null || node == null) {
            return false;
        }
        if (root.equals(node)) {
            return true;
        }
        boolean isFind = nodeIsChild(root.leftChild, node);
        if (!isFind) {
            isFind = nodeIsChild(root.rightChild, node);
        }
        return isFind;
    }

    /**
     * getTreeFromPreAndMid: 根据前序和中序构建二叉树
     * @param pre 前序序列
     * @param mid 中序序列
     */
    public static <T> TreeNode<T> getTreeFromPreAndMid(List<T> pre, List<T> mid) {
        if (pre == null || mid == null || pre.size() == 0 || mid.size() == 0) {
            return null;
        }
        if (pre.size() == 1) {
            return new TreeNode<T>(pre.get(0));
        }
        TreeNode<T> root = new TreeNode<T>(pre.get(0));
        // 找出根节点在中序中的位置
        int index = 0;
        while (!mid.get(index++).equals(pre.get(0))) {
        }
        // 构建左子树的前序
        List<T> preLeft = new ArrayList<T>(index);
        // 左子树的中序
        List<T> midLeft = new ArrayList<T>(index);
        for (int i = 1; i < index; i++) {
            preLeft.add(pre.get(i));
        }
        for (int i = 0; i < index - 1; i++) {
            midLeft.add(mid.get(i));
        }

        // 重建左子树
        root.leftChild = getTreeFromPreAndMid(preLeft, midLeft);
        // 右子树的前序
        List<T> preRight = new ArrayList<T>(pre.size() - index - 1);
        // 右子树的中序
        List<T> midRight = new ArrayList<T>(pre.size() - index - 1);
        for (int i = 0; i <= pre.size() - index - 1; i++) {
            preRight.add(pre.get(index + i));
        }
        for (int i = 0; i <= pre.size() - index - 1; i++) {
            midRight.add(mid.get(index + i));
        }
        // 重建→子树
        root.rightChild = getTreeFromPreAndMid(preRight, midRight);
        return root;
    }

    /**
     * equals: 查看node1和node2两棵树是否相等(两棵树所有节点都相等)
     * @param node1
     * @param node2 两个节点
     */
    public static <T> boolean equals(TreeNode<T> node1, TreeNode<T> node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        }
        boolean isEqual = node1.value.equals(node2.value);
        boolean isLeftEqual = equals(node1.leftChild, node2.leftChild);
        boolean isRightEqual = equals(node1.rightChild, node2.rightChild);
        return isEqual && isLeftEqual && isRightEqual;
    }

    /**
     * pathLength: 节点node到根节点root的路径长度，也是节点node所在的层数 - 1
     *
     * @param root 根节点
     * @param node 目标节点
     * @author wangkaixuan
     * */
    public static int pathLength(TreeNode root, TreeNode node){
        if (!nodeIsChild(root, node))
            return 0;
        if (root.equals(node))
            return 0;
        return 1 + pathLength(root.leftChild, node) + pathLength(root.rightChild, node);
    }

    /**
     * findAllFatherNode: 返回两个节点lnode和rnode的以root为根节点的公共父节点
     *
     * @param root 根节点
     * @param lNode
     * @param rNode
     * @return TreeNode 返回类型
     */
    public static <T> TreeNode<T> findAllFatherNode(TreeNode<T> root,
                                                    TreeNode<T> lNode, TreeNode<T> rNode) {
        if (lNode == root || rNode == root) {
            return root;
        }
        if (root == null || lNode == null || rNode == null) {
            return null;
        }
        // 如果lNode是左子树的节点
        if (nodeIsChild(root.leftChild, lNode)) {
            if (nodeIsChild(root.rightChild, rNode)) {
                return root;
            } else {
                return findAllFatherNode(root.leftChild, lNode, rNode);
            }
        } else {
            if (nodeIsChild(root.leftChild, rNode)) {
                return root;
            } else {
                return findAllFatherNode(root.rightChild, lNode, rNode);
            }
        }
    }

}
