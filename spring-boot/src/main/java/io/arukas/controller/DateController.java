package io.arukas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@RestController
public class DateController {

    @GetMapping("/localDate")
    public LocalDate todayLocalDate(){
        return LocalDate.now();
    }

    @GetMapping("/offsetDateTime")
    public OffsetDateTime todayOffsetDateTime() {
        return OffsetDateTime.now();
    }

}
