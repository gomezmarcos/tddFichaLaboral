package org.mog.temp.dominio;

public class Person {
    private final String lastName;
    private final String firstName;
    private final Integer age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return String.format("%s, %s (%s)", this.lastName, this.firstName, this.age);
    }
}
