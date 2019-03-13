package com.hlj.arith.sort.二叉树;

import org.junit.Test;

/**
 * 作者 ：HealerJean
 * 日期 ：2019/3/13  下午10:38.
 * 类描述：
 */
public class TestEcs {


    //注意必须逆序简历，先建立子节点，再逆序往上建立，
    //因为非叶子节点会使用到下面的节点，而初始化是按顺序初始化得，不逆序建立会报错
    public static Node init(){
        Node J = new Node(8, null, null);
        Node H = new Node(4, null, null);
        Node G = new Node(2, null, null);
        Node F = new Node(7, null, J);
        Node E = new Node(5, H, null);
        Node D = new Node(1, null, G);
        Node C = new Node(9, F, null);
        Node B = new Node(3, D, E);
        Node A = new Node(6, B, C);
        return A;  //返回根节点
    }


    private void printNode(Node node){
        System.out.printf(node.getData()+"_");
    }

    @Test
    public void testPreNode(){

        preNode(init());
        System.out.println();
        inNode(init());
    }

    public void preNode(Node node){

        printNode(node); //打印根节点

        if(node.getLeftNode()!=null){
            preNode(node.getLeftNode());
        }
        if(node.getRightNode()!=null){
            preNode(node.getRightNode());
        }
    }

    public void inNode(Node node){

        if(node.getLeftNode()!=null){
            inNode(node.getLeftNode());
        }

        printNode(node); //打印根节点


        if(node.getRightNode()!=null){
            inNode(node.getRightNode());
        }
    }


}
