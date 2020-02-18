package com.hlj.arith.demo00012_二叉树遍历;

import lombok.Data;
import org.junit.Test;

/**
 * @author HealerJean
 * @ClassName D3_后序遍历
 * @date 2020/2/18  15:01.
 * @Description
 */
public class D3_后序遍历 {

    @Test
    public void test() {
        System.out.println("后序遍历");
        Node node = init();
        postOrder(node);
    }

    /**
     *
     * 后续遍历(左右根) ：递归
     */
    public static void postOrder(Node root) {

        if (root.getLeftNode() != null) {
            postOrder(root.getLeftNode());
        }
        if (root.getRightNode() != null) {
            postOrder(root.getRightNode());
        }
        printNode(root);
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
