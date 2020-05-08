package com.hlj.arith.demo00066_不同的二叉搜索树;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
作者：HealerJean
题目：不同的二叉搜索树2
 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 示例:
     输入: 3
     输出:
     [
       [1,null,3,2],
       [3,2,null,1],
       [3,1,null,null,2],
       [2,1,3],
       [1,null,2,null,3]
     ]
     解释:
     以上的输出对应以下 5 种不同结构的二叉搜索树：

     1         3     3      2      1
     \       /     /      / \      \
     3     2     1      1   3      2
     /     /       \                 \
     2     1         2                 3
 解题思路：
 */
public class 不同的二叉搜索树_2 {


    @Test
    public void test(){

        System.out.println(generateTrees(3));
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }

        //从1开始到n
        return getAns(1, n);

    }

    private List<TreeNode> getAns(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        //此时没有数字，将 null 加入结果中
        if (start > end) {
            ans.add(null);
            return ans;
        }
        //只有一个数字，当前数字作为一棵树加入结果中
        if (start == end) {
            TreeNode tree = new TreeNode(start);
            ans.add(tree);
            return ans;
        }
        //尝试每个数字作为根节点
        for (int i = start; i <= end; i++) {
            //得到所有可能的左子树
            List<TreeNode> leftTrees = getAns(start, i - 1);
            //得到所有可能的右子树
            List<TreeNode> rightTrees = getAns(i + 1, end);
            //左子树右子树两两组合
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    //加入到最终结果中
                    ans.add(root);
                }
            }
        }
        return ans;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
