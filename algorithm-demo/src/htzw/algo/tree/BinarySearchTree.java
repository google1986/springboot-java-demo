package htzw.algo.tree;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * 二叉查找树
 * BinarySearchTree
 */
public class BinarySearchTree {
    private Node tree;

    /**
     * 在二叉查找树中查找相应的节点
     *
     * @param data 数值
     * @return
     */
    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) {
                p = p.left;
            } else if (data > p.data) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    /**
     * 向二叉查找树中插入节点
     * @param data 节点数值
     */
    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else { // data < p.data
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    /**
     * 根据数值删除相应的节点信息
     *
     * @param data 当前数值
     */
    public void delete(int data) {
        // p指向要删除的节点，初始化指向根节点
        Node p = tree;
        // pp记录的是p的父节点
        Node pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        if (p == null) {
            return; // 没有找到
        }

        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) { // 查找右子树中最小节点
            Node minP = p.right;
            Node minPP = p; // minPP表示minP的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data; // 将minP的数据替换到p中
            p = minP; // 下面就变成了删除minP了
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        Node child; // p的子节点
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        if (pp == null) {
            tree = child; // 删除的是根节点
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }

    /**
     * 在二叉查找树中查找最小的节点
     * @return
     */
    public Node findMin() {
        if (tree == null) {
            return null;
        }
        Node p = tree;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    /**
     * 在二叉查找树中查找最大的节点
     * @return
     */
    public Node findMax() {
        if (tree == null) {
            return null;
        }
        Node p = tree;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }


    /**
     * 二分查找的递归实现
     *
     * @param a
     * @param n
     * @param val
     * @return
     */
    public int bSearchDemo(int[] a, int n, int val) {
        return recursion(a, 0, n - 1, val);
    }

    private int recursion(int[] a, int low, int high, int value){
        if (low > high) {
            return -1;
        }
        int mid  = low + ((high-low)>>1);
        if (a[mid] == value) {
            return mid;
        }
        return a[mid] > value ? recursion(a, low,mid-1, value) : recursion(a,mid+1, high, value);
    }

    private int bSearchInternally(int[] a, int low, int high, int value) {
        if (low > high) {
            return -1;
        }

        int mid = low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bSearchInternally(a, mid + 1, high, value);
        } else {
            return bSearchInternally(a, low, mid - 1, value);
        }
    }

    /**
     * 二分查找算法
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bSearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (high-low)>>1;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
