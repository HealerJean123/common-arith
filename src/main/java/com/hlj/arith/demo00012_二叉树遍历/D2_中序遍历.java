package com.hlj.arith.demo00012_二叉树遍历;

import lombok.Data;
import org.junit.Test;

import java.util.Stack;

public class D2_中序遍历 {

    @Test
    public void test() {
        System.out.println("中序遍历");
        Node node = init();
        inOrder(node);
        System.out.println();
        inStack(node);
    }

    /**
     *
     * 中序遍历（左根右） ：递归
     */
    public void inOrder(Node root) {
        //使用递归遍历左孩子
        if (root.getLeftNode() != null) {
            inOrder(root.getLeftNode());
        }

        //打印根节点
        printNode(root);

        //使用递归遍历右孩子
        if (root.getRightNode() != null) {
            inOrder(root.getRightNode());
        }
    }

    /**
     * 中序遍历（左根右） ：非递归
     * 栈的特性：后进先出，先进后出
     * 1、因为初次打印就是左节点，所以我们需要讲右面的节点全部入栈，知道左子树为空
     * 2、这个时候栈里面有数据了，出栈，直接打印根节点，然后获取右子树。
     * ​		如果右子树不为空，讲当前节点设置为  则执行1，继续获取所有的节点，并入栈
     * ​		如果右子树为空， 则继续执行2，打印节点即可
     */
    public void inStack(Node node) {
        Stack<Node> stack = new Stack<Node>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                //后进先出，讲所有的左子树和跟节点依次放入
                stack.push(node);
                node = node.getLeftNode();
            }
            //消化队列中的数据
            if (!stack.isEmpty()) {
                node = stack.pop();
                printNode(node);
                node = node.getRightNode();
            }
        }
    }

    /**
     * 打印节点数值
     */
    public static void printNode(Node node) {
        System.out.print(node.getData()+ " ");
    }

    /**
     * 初始化二叉树：
     * 必须逆序简历，先建立子节点，再逆序往上建立，因为非叶子节点会使用到下面的节点，而初始化是按顺序初始化得，不逆序建立会报错
     */
    public static Node init() {
        Node H = new Node("H", null, null);
        Node K = new Node("K", null, null);
        Node G = new Node("G", H, K);
        Node F = new Node("F", G, null);
        Node E = new Node("E", null, F);
        Node D = new Node("D", null, null);
        Node C = new Node("C", D, null);
        Node B = new Node("B", null, C);
        Node A = new Node("A", B, E);
        return A;
    }

    @Data
    public static class Node {
        private String data;
        private Node leftNode;
        private Node rightNode;

        public Node(String data, Node leftNode, Node rightNode) {
            this.data = data;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

    }

}
