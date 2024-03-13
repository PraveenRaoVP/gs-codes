package leetcode;

public class RemoveNthNodeFromEndOfLinkedList {
     /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) return head;
        if(head.next==null && n==1) return null;
        ListNode rabbit = head;
        int size = 0;
        while(rabbit.next!=null) {
            size++;
            rabbit = rabbit.next;
        }
        int nSize = size-n;
        if(nSize < 0) return head.next;
        int count = 0;
        rabbit = head.next;
        ListNode ptr = head;
        while(count++ < nSize) {
            rabbit = rabbit.next;
            ptr = ptr.next;
        }
        if(rabbit!=null && rabbit.next!=null)
            ptr.next = rabbit.next;
        else {
            ptr.next = null;
        }
        return head;
    }
}