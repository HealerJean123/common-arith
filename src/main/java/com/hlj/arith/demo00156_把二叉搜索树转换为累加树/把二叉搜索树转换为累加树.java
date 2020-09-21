package com.hlj.arith.demo00156_把二叉搜索树转换为累加树;

import com.hlj.arith.z_common.treeNode.TreeNodeResources;

/**
作者：HealerJean
题目：
 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     例如：
         输入: 原始二叉搜索树:
             5
             /   \
             2     13
         输出: 转换为累加树:
             18
             /   \
             20     13
解题思路：
*/
public class 把二叉搜索树转换为累加树 {

    public TreeNode convertBST(TreeNode root) {
        return root;
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
