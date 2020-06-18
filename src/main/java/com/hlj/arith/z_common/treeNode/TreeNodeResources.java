package com.hlj.arith.z_common.treeNode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author HealerJean
 * @ClassName TreeNodeResources
 * @date 2020/5/9  10:26.
 * @Description
 */
public class TreeNodeResources {

    @Test
    public void test(){
        preOrder(initTreeNode());
        inOrder(initTreeNode());
        upToDownAndRightToLeft(initTreeNode());
    }

    public static void preOrder(TreeNode root){
        StringBuilder stringBuilder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        while (root!= null || !stack.isEmpty()){
            if (root != null){
                stringBuilder.append(root.val);
                stringBuilder.append(",");
                stack.push(root);
                root = root.left;
            }else {
                TreeNode node = stack.pop();
                root  = node.right;
            }
        }
        String str = stringBuilder.toString();
        System.out.println(str.substring(0, str.lastIndexOf(",")));
    }


    public static void postOrder(TreeNode root){
        StringBuilder stringBuilder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        while (root!= null || !stack.isEmpty()){
            if (root != null){
                stringBuilder.append(root.val);
                stringBuilder.append(",");
                stack.push(root);
                root = root.left;
            }else {
                TreeNode node = stack.pop();
                root  = node.right;
            }
        }
        String str = stringBuilder.toString();
        System.out.println(str.substring(0, str.lastIndexOf(",")));
    }


    public static void inOrder(TreeNode root){
        StringBuilder stringBuilder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        while (root!= null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                TreeNode node = stack.pop();
                stringBuilder.append(node.val);
                stringBuilder.append(",");
                root  = node.right;
            }
        }
        String str = stringBuilder.toString();
        System.out.println(str.substring(0, str.lastIndexOf(",")));
    }

    public static void upToDownAndRightToLeft(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder stringBuilder = new StringBuilder();

        while (!queue.isEmpty()){
            int size  = queue.size();
            while (size > 0){
                size--;
                TreeNode treeNode = queue.remove();
                stringBuilder.append(treeNode.val);
                stringBuilder.append(",");

                if (treeNode.right !=null){
                    queue.add(treeNode.right);
                }
                if (treeNode.left !=null){
                    queue.add(treeNode.left);
                }
            }
        }
        String str = stringBuilder.toString();
        System.out.println(str.substring(0, str.lastIndexOf(",")));
    }


    public TreeNode initTreeNode(){
        TreeNode treeNode1 = new TreeNode(3, null ,null);
        TreeNode treeNode2 = new TreeNode(6, null , null);
        TreeNode treeNode3 = new TreeNode(4, treeNode1, treeNode2);
        TreeNode treeNode4 = new TreeNode(1, null, null);
        TreeNode root = new TreeNode(5, treeNode3, treeNode4);
        return root ;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;

        }
    }

}
