package com.hlj.niubiArith.d01_LRU.d01_方法1;

import java.util.HashMap;

/**
 * @author HealerJean
 * @date 2020/12/4  14:59.
 * @description
 */
 public class LRU<K, V> {
    private int capcity;//总容量
    private HashMap<K, Node> caches;//所有的node节点
    private Node first;//头节点
    private Node last;//尾节点

    public LRU(int size) {
        this.capcity = size;
        caches = new HashMap<>(size);
    }

    /**
     * 放入元素
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        //1、从缓存中取
        Node node = caches.get(key);
        //2、如果新元素
        if (node == null) {
            //如果超过元素容纳量
            if (caches.size() >= capcity) {
                //移除最后一个节点
                caches.remove(last.key);
                removeLast();
            }
            //创建新节点
            node = new Node(key,value);
        }else{
            //已经存在的元素覆盖旧值
            node.value = value;
        }

        //把元素移动到首部
        moveToHead(node);
        caches.put(key, node);
    }

    /**
     * 通过key获取元素
     * @param key
     * @return
     */
    public Object get(K key) {
        Node node = caches.get(key);
        if (node == null) {
            return null;
        }
        //把访问的节点移动到首部
        moveToHead(node);
        return node.value;
    }

    /**
     * 根据key移除节点
     * @param key
     * @return
     */
    public Object remove(K key) {
        Node node = caches.get(key);
        if (node != null) {
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (node == first) {
                first = node.next;
            }
            if (last  == node) {
                last = node.pre;
            }
        }
        return caches.remove(key);
    }

    /**
     * 清除所有节点
     */
    public void clear() {
        first = null;
        last = null;
        caches.clear();
    }

    /**
     * 把当前节点移动到首部
     * @param node
     */
    private void moveToHead(Node node) {
        //1、如果是第一个节点，则返回即可，不需要变化
        if (first == node) {
            return;
        }
        //2、如果是最后一个节点,则重置最后一个节点
        if (last == node) {
            last = last.pre;
        }

        //3、如果是中间节点，则改变他下一个节点的指针pre指针
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        //3、如果是中间节点，则改变他上一个节点的指针next指针
        if (node.pre != null) {
            node.pre.next = node.next;
        }

        //如果首位节点都是null，则 当前节点就是first节点
        if (first == null || last == null) {
            first = last = node;
            return;
        }

        //此时讲节点设置为第一个节点
        node.next = first;
        first.pre = node;
        first = node;
        first.pre = null;
    }

    /**
     * 移除最后一个节点
     */
    private void removeLast() {
        if (last != null) {
            last = last.pre;
            //如果last等于null，说过该节点是第一个元素
            if (last == null) {
                first = null;
            } else {
                //如果不是则，讲last的nex设置null
                last.next = null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = first;
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.next;
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        LRU<Integer, String> lru = new LRU<Integer, String>(5);
        lru.put(1, "a");
        lru.put(2, "b");
        lru.put(3, "c");
        lru.put(4,"d");
        lru.put(5,"e");
        System.out.println("原始链表为:"+lru.toString());

        lru.get(4);
        System.out.println("获取key为4的元素之后的链表:"+lru.toString());

        lru.put(6,"f");
        System.out.println("新添加一个key为6之后的链表:"+lru.toString());

        lru.remove(3);
        System.out.println("移除key=3的之后的链表:"+lru.toString());
    }
}

class Node {
    //键
    Object key;
    //值
    Object value;
    //上一个节点
    Node pre;
    //下一个节点
    Node next;

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}
