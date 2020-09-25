package com.hlj.arith.z_common;

import com.hlj.arith.z_common.treeNode.TreeNodeResources;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author HealerJean
 * @ClassName TestMain
 * @date 2020/4/22  9:33.
 * @Description
 */
@Slf4j
public class TestMain {

    @Test
    public void test() {
        System.out.println(generateParenthesis(3));

    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
         dsf(n, n , "",  res);
         return res;
    }

    public void dsf(int left , int right, String str,List<String> res){
        if (left ==0 && right == 0){
            res.add(str);
        }
        if (left > right){
            return ;
        }

        if (left > 0){
            dsf(left-1,right , str+"(",  res);
        }
        if(right > 0){
            dsf(left, right-1, str+")", res);
        }

    }

    public TreeNode initTreeNode() {
        TreeNode treeNode1 = new TreeNode(3, null, null);
        TreeNode treeNode2 = new TreeNode(6, null, null);
        TreeNode treeNode3 = new TreeNode(4, treeNode1, treeNode2);
        TreeNode treeNode4 = new TreeNode(1, null, null);
        TreeNode root = new TreeNode(5, treeNode3, treeNode4);
        return root;
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
