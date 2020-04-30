package com.hlj.arith.demo00061_删除排序链表中的重复元素;

import org.junit.Test;

/**
作者：HealerJean
题目：删除排序链表中的重复元素1
 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     示例 1:
         输入: 1->2->3->3->4->4->5
         输出: 1->2->5
     示例 2:
         输入: 1->1->1->2->3
         输出: 2->3
解题思路：就是删除中间的指针，没有别的意思
*/
public class 删除排序链表中的重复元素_2 {


    @Test
    public void test(){
        printListNode(deleteDuplicates(initListNode()));
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }
        //如果只有1个节点，则直接返回就行了。肯定不会重复的
        if (head.next == null){
            return head ;
        }

        //初始化比较的值
        int val = head.val ;
        int count = 1 ;
        //新节点头
        ListNode node = null ;
        //新节点的尾部节点（因为每次都是在尾部放入新的数据）
        ListNode lastNode = null ;
        //因为要比较当前节点和下一个节点的值，所以我们要遍历的条件就是下一个节点有值
        while (head.next != null){
            //每次都会比较当前和下一个的，如果相同则删除接续比较。
             if (val == head.next.val){
                 head.next = head.next.next;
                 count ++ ;
             }else {
                 //下次while要使用，提前取出来，防止下面业务判断发生改变
                 val = head.next.val;
                 ListNode next = head.next;

                 //count > 1 表示当前节点肯定是重复项的节点，肯定不会放到里面取的,
                 // 经历过商品的if判断，和后一个节点不一样，但是后一个节点不能保证是不重复的,所以再次设置count=1进行遍历
                 if (count > 1){
                     count = 1 ;
                 }else {
                     // 此时 count = 1，表示肯定只有一个元素了 首次进入肯定是唯一的头节点，第二个开始就不能保证了
                     if (node == null){
                         node = head ;
                         node.next = null;
                         lastNode = node ;
                     }else {
                         lastNode.next = head;
                         lastNode.next.next = null;
                         lastNode = lastNode.next;
                     }
                     //count此时等于1，表示要留在node节点上。因为我们还要继续向后遍历，所以先获取next
                 }
                 head =  next ;
             }
        }

        //上面判断额是 head.next 会存在尾节点有值不走的情况
        if (count == 1){
            // [1,1,2]，走完之后，直接进入的2 这个时候肯定是不会再走了，head就是当前的值
            if (node == null){
                return head;
            }else {
                // [1,2,3,3,4,4,5]，
                lastNode.next = head;
            }
        }

        return node;
    }


    void  printListNode(ListNode listNode){
        while (listNode != null){
            System.out.printf( listNode.val + ",");
            listNode = listNode.next;
        }
        System.out.println();
    }


    public ListNode initListNode(){
        // ListNode listNode_5 = new ListNode(4, null);
        ListNode listNode_4 = new ListNode(4, null);
        ListNode listNode_3 = new ListNode(3, listNode_4);
        ListNode listNode_2 = new ListNode(2, listNode_3);
        ListNode listNode_1 = new ListNode(1, listNode_2);
        return listNode_1;
    }

    class ListNode{
        int val;
        ListNode next ;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
