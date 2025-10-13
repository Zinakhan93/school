package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;

import java.util.stream.IntStream;
@Service
public class CalculationService {

    public int calculateSum() {
        long startTime1 = System.currentTimeMillis();
        int sum = IntStream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
        long endTime1 = System.currentTimeMillis();
        System.out.println("Calculation time: " + (endTime1 - startTime1) + " ms");
        return sum;
    }
    public int calculateSumFast() {
        long startTime2 = System.currentTimeMillis();
        int sumFast = IntStream.rangeClosed(1, 1_000_000)
                .parallel()
                .sum();
        long endTime2 = System.currentTimeMillis();
        System.out.println("Calculation time2: " + (endTime2 - startTime2) + " ms");
        return sumFast;
    }
}
