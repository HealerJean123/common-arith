package com.hlj.arith.demo00012_二叉树遍历;

import lombok.Data;
import org.junit.Test;

import java.util.Stack;

public class D1_先序遍历 {

    @Test
    public void test() {
        System.out.println("先序遍历");
        Node node = init();
        preOrder(node);
        System.out.println();
        preStack(node);
    }

    /**
     * 先序遍历（根左右）：递归
     */
    public  void preOrder(Node root) {

        //1、打印根节点
        printNode(root);

        //2、使用递归遍历左孩子
        if (root.getLeftNode() != null) {
            preOrder(root.getLeftNode());
        }

        //3、使用递归遍历右孩子
        if (root.getRightNode() != null) {
            preOrder(root.getRightNode());
        }
    }


    /**
     *  先序遍历（根左右）：非递归
     *  根据栈的特性：后进先出，先进后出：
     * 1、因为初次打印就是跟节点，所以每次节点进来，不管三七二十一先打印跟节点，然后根节点入栈，再讲节点切换为左子树节点作为根节点，循环，直到左子树为空
     * 2、这个时候栈中有数据的，也就是说所有靠右的节点的集合
     * ​			如果右子树不为空，然后获取最后进入的节点的右子树作为根节点再进行1中的遍历。
     * ​			如果右子树为空，则从栈中取出数据继续执行2
     */
    public  void preStack(Node node) {
        // 1、初始化一个栈
        Stack<Node> stack = new Stack<>();
        // 2、判断节点不为NULL，或者栈不为空
        while (node != null || !stack.isEmpty()) {

            // if (node != null) { 目的是为了打压跟节点和左子树
            if (node != null) {
                //先打印跟节点
                printNode(node);
                //根节点放入 栈中,放到栈中的目的
                stack.push(node);
                //设置循环节点为当前节点的左子树
                node = node.getLeftNode();

                //目的是为了从栈里面取出节点
            } else {
                node = stack.pop();
                //设置循环节点为当前节点的右子树
                node = node.getRightNode();
            }
        }
    }



    /**
     * 打印节点数值
     */
    public  void printNode(Node node) {
        System.out.print(node.getData()+ " ");
    }

    /**
     * 初始化二叉树：
     * 必须逆序简历，先建立子节点，再逆序往上建立，因为非叶子节点会使用到下面的节点，而初始化是按顺序初始化得，不逆序建立会报错
     */
    public  Node init() {
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
