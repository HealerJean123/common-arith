package com.hlj.arith.sort.二叉树;

/**
 * 作者 ：HealerJean
 * 日期 ：2019/3/13  下午10:35.
 * 类描述：
 */
public class BinaryTree_DiGui {

    /*
     * 二叉树先序中序后序排序
     * 方式：递归。
     */

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

    //打印节点数值
    public static void printNode(Node node){
        System.out.print(node.getData());
    }


    //先序遍历
    public static void preOrder(Node root){

        printNode(root);//打印根节点

        if(root.getLeftNode() != null){//使用递归遍历左孩子
            preOrder(root.getLeftNode());
        }
        if(root.getRightNode() != null){//使用递归遍历右孩子
            preOrder(root.getRightNode());
        }
    }


    //中序遍历
    public static void inOrder(Node root){

        if(root.getLeftNode() != null){//使用递归遍历左孩子
            inOrder(root.getLeftNode());
        }

        printNode(root);//打印根节点

        if(root.getRightNode() != null){//使用递归遍历右孩子
            inOrder(root.getRightNode());
        }
    }


    //后续遍历
    public static void postOrder(Node root){

        if(root.getLeftNode() != null){//使用递归遍历左孩子
            postOrder(root.getLeftNode());
        }

        if(root.getRightNode() != null){//使用递归遍历右孩子
            postOrder(root.getRightNode());
        }

        printNode(root);//打印根节点
    }


    public static void main(String[] args){
//		BinaryTree tree = new BinaryTree();//注释掉本行后类中方法需变为static
        Node root = init();

        System.out.println("先序遍历");
        preOrder(root);
        System.out.println("");

        System.out.println("中序遍历");
        inOrder(root);
        System.out.println("");

        System.out.println("后序遍历");
        postOrder(root);
        System.out.println("");

    }

}
