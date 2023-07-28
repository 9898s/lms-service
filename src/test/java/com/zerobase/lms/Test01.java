package com.zerobase.lms;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test01 {
    @Test
    void TEST_01() {
        String value = "2023-07-28";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(value, formatter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
