package com.hlj;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author HealerJean
 * @ClassName MainTest
 * @date 2020-12-07  19:34.
 * @Description
 */
public class MainTest {


    @Test
    public void test(){
        TreeNode root = initNode();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        System.out.println(root.value);
        boolean flag  =  true;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size >0){
                size--;

                if(flag){
                    TreeNode node = queue.removeFirst();
                    if (node.right != null){
                        queue.add((node.right));
                        System.out.println(node.right.value);
                    }
                    if (node.left != null){
                        queue.add((node.left));
                        System.out.println(node.left.value);

                    }
                }else {
                    TreeNode node = queue.removeLast();
                    if (node.left != null){
                        queue.add((node.left));
                        System.out.println(node.left.value);

                    }
                    if (node.right != null){
                        queue.add((node.right));
                        System.out.println(node.right.value);
                    }
                }

            }
            flag = !flag;



        }
    }


    public TreeNode initNode(){
        TreeNode root7 = new TreeNode(7,null, null );
        TreeNode root6 = new TreeNode(6,null, null );
        TreeNode root5 = new TreeNode(5,null, null );
        TreeNode root4 = new TreeNode(4,null, null );
        TreeNode root3 = new TreeNode(3,root6, root7 );
        TreeNode root2 = new TreeNode(2,root4, root5 );
        TreeNode root = new TreeNode(1,root2, root3 );
        return root;
    }
}


class TreeNode{
      int value;
     TreeNode left;
     TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right){
        this.value = val;
        this.left = left;
        this.right = right;
    }
}
