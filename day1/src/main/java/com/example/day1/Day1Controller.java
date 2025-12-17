package com.example.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/day1")
public class Day1Controller {
    
    @GetMapping("/basics")
    public void basics() {

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        
        int sum = numbers.stream()
                .reduce(0, Integer::sum);

        Optional<Integer> max = numbers.stream()
                .max(Integer::compareTo);

        System.out.println("Sum: " + sum);

        max.ifPresent(n -> System.out.println("Max: " + n));
    }
}
