package org.mog.temp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

public class FirstDoubleNumberApp {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 2, 5,  3, 4, 5, 6, 7, 8);

        BiPredicate<Integer, Integer> isBiggerThan = (evaluated, evaluator) -> evaluated.compareTo(evaluator) > 0;

        Optional<Integer> integer = numbers.stream()
                .filter(n-> isBiggerThan.test(n, 3))
                .filter(n -> n % 2 == 0)
                .findFirst()
                .map(FirstDoubleNumberApp::doubleIt);

        integer.ifPresent(System.out::println);
        integer.orElseThrow(RuntimeException::new);


    }

    private static Integer doubleIt(Integer number){
        return number * 2;
    }
}
