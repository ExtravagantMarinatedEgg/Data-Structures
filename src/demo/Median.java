package demo;

import java.util.PriorityQueue;

public class Median {

    private PriorityQueue<Integer> maxQueue;
    private PriorityQueue<Integer> minQueue;
    private int size;


    public Median() {
        maxQueue = new PriorityQueue<>((a, b) -> b - a);
        minQueue = new PriorityQueue<>();
    }

    /**
     * 判断总元素的奇偶
     * @param val size
     * @return
     */
    private boolean isEven(int val) {
        return val%2.0 == 0;
    }

    /**
     * 添加一个元素到根堆
     * @param val
     */
    public void add(int val) {
        if (isEven(size)) {
            //
            if (size != 0 && val > maxQueue.peek()) {
                minQueue.add(val);
                maxQueue.add(minQueue.poll());
            } else {
                maxQueue.add(val);
            }
        } else {
            if (val > maxQueue.peek()) {
                minQueue.add(val);
            } else {
                maxQueue.add(val);
                minQueue.add(maxQueue.poll());
            }
        }
        size++;
    }

    /**
     * 获得中位数
     * @return
     */
    public double getMedian() {
        if (size == 0) {
            return 0;
        }
        if (isEven(size)) {
            return (maxQueue.peek()+minQueue.peek()) / 2.0;
        } else {
            return maxQueue.peek();
        }
    }


}
/*
维护一个大根堆和一个小根堆，大根堆放置前n/2的元素，小根堆放置后n/2的元素。和一个size的变量。
eg：{1，2，3，4，5}，大根堆：1，2，3。小根堆：4，5。
在加入元素的时候，与大根堆堆顶元素比较：
    如果该元素大于大根堆的堆顶元素（1）总元素size为奇数，将其加入小根堆中，（2）为偶数时，先将其加入小根堆，再将小根堆堆顶元素放入大根堆。
    如果该元素小于大根堆的堆顶元素（1）总元素size为偶数，将其加入大根堆中，（2）为奇数时，先将其加入大根堆，再将大根堆堆顶元素放入小根堆。
获得中位数时，判断总元素，为奇数直接返回大根堆堆顶元素，为偶数取大根堆与小根堆堆顶元素平均值。
 */