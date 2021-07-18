package com.develeno.mylo.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devel_000 on 25-Jan-18.
 */
public class WishList {
    private List<String> list;

    public WishList() {
        list = new ArrayList<>();
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void add(String productId) {
        list.add(productId);
    }

    public void remove(String productId) {
        list.remove(productId);
    }

    public boolean contains(String first) {
        for (String s : list) {
            if (s.equalsIgnoreCase(first)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        list.clear();
    }
}
