package com.hlj;

/**
 * @author HealerJean
 * @ClassName SingleBean
 * @date 2020-12-07  20:51.
 * @Description
 */
public class SingleBean {

    private final static SingleBean instance = null;

    private SingleBean() {
    }

    public static SingleBean getSingleBean(){
        if (instance == null){
            syn();
        }
        return instance;
    }

    public static synchronized void syn() {
        if (instance == null){
            instance = new SingleBean();
        }
    }
}
