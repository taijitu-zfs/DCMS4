package org.leetcode.dkm.ms4;

import java.util.HashSet;

/**
 * 哈希冲突
 */
public class HashConflictDemo {
    static class Book{
        int id;
    }
    public static void main(String[] args) {
//        m1();
//多少次Hash才会冲突
        HashSet<Integer> hashSet = new HashSet<>();
        for(int i = 0;i < 110000;i++){
            int bookHashCode = new Book().hashCode();
            if(!hashSet.contains(bookHashCode)){
                hashSet.add(bookHashCode);
            }else {
                System.out.println("发生了HASH冲突了，第" + i + "次,值是" + i);
            }
        }
    }




    private static void m1(){
        //写个Hash冲突的例子
        System.out.println("AA".hashCode());
        System.out.println("BB".hashCode());
        System.out.println();

        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());
        System.out.println();

        System.out.println("柳柴".hashCode());
        System.out.println("柴柕".hashCode());

    }

}
