package com.example.springtestcode;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberCollection {
    List<Integer> numbers;

    public LottoNumberCollection() {
        this.numbers = new ArrayList<>();
    }

    public List<Integer> createNumbers() {
        this.numbers.add(0, 1);
        this.numbers.add(1, 2);
        this.numbers.add(2, 3);
        this.numbers.add(3, 4);
        this.numbers.add(4, 5);
        this.numbers.add(5, 6);
        return numbers;
    }
}
