package org.leetcode.dkm.ms4;

import java.util.HashSet;
import java.util.Set;

/**
 * ==  比较的是new出来的对象是false，基本类型是比较值，引用类型是比较地址
 *
 */
public class TestEquals {
    public static void main(String[] args) {
        String str1 = new String("abc");
        String str2 = new String("abc");
        System.out.println(str1 == str2);
        /**
         * String.java
         *     public boolean equals(Object anObject) {
         *         if (this == anObject) {
         *             return true;
         *         }
         *         return (anObject instanceof String aString)
         *                 && (!COMPACT_STRINGS || this.coder == aString.coder)
         *                 && StringLatin1.equals(value, aString.value);
         *     }
         *被复写过，判断值是不是一样
         */
        System.out.println(str1.equals(str2));
        //HashSet底层是个map，只关心key
        Set<String> set01 = new HashSet<>();
        set01.add(str1);
        set01.add(str2);
        System.out.println(set01.size());
        //Steing里的hashCode方法
        System.out.println(str1.hashCode() + "\t" + str2.hashCode());
        System.out.println("====================");

        Person p1 = new Person("abc");
        Person p2 = new Person("abc");
        System.out.println(p1 == p2);
        /**
         * Object.java
         * public boolean equals(Object obj) {
         *         return (this == obj);
         *     }
         *还是用的==，equals没有被复写过
         */
        System.out.println(p1.equals(p2));
        Set<Person> set02 = new HashSet<>();
        set02.add(p1);
        set02.add(p2);
        System.out.println(set02.size());
        //Object里的hashCode方法   两个不同的对象不同hashCode
        System.out.println(p1.hashCode() + "\t" + p2.hashCode());
        System.out.println("===================");
        System.out.println();
    }
}
