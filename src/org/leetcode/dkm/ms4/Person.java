package org.leetcode.dkm.ms4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    private Integer id;
    private String personName;

    public Person(String personName){
        this.personName = personName;
    }
}
