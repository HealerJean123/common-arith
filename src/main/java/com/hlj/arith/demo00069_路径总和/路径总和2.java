package com.hlj.arith.demo00069_路径总和;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
作者：HealerJean
题目：x的平方根
解题思路：
*/
public class 路径总和2 {

    @Test
    public void test(){
        TreeNode treeNode = initTreeNode();

        System.out.println(pathSum(treeNode, 22));
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {

        List<List<Integer>> list = new ArrayList<>();

        hasPathSum(list, new LinkedList<>(), root, target);

        return list;
    }

    private void hasPathSum(List<List<Integer>> list, LinkedList linkedList, TreeNode root,  int target) {
        if (root == null){
            return;
        }
        linkedList.add(root.val);
        //后面加上判断节点的结束，防止提前结束
        if (target ==  root.val && root.left == null && root.right == null){
            list.add(new ArrayList<>(linkedList));
            return;
        }

        if (root.left != null) {
            hasPathSum(list, linkedList, root.left, target-root.val);
            linkedList.removeLast();
        }

        if (root.right != null) {
            hasPathSum(list, linkedList, root.right, target-root.val);
            linkedList.removeLast();
        }

    }


    public TreeNode initTreeNode(){
        TreeNode treeNode0 = new TreeNode(5, null , null);
        TreeNode treeNode1 = new TreeNode(7, null ,null);
        TreeNode treeNode2 = new TreeNode(2, null , null);
        TreeNode treeNode3 = new TreeNode(11, treeNode1, treeNode2);
        TreeNode treeNode4 = new TreeNode(1, null, null);
        TreeNode treeNode5 = new TreeNode(4, treeNode0, treeNode4);
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

