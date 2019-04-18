import maxHeap.MaxHeap;
import segmentTree.Merger;
import segmentTree.SegmentTree;
import trie.Trie;
import unionFind.*;

import java.util.*;
import java.util.LinkedList;
import java.util.Stack;

public class Main {




    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ret = 0;
        for (int i = 0; i < nums.length; i+=2) {
            ret += nums[i];
        }
        return ret;
    }

//    private int[] sum;
//
//    private int[] data;
//
//    public NumArray(int[] nums) {
//
//        data = new int[nums.length];
//        for (int i = 0; i < data.length; i ++) {
//            data[i] = nums[i];
//        }
//
//        sum = new int[nums.length + 1];
//        sum[0] = 0;
//        for (int i = 1; i < sum.length; i++) {
//            sum[i] = sum[i - 1] + nums[i - 1];
//        }
//    }
//
//    public void update(int index, int val) {
//        data[index] = val;
//        for (int i = index + 1; i < data.length; i++) {
//            sum[i] = sum[i - 1] + data[i - 1];
//        }
//
//    }
//
//    public int sumRange(int i, int j) {
//        return sum[j + 1] - sum[i];
//    }




//    private int[] sum;
//
//    public NumArray(int[] nums) {
//        sum = new int[nums.length + 1];
//        sum[0] = 0;
//        for (int i = 1; i < sum.length; i++) {
//            sum[i] = sum[i - 1] + nums[i - 1];
//        }
//    }
//
//    public int sumRange(int i, int j) {
//        return sum[j + 1] - sum[i];
//    }
//
    private SegmentTree<Integer> segmentTree;

//    public NumArray(int[] nums) {
//        if (nums.length > 0) {
//            Integer[] data = new Integer[nums.length];
//            for (int i = 0; i < nums.length; i++) {
//                data[i] = nums[i];
//            }
//            segmentTree = new SegmentTree<Integer>(data, (a, b) -> a + b);
//        }
//    }
    public void update(int i, int val) {
        segmentTree.set(i, val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("error");
        }
        return segmentTree.query(i, j);
    }


    public String simplifyPath(String path) {
        //双端队列
//        ArrayDeque<Integer> aDeque=new ArrayDeque<Integer>();
        Queue<String> queue = new LinkedList<>();
        String[] s = path.split("/");
        for (String i : s) {
            if (i.equals(""))
                continue;
            if (i.equals(".")) {

            } else if (i.equals("..")) {
                if (!queue.isEmpty()) {
                    ((LinkedList<String>) queue).removeLast();
                }
            } else {
                ((LinkedList<String>) queue).addLast("/"+i);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (queue.isEmpty())
            queue.add("/");
        while (!queue.isEmpty()) {
            stringBuilder.append(((LinkedList<String>) queue).removeFirst());
        }
        return stringBuilder.toString();
    }


    public String frequencySort(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        PriorityQueue<Character> queue = new PriorityQueue(
                (a, b) -> -(hashMap.get(a) - hashMap.get(b))
        );
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            } else {
                hashMap.put(s.charAt(i), 1);
            }
            if (!queue.contains(s.charAt(i))) {
                queue.add(s.charAt(i));
            } else {
                queue.remove(s.charAt(i));
                queue.add(s.charAt(i));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.remove();
            for (int i = 0; i < hashMap.get(c); i++) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    // todo 比较器

    public int[][] kClosest(int[][] points, int K) {
        int[][] ret = new int[K][2];
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(
                (int[] a, int[] b) -> -(int)(Math.pow(a[0], 2)+Math.pow(a[1], 2)-Math.pow(b[0], 2)-Math.pow(b[1], 2))
        );
        for (int i = 0; i < points.length; i++) {
            int a = (int)(Math.pow(points[i][0], 2) + Math.pow(points[i][1], 2));
            if (queue.size() < K) {
                queue.add(points[i]);
            } else if ((int)(Math.pow(points[i][0], 2)+Math.pow(points[i][1], 2)) < (int)(Math.pow(queue.peek()[0], 2)+Math.pow(queue.peek()[1], 2))) {
                queue.remove();
                queue.add(points[i]);
            }
        }
        for (int i = 0; i < K; i++) {
            ret[i] = queue.remove();
        }
        return ret;
    }


//    private PriorityQueue<Integer> queue;
//    private int k;
//
//    public KthLargest(int k, int[] nums) {
//        this.k = k;
//        queue = new PriorityQueue();
//        for (int num : nums) {
//            add(num);
//        }
//    }
//
//    public int add(int val) {
//        if (queue.size() < k) {
//            queue.add(val);
//        } else {
//            if (val > queue.peek()) {
//                queue.remove();
//                queue.add(val);
//            }
//        }
//        return queue.peek();
//    }

    //将队元素 设置为实现可比较的
    private class Freq implements Comparable<Freq> {
        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq o) {
            if (this.freq < o.freq) {
                return -1;
            } else if (this.freq > o.freq) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    //为PriorityQueue设置 比较器  改变大根堆小根堆  定义比较器的之后 可以不实现可比较的
    //写成匿名类
    private class FreqComparator implements Comparator<Freq> {

        @Override
        public int compare(Freq o1, Freq o2) {
            return o1.freq - o2.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        //传入比较器对象 看完成堆类型的定义
//        PriorityQueue<Freq> queue = new PriorityQueue<>(new FreqComparator());


        /*PriorityQueue<Freq> queue = new PriorityQueue<>(new Comparator<Freq>() {
            @Override
            public int compare(Freq o1, Freq o2) {
                return o1.freq - o2.freq;
            }
        });*/

        /*PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });*/

        //使用lambda表达式
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (o1, o2) -> map.get(o1)-map.get(o2)
        );
        for (int key : map.keySet()) {
            Freq freq = new Freq(key, map.get(key));
            if (queue.size() < k) {
                queue.add(key);
            } else if (map.get(key) > map.get(queue.peek())) {
//                queue.remove()
                queue.poll();
                queue.add(key);
            }
        }
        List<Integer> list = new ArrayList<>(k);
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }
        return list;
    }

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

        return (endTime - startTime) / 100000000.0;
    }

    private static double testUF(UF uf, int m) {



        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
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

//        int n = 10000000;
//        Random random = new Random();
//        Integer[] testData = new Integer[n];
//        for (int i = 0; i < n; i++) {
//            testData[i] = random.nextInt(Integer.MAX_VALUE);
//        }
//        double time1 = testHeap(testData, true);
//        double time2 = testHeap(testData, false);
//        System.out.println("time1 true : "+time1);
//        System.out.println("time2 false : "+time2);
//        Main a = new Main();
//        System.out.println(a.frequencySort("aaacc"));
//        System.out.println(a.frequencySort("tree"));
//        System.out.println(a.frequencySort("aaasffrrrr"));
//        a.simplifyPath("/a/./b/../../c/");
//        Integer[] nums = {5, 6, 9, 8, 7, 3, 10, 99};
//        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums,
//                (a, b) -> Math.max(a, b)
//        );
//        System.out.println(segmentTree.query(3, 7));

//        Trie trie = new Trie();
//        trie.add("at");
//        trie.add("and");
//        trie.add("an");
//        trie.add("add");
//        System.out.println(trie.search("a"));
//        System.out.println(trie.search(".at"));
//        trie.add("bat");
//        System.out.println(trie.search(".at"));
//        System.out.println(trie.search("an."));
//        System.out.println( trie.search("a.d."));
//        System.out.println(trie.search("b."));
//        System.out.println(trie.search("a.d"));
//        System.out.println(trie.search("."));

//        int[] a = new int[9];
//        int b = 0;
//        a[++b] = ++b;
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(i + ": " + a[i]);
//        }
        int size = 10000000;
        int m = 10000000;
//        UnionFion1 uf1 = new UnionFion1(size);
//        System.out.println("1"+testUF(uf1, m));
//        UnionFion2 uf2 = new UnionFion2(size);
//        System.out.println("1"+testUF(uf2, m));
//        UnionFion3 uf3 = new UnionFion3(size);
//        System.out.println("1:   "+testUF(uf3, m));
        UnionFion4 uf4 = new UnionFion4(size);
        System.out.println("4:   "+testUF(uf4, m));
        UnionFion5 uf5 = new UnionFion5(size);
        System.out.println("5:   "+testUF(uf5, m));
        UnionFion6 uf6 = new UnionFion6(size);
        System.out.println("5:   "+testUF(uf6, m));
    }


}