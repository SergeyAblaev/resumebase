package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Titles {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;

    public Titles(LocalDate startDate, LocalDate endDate, String title) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }
}
