package maxHeap;

import java.util.ArrayList;
import java.util.Collections;

//可用于优先队列PriorityQueue的底层实现
public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        data = new ArrayList<>();
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    //返回index父亲节点下标
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    //返回左孩子节点下标
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //返回右孩子节点下标
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private void siftUp(int index) {

        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            Collections.swap(data, index, parent(index));
//            E temp = data.get(index);
//            data.set(index, data.get(parent(index)));
//            data.set(parent(index), temp);
            index = parent(index);
        }

        //我的递归实现  没毛病的
//        if (index == 0 || data.get(index).compareTo(data.get(parent(index))) <= 0) {
//            return;
//        }
//        Collections.swap(data, index, parent(index));
//        siftUp(parent(index));
    }

    public void add(E e) {
        data.add(e);
        siftUp(data.size()-1);
    }

    //看堆中最大元素
    public E findMax() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    //取出堆中最大元素
    public E extractMax() {

        E ret = findMax();

        Collections.swap(data, 0, data.size() - 1);
        data.remove(data.size()-1);
        siftDown(0);
        return ret;
    }

    private void siftDown(int index) {

        while (leftChild(index) < data.size()) {

            int maxIndex = leftChild(index);
            if (maxIndex + 1 < data.size() && data.get(maxIndex + 1).compareTo(data.get(maxIndex)) > 0) {
                maxIndex = rightChild(index);
            }
            if (data.get(index).compareTo(data.get(maxIndex)) >= 0) {
                break;
            }
            Collections.swap(data, index, maxIndex);
            index = maxIndex;
        }


        //我的递归实现  仅仅思路 未判断左右孩子是否为空
//        if (rightChild(index) >= data.size()) {
//            return;
//        }
//        if (data.get(index).compareTo(data.get(leftChild(index))) >= 0 && data.get(index).compareTo(data.get(rightChild(index))) >= 0) {
//            return;
//        }
//        if (data.get(leftChild(index)).compareTo(data.get(rightChild(index))) < 0) {
//            Collections.swap(data, index, rightChild(index));
//            siftDown(rightChild(index));
//        } else {
//            Collections.swap(data, index, leftChild(index));
//            siftDown(leftChild(index));
//        }
    }

    //取出堆中最大元素，再添加一个元素（思想：将堆顶元素取出，将新元素放置堆顶，执行siftDown）
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    //Heapify
    public MaxHeap(E[] arr) {
        data = new ArrayList<E>();
        Collections.addAll(data, arr);
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }
}
