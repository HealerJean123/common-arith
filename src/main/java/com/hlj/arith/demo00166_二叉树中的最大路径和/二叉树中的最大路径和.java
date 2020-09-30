package com.hlj.arith.demo00166_二叉树中的最大路径和;

import com.hlj.arith.z_common.treeNode.TreeNodeResources;
import lombok.val;
import org.junit.Test;

/**
 * @author HealerJean
 * @date 2020/9/29  15:25.
 * @description
 */
public class 二叉树中的最大路径和 {

    @Test
    public void test(){
        System.out.println( maxPathSum(initTreeNode()));
    }

    int max = Integer.MIN_VALUE ;
    public int maxPathSum(TreeNode node){
        dfs(node);
        return max;
    }


    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 只有在最大贡献值大于 0 时，才会选取对应子节点,如果比就不会选择了了，下面的计算亿0为主
        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        // 更新答案
        max = Math.max(max, node.val + left + right);

        // 返回节点的最大贡献值
        return  Math.max(left, right) + node.val ;
    }


    public TreeNode initTreeNode(){
        // TreeNode treeNode7 = new TreeNode(7, null ,null);
        // TreeNode treeNode15= new TreeNode(15, null , null);
        // TreeNode treeNode20 = new TreeNode(20, treeNode15, treeNode7);
        // TreeNode treeNode9 = new TreeNode(9, null, null);
        // TreeNode root = new TreeNode(-10, treeNode9, treeNode20);


        // TreeNode treeNode20 = new TreeNode(3, null, null);
        // TreeNode treeNode9 = new TreeNode(-2, null, null);
        // TreeNode root = new TreeNode(1, treeNode9, treeNode20);

        TreeNode treeNode7 = new TreeNode(-1, null ,null);
        TreeNode treeNode6 = new TreeNode(-2, null ,null);
        TreeNode treeNode5 = new TreeNode(3, null ,null);
        TreeNode treeNode4 = new TreeNode(1, treeNode7 ,null);
        TreeNode treeNode3 = new TreeNode(-3, treeNode6, null);
        TreeNode treeNode2 = new TreeNode(-2, treeNode4, treeNode5);
        TreeNode root = new TreeNode(1, treeNode2, treeNode3);

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
