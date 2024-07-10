package org.leetcode.dkm.ms4;

public class IntergeBugDemo {
    public static void main(String[] args) {
        //从Java8->Java17构造方法行？
//        Integer integer = new Integer(22);
        //解决方法：使用静态方法
        Integer integer = Integer.valueOf(22);

        //比较原因：有范围限制-128~127之间的值会复用，超出范围时，会创建新的对象
        //所以比较时，全部使用equals方法
        //Integer值的比较
        Integer a = Integer.valueOf(600);
        Integer b = Integer.valueOf(600);
        int c = 600;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a == c);

        System.out.println("================");

        Integer x = Integer.valueOf(99);
        Integer y = Integer.valueOf(99);
        System.out.println(x == y);
        System.out.println(x.equals(y));
    }
}
