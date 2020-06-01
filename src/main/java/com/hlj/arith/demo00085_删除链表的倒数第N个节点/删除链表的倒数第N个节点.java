package com.hlj.arith.demo00085_删除链表的倒数第N个节点;

import com.hlj.arith.z_common.linkNode.ListNodeResources;
import org.junit.Test;

/**
 * 作者：HealerJean
 * 题目：删除链表的倒数第N个节点
 * 解题思路：
 */
public class 删除链表的倒数第N个节点 {

    @Test
    public void test() {
        System.out.println(removeNthFromEnd(listNode(), 2));
    }

    /**
     * 两次遍历
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        int count = 0;
        ListNode node = head;
        while (head != null) {
            count++;
            head = head.next;
        }
        int diff = count - n;
        if (diff == 0) {
            return node.next;
        }

        ListNode listNode = node;
        while (diff != 0) {
            diff--;
            if (diff == 0) {
                node.next = node.next.next;
            }
            node = node.next;
        }
        return listNode;
    }


    public ListNode listNode() {
        // ListNode listNode_5 = new ListNode(5, null);
        // ListNode listNode_4 = new ListNode(4, listNode_5);
        // ListNode listNode_3 = new ListNode(3, listNode_4);
        ListNode listNode_2 = new ListNode(2, null);
        ListNode listNode_1 = new ListNode(1, listNode_2);
        return listNode_1;
    }

    public String listNodeStr(ListNode listNode, String str) {
        if (listNode == null) {
            return str.substring(0, str.lastIndexOf(","));
        }
        str = str + listNode.val + ",";
        return listNodeStr(listNode.next, str);
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
