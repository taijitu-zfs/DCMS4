package org.leetcode.dkm.ms4;

import java.math.BigDecimal;

public class BigDecimal_BugDemo {
    public static void main(String[] args) {
//        doubleDemo();
        m1();
    }
    public static void m1(){
        BigDecimal amount1 = new BigDecimal(0.03);
        BigDecimal amount2 = new BigDecimal(0.02);
        //阿里手册12，【强制】禁止使用构造方法 BigDecimal(double)的方式把 double 值转化为 BigDecimal。
        System.out.println("amount1: " + amount1);
        System.out.println("amount2: " + amount2);
        //赢等于0.01，但是实际结果不是
        System.out.println("amount1 - amount2等于：" + (amount1.subtract(amount2)));
        System.out.println();

        //推荐使用入参为String的构造方法
        BigDecimal amount3 = new BigDecimal("0.03");
        BigDecimal amount4 = new BigDecimal("0.02");
        System.out.println("amount3: " + amount3);
        System.out.println("amount4: " + amount4);
        //赢等于0.01，但是实际结果不是
        System.out.println("amount3 - amount4等于：" + (amount3.subtract(amount4)));
        System.out.println();

        //或者使用BigDecimal.valueOf(double)方法
        BigDecimal amount5 = BigDecimal.valueOf(1.665);
        BigDecimal amount6 = BigDecimal.valueOf(3.0);
        System.out.println("amount5 - amount6等于：" + (amount5.subtract(amount6)));
        System.out.println("amount5 + amount6等于：" + (amount5.add(amount6)));
        System.out.println();

        //等值比较，使用compareTo方法，不使用equals方法，因为equals会比较精度
//        BigDecimal amount7 = BigDecimal.valueOf(1.00);
//        BigDecimal amount8 = BigDecimal.valueOf(1.0);
        BigDecimal amount7 = new BigDecimal("1.00");
        BigDecimal amount8 = new BigDecimal("1.0");
        System.out.println("amount7 与 amount8 equals比较：" + amount7.equals(amount8));
        System.out.println("amount7 与 amount8 compareTo比较：" + amount7.compareTo(amount8));

        //除法，使用divide方法，需要指定精度，四舍五入的关键字是RoundingMode.HALF_UP，五舍六入的关键字是RoundingMode.HALF_DOWN，默认是四舍五入
        System.out.println(amount5.divide(amount6, 2, BigDecimal.ROUND_HALF_UP));
        System.out.println(amount5.divide(amount6, 2, BigDecimal.ROUND_HALF_DOWN));

        //科学计数法
        BigDecimal amount9 = BigDecimal.valueOf(1234567891236.2315468795);
        System.out.println(amount9);
        System.out.println(amount9.toString());
        System.out.println(amount9.toPlainString());
        System.out.println();

        BigDecimal amount10 = new BigDecimal("1234567891236.2315468795");
        System.out.println(amount10);
        System.out.println(amount10.toString());
        System.out.println(amount10.toPlainString());
        //推荐使用字符串构造方法

    }

    // 浮点数运算的bug,会造成精度丢失

    /**double类型的两个参数相减会装换成二进制
     * 因为double有效位数为16位，这样就会出现存储小数位数不够的情况
     * 这种情况下会出现误差
     */
    private static void doubleDemo(){
        double d1 = 0.03;
        double d2 = 0.02;
        System.out.println(d1 - d2); // 0.5000000000000001
    }

}
