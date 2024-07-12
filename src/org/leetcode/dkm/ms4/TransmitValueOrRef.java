package org.leetcode.dkm.ms4;

public class TransmitValueOrRef {
    public void changeValue(int age){
        age = 30;
    }
    public void changeValue2(Person person){
        person.setPersonName("xxx");
    }
    public void changeValue3(String str){
        str = "xxx";
    }

    public static void main(String[] args) {
        TransmitValueOrRef transmit = new TransmitValueOrRef();
        int age = 20;
        transmit.changeValue(age);
        System.out.println("age="+age);
        Person person = new Person("Tom");
        transmit.changeValue2(person);
        System.out.println("name="+person.getPersonName());
        String str = "abc";
        transmit.changeValue3(str);
        System.out.println("str="+str);
    }
}
