package com.akeb.TP0.exo1;

/** 
 * Represents a person with a name and age.
 * Provides methods to get the full name and check if the person is an adult.
 */

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isAdult() {
        return age >= 18;
    }
}
