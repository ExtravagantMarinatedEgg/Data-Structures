package segmentTree;

public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0 , 0, data.length - 1);
    }

    private void buildSegmentTree(int index, int l, int r) {
        if (l == r) {
            tree[index] = data[l];
            return;
        }

        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);
        //当数字过大会有整形溢出
//        int mid = (l + r) / 2;
        int mid = l + (r - l) / 2;


        buildSegmentTree(leftIndex, l, mid);
        buildSegmentTree(rightIndex, mid+1, r);

        tree[index] = merger.merge(tree[leftIndex], tree[rightIndex]);

    }

    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int index, int l, int r, int queryL, int queryR) {
        if (queryL == l && queryR == r) {
            return tree[index];
        }
        int mid = l + (r - l) / 2;
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);
        if (queryL > mid) {
            return query(rightIndex, mid + 1, r, queryL, queryR);
        } else if (queryR < mid + 1) {
            return query(leftIndex, l, mid, queryL, queryR);
        }
//        if (queryL <= mid && queryR >= mid + 1)
        E leftResult = query(leftIndex, l, mid, queryL, mid);
        E rightResult = query(rightIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        int rightIndex = rightChild(treeIndex);
        int liftIndex = leftChild(treeIndex);

        int mid = l + (r - l) / 2;
        if (index > mid) {
            set(rightIndex, mid + 1, r, index, e);
        } else {
            set(liftIndex, l, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[liftIndex], tree[rightIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    //返回左孩子节点下标
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //返回右孩子节点下标
    private int rightChild(int index) {
        return index * 2 + 2;
    }
}
