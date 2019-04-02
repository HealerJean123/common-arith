package com.hlj.arith.domo00013二叉树;

/**
 * 作者 ：HealerJean
 * 日期 ：2019/3/13  下午10:34.
 * 类描述：
 */
public class Node {

    private int data;
    private Node leftNode;
    private Node rightNode;

    public Node(int data, Node leftNode, Node rightNode){
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getData(){
        return data;
    }

    public void setData(int data){
        this.data = data;
    }

    public Node getLeftNode(){
        return leftNode;
    }

    public void setLeftNode(Node leftNode){
        this.leftNode = leftNode;
    }

    public Node getRightNode(){
        return rightNode;
    }

    public void setRightNode(Node rightNode){
        this.rightNode = rightNode;
    }

}
