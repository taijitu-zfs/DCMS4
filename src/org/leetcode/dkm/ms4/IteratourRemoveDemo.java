package org.leetcode.dkm.ms4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratourRemoveDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 12) {
                //报错原因：迭代器遍历过程中，不能对集合进行增删改操作
//                list.remove(integer);
                //正确写法：使用迭代器本身的remove方法，即iterator.remove();
                iterator.remove();
            }
        }

        list.forEach(v ->{
            System.out.println(v);
        });

    }
}
