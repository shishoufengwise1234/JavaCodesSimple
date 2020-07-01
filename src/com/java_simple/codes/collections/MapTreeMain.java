package com.java_simple.codes.collections;

import com.java_simple.codes.PKt;

import java.time.temporal.ValueRange;
import java.util.HashMap;

public class MapTreeMain {

    public static void main(String[] args) {
        HashMap<MapTreeMain, Integer> map = new HashMap<>(1);

        for (int i = 0; i < 1000; i++) {
            map.put(new MapTreeMain(),i);
        }
        PKt.out(map);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
