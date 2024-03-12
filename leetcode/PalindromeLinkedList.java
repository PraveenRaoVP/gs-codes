package leetcode;

public class PalindromeLinkedList {
    

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
    public boolean isPalindrome(ListNode head) {
        ListNode temp = head;
        String s = "";
        while(temp!=null) {
            s+=Integer.toString(temp.val);
            temp=temp.next;
        }
        if(new StringBuilder(s).reverse().toString().equals(s)) {
            return true;
        }        
        return false;
    }
}
