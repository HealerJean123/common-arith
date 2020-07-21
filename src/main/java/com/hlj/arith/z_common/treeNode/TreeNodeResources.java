package com.hlj.arith.z_common.treeNode;

import com.hlj.arith.demo00107_将有序数组转换为二叉搜索树.将有序数组转换为二叉搜索树;
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














    public  void collect(TreeNode root, LinkedList<Integer> linkedList) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //表示每行有多少个
            int hangSize = queue.size();
            //遍历每行的数据
            while (hangSize > 0) {
                //从队列中取出，打印根节点
                TreeNode node  = queue.remove();
                hangSize--;
                if (node == null){
                    linkedList.add(null);
                }else {
                    linkedList.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
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
