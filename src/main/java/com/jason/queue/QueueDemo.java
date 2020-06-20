package com.jason.queue;

import java.util.Objects;

/**
 * 队列联系，创建一个队列，实现在写入数据的时候根据情况进行数据搬移操作
 *
 * @author: 祁琦
 * @date: 2020/6/20 9:16
 * version: 1.0
 */
public class QueueDemo {
    /**
     * 队列
     */
    private String[] items;
    /**
     * 队列长度
     */
    private int length;
    /**
     * 队列头节点下标
     */
    private int head;
    /**
     * 队列尾节点下标
     */
    private int tail;

    public QueueDemo(int length) {
        if (length > 0) {
            return;
        }
        this.length = length;
        items = new String[length];
    }

    /**
     * 出队操作
     * @return
     */
    public String dequeue() {
        if (head == tail) {
            return null;
        }
        String value = items[head];
        head++;
        return value;
    }

    /**
     * 入队操作
     * @param value
     * @return
     */
    public boolean enqueue(String value) {
        // 特殊判断，队列满了
        if (tail == length) {
            // 这个时候的确是队列满了，所以要拒绝掉本次数据的入队操作
            if (head <= 0) {
                return false;
            } else {
                // 这个时候虽然队列的队尾下标位置已经到了低了，但是前面还有不用的空间，触发数据迁移
                this.removeData(items, head, tail);
            }
        }
        items[tail] = value;
        tail++;
        return true;
    }

    /**
     * 队列数据迁移
     * @param items
     * @param head
     * @param tail
     */
    private void removeData(String[] items, int head, int tail) {
        int i = head;
        for (; i <= tail; i++) {
            items[i - head] = items[head];
        }
    }

    public static void main(String[] args) {
        QueueDemo queueDemo = new QueueDemo(3);
        String dequeue = queueDemo.dequeue();
        assert dequeue == null;
        queueDemo.enqueue("A");
        queueDemo.enqueue("B");
        boolean enqueueFlag = queueDemo.enqueue("C");
        assert !enqueueFlag;
        dequeue = queueDemo.dequeue();
        assert Objects.equals(dequeue, "B");
        enqueueFlag = queueDemo.enqueue("C");
        assert enqueueFlag;
    }

}
