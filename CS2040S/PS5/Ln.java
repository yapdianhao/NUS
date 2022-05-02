class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {val = x;}

        public String toString() {
             String s = "[" + val;
             while (next != null) {
                s += ", " + next.val;
                this = this.next;
             }
             return s;
        }
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(1);
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                ret = new ListNode(l2.val);
                l2 = l2.next;
            } else if (l2 == null) {
                ret = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                if (l1.val > l2.val) {
                    ret = new ListNode(l2.val);
                    l2 = l2.next;
                } else {
                    ret = new ListNode(l1.val);
                    l1 = l1.next;
                }
            }
            ret = ret.next;
        }
        return ret;
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        System.out.println(l1);
    }
}
