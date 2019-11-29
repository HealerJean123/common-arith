package com.hlj.arith.demo00013二叉树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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


    //先序遍历 根左右
    public static void preOrder(Node root){

        printNode(root);//打印根节点

        if(root.getLeftNode() != null){//使用递归遍历左孩子
            preOrder(root.getLeftNode());
        }
        if(root.getRightNode() != null){//使用递归遍历右孩子
            preOrder(root.getRightNode());
        }
    }
    @Test
    public void  preOrder(){
        System.out.println("先序遍历");
        Node node = init();
        preStack(node);
    }



    //中序遍历 左根右
    public  void inOrder(Node root){

        if(root.getLeftNode() != null){//使用递归遍历左孩子
            inOrder(root.getLeftNode());
        }
        printNode(root);//打印根节点
        if(root.getRightNode() != null){//使用递归遍历右孩子
            inOrder(root.getRightNode());
        }
    }

    @Test
    public void  inOrder(){
        System.out.println("中序遍历");
        Node node = init();
        preStack(node);
    }


    //后续遍历 左右根
    public static void postOrder(Node root){

        if(root.getLeftNode() != null){//使用递归遍历左孩子
            postOrder(root.getLeftNode());
        }
        if(root.getRightNode() != null){//使用递归遍历右孩子
            postOrder(root.getRightNode());
        }
        printNode(root);//打印根节点
    }

    @Test
    public void  postOrder(){
        System.out.println("\n后序遍历");
        Node node = init();
        preStack(node);
    }




    // 非递归 //先序遍历 跟左右
    public  static  void preStack(Node node) {
        Stack<Node> stack = new Stack<Node>();

        while (node != null || !stack.isEmpty()) {  //将所有左孩子压栈
            if (node != null) {   //压栈之前先访问
                printNode(node); //先打印跟节点（跟左右）
                stack.push(node); //先进后出 将 根节点放入 栈中（为了将来取出它的右子树）
                node = node.getLeftNode(); //取出它的左子树
            } else { //当上门的左子树结束之后，才开始打印右子树
                node = stack.pop();
                node = node.getRightNode();
            }
        }
    }

    @Test
    public void  testpreStack(){
        System.out.println("\n非递归——先序遍历");
        Node node = init();
        preStack(node);
    }




    // 非递归 中序遍历 左跟右
    public  void inStack(Node node) {
        Stack<Node> stack = new Stack<Node>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {//添加到队列中
                stack.push(node);//先进后出 （为了方便先取出左子树）
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

    @Test
    public void  inStack(){
        System.out.println("\n非递归——中序遍历");
        Node root = init();
        inStack(root);
        System.out.println("");
    }




    /**
     * 从上到下，从从右到左
     * @param root
     */
    public static void rightToLeft(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Node temp = null;
        while (!queue.isEmpty()) {
            int i = queue.size();
            while (i > 0) {
                temp = queue.remove();
                printNode(temp);//打印根节点
                i--;

                if (temp.getRightNode() != null) {
                    queue.add(temp.getRightNode());
                }
                if (temp.getLeftNode() != null) {
                    queue.add(temp.getLeftNode());
                }
            }
        }
    }

    @Test
    public void  rightToLeft(){
        System.out.println("\n层序遍历 从上到下，从右到左");
        Node root = init();
        rightToLeft(root);
        System.out.println("");
    }

    /**
     * 使用Queue 先进先出
     * @param node
     */
    public  void leftToRigit(Node node){
       Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            int i = queue.size(); //取得它的大小//取得每层的大小
             while (i>0){ //下面制作并且消耗每层的队列
                 Node temp = queue.remove(); //从队列中，先进先出，取出一个
                 printNode(temp);//打印根节点
                 i-- ; //每次取出元素 i就要减去
                 if(temp.getLeftNode()!=null){
                     queue.add(temp.getLeftNode());
                 }
                 if(temp.getRightNode()!=null){
                     queue.add(temp.getRightNode());
                 }
             }
        }

    }



    @Test
    public void  leftToRigit(){
        System.out.println("\n层序遍历 从上到下，从左到右");
        Node root = init();
        leftToRigit(root);
        System.out.println("");
    }




}
