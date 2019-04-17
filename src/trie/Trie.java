package trie;

import java.util.HashMap;

public class Trie {

    private class Node {
        public boolean isWord;

        public HashMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }
    //返回单词数
    public int getSize() {
        return size;
    }

    //添加一个新的单词
    public void add(String word) {

        //todo 递归写法
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean contains(String word) {
        //todo 递归写法
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        if (cur.isWord) {
            return true;
        }
        return false;
    }

    //查看是否有以prefix为前缀的单词
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null) {
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else {
            //遍历需要TreeMap HashMap超时
            for (char nextChar : node.next.keySet()) {
                if (match((node.next.get(nextChar)), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }

//        if (node.next.get(word.charAt(index)) != null && word.charAt(index) != '.') {
//            return match(node.next.get(word.charAt(index)), word, index+1);
//        }
//        if (word.charAt(index) == '.') {
//            for (char i : node.next.keySet()) {
//                if(match(node.next.get(i), word, index+1)) {
//                    return true;
//                }
//            }
//        }
//        return false;
    }
}
