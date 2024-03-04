package leetcode;

/*
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int num1=0;
        int num2=0;
        ListNode temp = l1;
        while(temp!=null) {
            num1 = num1*10 + temp.val;
            temp = temp.next;
        }
        // num1 = num1*10 + temp.val;
        int revNum1 = 0;
        while(num1!=0) {
            revNum1 = revNum1*10 + num1%10;
            num1/=10;
        }
        temp = l2;
        while(temp!=null) {
            num2 = num2*10 + temp.val;
            temp = temp.next;
        }
        
        int revNum2 = 0;
        while(num2!=0) {
            revNum2 = revNum2*10 + num2%10;
            num2/=10;
        }
        // System.out.println(revNum1+" "+revNum2);
        if(revNum1 == 0 && revNum2 == 0) {
            return new ListNode(0);
        }
        int sums = revNum1 + revNum2;
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(sums!=0) {
            ListNode newNode = new ListNode(sums%10);
            sums/=10;
            tail.next = newNode;
            tail = tail.next;
        }
        ListNode result = dummy.next;
        dummy.next = null;
        return result;  
        // return new ListNode();
    }
}
