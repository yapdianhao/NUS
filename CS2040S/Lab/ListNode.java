class Main {

    static class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(3);
        l.next = new ListNode(1);
        l.next.next = new ListNode(2);
        ListNode r = reverse(l);
        System.out.println(r.val);
        System.out.println(r.next.val);
        System.out.println(r.next.next.val);
    }

    public static ListNode reverse(ListNode l) {
        if (l == null || l.next == null) {
            return l;
        }
        ListNode to_do = l.next;
        ListNode reversed = l;
        reversed.next = null;
        while (to_do != null) {
            ListNode temp = to_do;
            to_do = to_do.next;
            temp.next = reversed;
            reversed = temp;
        }
        return reversed;
    }
}