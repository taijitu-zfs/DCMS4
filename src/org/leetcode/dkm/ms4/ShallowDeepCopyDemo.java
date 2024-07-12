package org.leetcode.dkm.ms4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ShallowDeepCopyDemo implements Cloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
//        ShallowDeepCopyDemo shallowDeepCopyDemo = new ShallowDeepCopyDemo();
//        Object obj = shallowDeepCopyDemo.clone();
//        System.out.println(obj.hashCode());
        m1();

    }

    private static void m1() throws CloneNotSupportedException {
        Emp emp = new Emp("张三",20,"李四","CEO");
        System.out.println("原始对象：" + emp.getBoss().getTitle());

        Emp emp1 = (Emp) emp.clone();
        System.out.println("拷贝对象：" + emp1.getBoss().getTitle());
        System.out.println();

        emp1.getBoss().setTitle("CTO");

        System.out.println("原始对象：" + emp.getBoss().getTitle());
        System.out.println("拷贝对象：" + emp1.getBoss().getTitle());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Boos implements Cloneable {
        private String bossName;
        private String title;

        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Emp implements Cloneable {
        private String empName;
        private Integer age;
        private Boos boss;

        public Emp(String empName, Integer age,String bosName,String title) {
            this.empName = empName;
            this.age = age;
            this.boss = new Boos(bosName,title);
        }

//        @Override
//        protected Object clone() throws CloneNotSupportedException {
//            return super.clone();
//        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new Emp(this.empName, this.age,this.boss.getBossName(),this.boss.getTitle());
        }
    }
}
