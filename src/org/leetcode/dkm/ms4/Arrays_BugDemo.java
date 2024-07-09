package org.leetcode.dkm.ms4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arrays_BugDemo {
    public static void main(String[] args) {
        // 数组的不可变性,使用Arrays.asList()方法,返回的是java.util.Arrays$ArrayList，并不是java.util.ArrayList
        //不能使用add()方法，否则会报错
//        List<Integer> list = Arrays.asList(1,2,3,4,5);
        // 解决办法：在java.util包下有一个ArrayList类，使用这个类就可以解决这个问题，直接使用new ArrayList()即可
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        list.add(6);
        list.forEach(System.out::println);
    }
}
