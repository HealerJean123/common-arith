package com.hlj.niubiArith.d01_LRU.D03_方法3;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author HealerJean
 * @date 2020/12/4  14:59.
 * @description
 */
 public class LRU<K, V> {
    private int capcity;//总容量
    private HashMap<K, Node> caches;//所有的node节点
    LinkedList<Node> linkedList = new LinkedList<>();
    public LRU(int capcity) {
        this.capcity = capcity;
        caches = new HashMap<>(capcity);
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
                caches.remove(linkedList.getLast().key);
                linkedList.removeLast();
            }
            //创建新节点
            node = new Node(key,value);
        }else{
            //已经存在的元素，先删除
            linkedList.remove(node);
            node.value = value;
        }
        //把元素移动到首部
        linkedList.addFirst(node);
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
        linkedList.remove(node);
        linkedList.addFirst(node);
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
           linkedList.remove(node);
        }
        return caches.remove(key);
    }

    /**
     * 清除所有节点
     */
    public void clear() {
        caches.clear();
        linkedList.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!linkedList.isEmpty()){
            linkedList.stream().forEach(node ->{
                sb.append(String.format("%s:%s ", node.key, node.value));
            });
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
    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}
