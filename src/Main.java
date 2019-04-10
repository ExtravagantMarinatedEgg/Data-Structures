import maxHeap.MaxHeap;
import tree.BST;

import javax.xml.soap.Node;
import java.util.*;
import java.util.Stack;

public class Main {

    public String toLowerCase(String str) {
        return str.toLowerCase();
    }

    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < J.length(); i++) {
            set.add(J.charAt(i));
        }
        int ret = 0;
        for (int i = 0; i < S.length(); i++) {
            if (set.contains(S.charAt(i))) {
                ret++;
            }
        }
        return ret;
    }


    public int[] intersection(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums1) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                arrayList.add(num);
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }
        int[] res = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            res[i] = arrayList.get(i);
        }
        return res;
    }

















    //我瞎蒙的
    //在t中找 比s大的最近点的温度
    public ArrayList<Double> fun(double[] t, double[] c, double[] s) {
        ArrayList<Double> arrayList = new ArrayList<>();
        TreeMap<Double, Double> treeMap = new TreeMap();
        for (int i = 0; i < t.length; i++) {
            treeMap.put(t[i], c[i]);
        }
        for (int i = 0; i < s.length; i++) {
            arrayList.add(treeMap.ceilingKey(s[i]));
        }
        return arrayList;
    }


    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> set = new HashSet<>();
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }
            set.add(res.toString());
        }
        return set.size();
    }





    /*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }
    private int maxDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        int max = 0;
        for (int i = 0; i < root.children.size(); i++) {
            int res = maxDepth(root.children.get(i), depth+1);
            if (max < res) {
                max = res;
            }
        }
        return max;
    }





    public int longestUnivaluePath(TreeNode root) {
        return longestUnivaluePath(root, 1);
    }

    private int longestUnivaluePath(TreeNode node, int size) {
        if (node == null) {
            return size;
        }
        if (node.left.val == node.val) {
            size++;
        }
        if (node.right.val == node.val) {
            size++;
        }
        longestUnivaluePath(node.left, size);
        longestUnivaluePath(node.right, size);
        return size;
    }






    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode ret;
        if (l1.val <= l2.val) {
            ret = l1;
            ret.next = mergeTwoLists(l1.next, l2);
        } else {
            ret = l2;
            ret.next = mergeTwoLists(l1, l2.next);
        }
        return ret;

    }



    public ListNode middleNode(ListNode head) {
        ListNode low, fast;
        low = fast = head;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }




    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode listNode = reverseList(head.next);

        head.next.next = head;

        head.next = null;
        return listNode;

    }


    public void deleteNode(ListNode node) {
        while (node.next.next != null) {
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }



    public static ListNode removeElements(ListNode head, int val) {


        if (head == null) {
            return head;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;

    }

    public static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;
        } else {
            return arr[l] + sum(arr, l+1);
        }
    }

    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>();
            for (int i = 0; i < testData.length; i++) {
                maxHeap.add(testData[i]);
            }
        }
        long endTime = System.nanoTime();

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < testData.length; i++) {
            if (arr[i-1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("end");

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
	// write your code here

//        Stack<Integer> stack = new Stack<>();
//        stack.push(null);
//        System.out.println(stack.isEmpty());
//        System.out.println(stack.pop());


//        BST<Integer> bst = new BST<>();
//        int[] nums = {6,4,9,2,1,3,8,7,10};
//        for (int i = 0; i < nums.length; i++) {
//            bst.add(nums[i]);
//        }
//
//        bst.leveOrder();
//        System.out.println("--------------------------");
//        bst.inOrder();
//        bst.remove(9);
//        System.out.println("--------------------------");
//        bst.leveOrder();
//        System.out.println("--------------------------");
//        bst.inOrder();
//        System.out.println(bst);
//        bst.preOrder();
//        System.out.println("--------------------------");
//        bst.preOrderNR();
//        bst.postOrder();
//        System.out.println("--------------------------");
//        bst.inOrder();


//        int n = 1000000;
//        MaxHeap<Integer> maxHeap = new MaxHeap<>();
//        Random random = new Random();
//        for (int i = 0; i < n; i++) {
//            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
//        }
//
//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = maxHeap.extractMax();
//        }
//        for (int i = 1; i < n; i++) {
//            if (arr[i-1] < arr[i]) {
//                throw new IllegalArgumentException("Error");
//            }
//        }
//        System.out.println("end");

        int n = 10000000;
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = testHeap(testData, true);
        double time2 = testHeap(testData, false);
        System.out.println("time1 true : "+time1);
        System.out.println("time2 false : "+time2);
    }
}