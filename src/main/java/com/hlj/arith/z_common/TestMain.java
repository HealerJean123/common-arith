package com.hlj.arith.z_common;

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
    public void test(){

    }


    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty()  || root != null){
            if (root != null){
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }else {
                TreeNode node = stack.pop();
                root = node.right ;
            }
        }
        return res ;
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
