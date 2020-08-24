package htzw.algo.linkedlist;

/**
 * 链表操作
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/8 15:24
 */
public class LinkedListAlgo {

    /**
     * 反转
     * @param list
     * @return
     */
    public static Node reverse(Node list){
        Node curr = list, pre = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 检测环
     * @param list
     * @return
     */
    public static boolean checkCircle(Node list){
        if (list == null) {
            return false;
        }
        Node fast = list.next;
        Node slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 有序链表合并
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
//        利用哨兵结点简化实现难度
        ListNode soldier = new ListNode(0);
        ListNode p = soldier;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val){
                p.next = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 !=null){
            p.next = l1;
        }
        if (l2 != null){
            p.next = l2;
        }
        return soldier.next;
    }

    // 删除倒数第K个结点
    public static Node deleteLastKth(Node list, int k) {
        Node fast = list;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            ++i;
        }

        if (fast == null) {
            return list;
        }

        Node slow = list;
        Node prev = null;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null) {
            list = list.next;
        } else {
            prev.next = prev.next.next;
        }
        return list;
    }

    // 求中间结点
    public static Node findMiddleNode(Node list) {
        if (list == null) {
            return null;
        }

        Node fast = list;
        Node slow = list;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode(int x){
            val = x;
        }
    }
    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
