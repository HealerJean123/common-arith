package com.hlj.arith.demo00012_二叉树遍历;

import lombok.Data;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class D4_层序遍历 {


    @Test
    public void test() {
        Node root = init();
        System.out.println("层序遍历 从上到下，从右到左");
        rightToLeft(root);
        System.out.println();
        System.out.println("层序遍历 从上到下，从左到右");
        leftToRigit(root);
    }

    /**
     * 层序遍历（上到下，从从右到左）：
     * 队列的解决方案，将每一行的数据放到队列中，依次打印出来
     */
    public static void rightToLeft(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //表示每行有多少个
            int hangSize = queue.size();
            //遍历每行的数据
            while (hangSize > 0) {
                //从队列中取出，打印根节点
                Node node  = queue.remove();
                printNode(node);
                hangSize--;

                if (node.getRightNode() != null) {
                    queue.add(node.getRightNode());
                }
                if (node.getLeftNode() != null) {
                    queue.add(node.getLeftNode());
                }
            }
        }
    }


    /**
     * 层序遍历（上到下，从从左到右）：
     * 队列的解决方案，将每一行的数据放到队列中，依次打印出来
     */
    public void leftToRigit(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int i = queue.size();
            while (i > 0) {
                Node node = queue.remove();
                printNode(node);
                i--;
                if (node.getLeftNode() != null) {
                    queue.add(node.getLeftNode());
                }
                if (node.getRightNode() != null) {
                    queue.add(node.getRightNode());
                }
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
