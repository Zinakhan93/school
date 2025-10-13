package ru.hogwarts.school.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.CalculationService;

import java.util.stream.IntStream;

@RestController

public class NewController {
    private final CalculationService calculationService;

    public NewController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/sum")
    public int calculateSum() {
        return calculationService.calculateSum();
    }

    @GetMapping("/sum-fast")
    public int calculateSumFast() {
        return calculationService.calculateSumFast();
    }
}
