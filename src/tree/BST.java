package tree;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void add(E e) {

        root = add(root, e);

//        if (root == null) {
//            Node node = new Node(e);
//            root = node;
//            return;
//        } else {
//            root = add(root, e);
//        }
    }
    //递归调用 返回插入后的根节点
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }
        return node;
        //正常思路吧 void
//        if (e.equals(node.e)) {
//            return;
//        } else if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//            return;
//        } else if (e.compareTo(node.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//        if (e.compareTo(node.e) < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
        //我的实现 void
//        if (e.compareTo(node.e) < 0) {
//            if (node.left == null) {
//                node.left = new Node(e);
//                size++;
//                return;
//            } else {
//                add(node.left, e);
//            }
//        } else {
//            if (node.right == null) {
//                node.right = new Node(e);
//                size++;
//                return;
//            } else {
//                add(node.right, e);
//            }
//        }
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        }
        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(Node node) {

        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrderNR() {
        preOrderNR(root);
    }

    private void preOrderNR(Node node) {
        Stack<Node> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
        }
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public void leveOrder() {
//        Queue<Node> queue = new PriorityQueue<>();
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.e);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public E minmun() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return minmun(root).e;
    }

    //查找最小元素
    private Node minmun(Node node) {
        if (node.left == null) {
            return node;
        }
        return minmun(node.left);
    }

    public E removeMin() {
        E ret = minmun();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E ret = minmun();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            size--;
            return node.left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) == 0) {
            if (node.left == null) {
                size--;
                return node.right;
            } else if(node.right == null) {
                size--;
                return node.left;
            } else {
                Node successor = minmun(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                return successor;
            }
        } else if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
        } else {
            node.right = remove(node.right, e);
        }
        return node;
    }





    public E maxmun() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return maxmun(root).e;
    }
    //查找最大元素
    private Node maxmun(Node node) {
        if (node.right == null) {
            return node;
        }
        return maxmun(node.right);
    }



    public void postOrder() {
        postOrder(root);
    }
    private void postOrder(Node node) {

        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }


    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(Node node) {

        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);

    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0 , res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(depth).append(generateDepthString(depth)+"null\n");
            return;
        }
        res.append(depth).append(generateDepthString(depth)).append(node.e+"\n");
        generateBSTString(node.left, depth+1, res);
        generateBSTString(node.right, depth+1, res);

    }

    private String generateDepthString(int dept) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < dept; i++) {
            res.append("--");
        }
        return res.toString();
    }

}
