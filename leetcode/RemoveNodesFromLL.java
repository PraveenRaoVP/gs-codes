package leetcode;

class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

public class RemoveNodesFromLL {
    public static void main(String[] args) {
        
    }

    public ListNode removeNodes(ListNode head) {
        ListNode newHead = reverse(head);
        int maxValue = Integer.MIN_VALUE;

        ListNode curr = newHead;
        ListNode prev = null;
        while(curr!=null) {
            if(curr.val < maxValue) {
                prev.next = curr.next;
            } else {
                maxValue = curr.val;
                prev = curr;
            }
        }
        return reverse(newHead);
    }

    public ListNode reverse(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;
        ListNode next = null;
        while(curr!=null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
