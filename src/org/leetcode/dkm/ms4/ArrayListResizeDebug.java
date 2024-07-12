package org.leetcode.dkm.ms4;

import java.util.ArrayList;
import java.util.List;

public class ArrayListResizeDebug {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 17 ; i++) {
            list.add(i);
        }

        list.forEach(r -> System.out.print(r + " "));
    }
}
