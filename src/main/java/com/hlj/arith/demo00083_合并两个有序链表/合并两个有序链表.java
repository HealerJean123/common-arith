package com.hlj.arith.demo00083_合并两个有序链表;

import com.hlj.arith.z_common.linkNode.ListNodeResources;
import org.junit.Test;

/**
作者：HealerJean
题目：
 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 示例：
     输入：1->2->4, 1->3->4
     输出：1->1->2->3->4->4
解题思路：
*/
public class 合并两个有序链表 {

    @Test
    public void test(){
        ListNode listNode = mergeTwoLists(l1(),l2());
        System.out.println(listNode);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //将来用它的下一个节点就是所求节点，否则初始化复制容易导致变量地址的复制问题
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            //下面这行可写，可不写，建议写上，这样比较严谨，因为后面会进行替换的
            prev.next.next = null;
           //下面这个旨在删除 l1 或者l2产生的垃圾，因为下次使用的时候，还是会使用prev.next这样就删除调垃圾了
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }



    public ListNode l1(){
       ListNode  listNode_3 = new ListNode(4, null);
       ListNode  listNode_2 = new ListNode(2, listNode_3);
       ListNode  listNode_1 = new ListNode(1, listNode_2);
        return listNode_1;
    }


    public ListNode l2(){
        ListNode  listNode_3 = new ListNode(4, null);
        ListNode  listNode_2 = new ListNode(3, listNode_3);
        ListNode  listNode_1 = new ListNode(1, listNode_2);
        return listNode_1;
    }

    class ListNode{
        int val;
        ListNode  next ;


        public ListNode(int val) {
            this.val = val;
        }
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
