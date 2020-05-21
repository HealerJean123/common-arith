package com.hlj.arith.demo00069_路径总和;

import org.junit.Test;

/**
作者：HealerJean
题目：x的平方根
解题思路：
*/
public class 路径总和1 {

    @Test
    public void test(){
        TreeNode treeNode = initTreeNode();
        System.out.println(hasPathSum(treeNode, 22));
    }


    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.val == sum && root.left ==null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }



    public TreeNode initTreeNode(){
        TreeNode treeNode1 = new TreeNode(7, null ,null);
        TreeNode treeNode2 = new TreeNode(2, null , null);
        TreeNode treeNode3 = new TreeNode(11, treeNode1, treeNode2);
        TreeNode treeNode4 = new TreeNode(1, null, null);
        TreeNode treeNode5 = new TreeNode(4, null, treeNode4);
        TreeNode treeNode6 = new TreeNode(13, null, null);
        TreeNode treeNode7 = new TreeNode(8, treeNode6, treeNode5);
        TreeNode treeNode8 = new TreeNode(4, treeNode3, null);
        TreeNode root = new TreeNode(5, treeNode8, treeNode7);
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

