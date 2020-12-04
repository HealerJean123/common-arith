package com.hlj.niubiArith.d01_LRU.d02_方法2;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author HealerJean
 * @date 2020/12/4  15:24.
 * @description
 */
class LRU<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;

    /**
     * true 表示让 linkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部。
     * cacheSize / 0.75 来源于 0.75 * capacity = cacheSize
     * Math.ceil()“向上取整”, 即小数部分直接舍去，因为舍去了小数部分所以 + 1
     *
     * capacity =  (int) Math.ceil(cacheSize / 0.75) + 1,
     */
    public LRU(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    /**
     * 当 map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > CACHE_SIZE;
    }

}
