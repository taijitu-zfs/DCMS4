package org.leetcode.dkm.ms4;

import java.util.*;
import java.util.stream.Collectors;

public class ListRemoveDuplicates {
    public static void main(String[] args) {
//        m1();
//        m2();
//        m3();
//        m4();
        m5();
    }

    /**
     * 双层for循环，找到值一样的则说明重复，删除
     */
    private static void m5(){
        List<Integer> list = Arrays.asList(70,70,-1,5,3,3,4,4,4,99);
        List<Integer> srclist = new ArrayList<>(list);
        List<Integer> newlist = new ArrayList<>(list);

        for (int i = 0; i < newlist.size() - 1; i++) {
            for (int j = newlist.size() - 1; j > i ; j--) {
                if (newlist.get(j).equals(newlist.get(i))){
                    newlist.remove(j);
                }
            }
        }
        newlist.forEach(s -> System.out.print(s + " "));
    }

    /**
     * 循环坐标去重复，前往后找与后往前找同一元素的下标不一致，则删除其中一个
     */
    private static void m4(){
        List<Integer> list = Arrays.asList(70,70,-1,5,3,3,4,4,4,99);
        List<Integer> srclist = new ArrayList<>(list);
        List<Integer> newlist = new ArrayList<>(list);

        for (Integer element:srclist) {
            if(newlist.indexOf(element) != newlist.lastIndexOf(element)){
                newlist.remove(newlist.lastIndexOf(element));
            }
        }
        newlist.forEach(s -> System.out.print(s + " "));
    }

    /**
     * Stream流计算
     */
    private static void m3(){
        List<Integer> list = Arrays.asList(70,70,-1,5,3,3,4,4,4,99);
        List<Integer> srclist = new ArrayList<>(list);
        List<Integer> newlist = new ArrayList<>();

        newlist = srclist.stream().distinct().collect(Collectors.toList());
        newlist.forEach(s -> System.out.print(s + " "));
    }
    /**
     * HashSet或linkedHashSet
     */
    private static void m2(){
        //无序
        List<Integer> srclist = Arrays.asList(70,70,-1,5,3,3,4,4,4,99);
        List<Integer> newlist = new ArrayList<>(new HashSet<>(srclist));
        newlist.forEach(s -> System.out.print(s + " "));
        System.out.println();

        //有序
        newlist = new ArrayList<>(new LinkedHashSet<>(srclist));
        newlist.forEach(s -> System.out.print(s + " "));
    }
    /**
     * for循环遍历判断
     */
    private static void m1(){
        List<Integer> list = Arrays.asList(70,70,-1,5,3,3,4,4,4,99);
        List<Integer> srclist = new ArrayList<>(list);
        List<Integer> newlist = new ArrayList<>();

        for (int i = 0; i < srclist.size(); i++) {
            Integer tempValue = list.get(i);
            if (!newlist.contains(tempValue)){
                newlist.add(tempValue);
            }
        }
        System.out.println(newlist);

    }
}
