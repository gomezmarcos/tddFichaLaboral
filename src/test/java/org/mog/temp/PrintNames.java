package org.mog.temp;

import org.mog.temp.dominio.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PrintNames {
    public static void main(String args[]){
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carrol", 30),
                new Person("Thomas", "Carlyle", 20),
                new Person("Charlotte", "Bronte", 11),
                new Person("Matthew", "Arnols", 34)
        );

        System.out.println("### Print all");
        printConditional(people, p-> true, p-> System.out.print(p));
        System.out.println("### Print full name when starts with C");
        printConditional(people, p-> p.getLastName().startsWith("C"), p-> System.out.print(p));
        System.out.println("### Print first name when starts with C");
        printConditional(people, p-> p.getLastName().startsWith("C"), p-> System.out.print(p.getFirstName()));





    }

    public static void printConditional(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer){
        people.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .filter(p-> predicate.test(p))
                .forEach(p-> consumer.accept(p));


    }
}

