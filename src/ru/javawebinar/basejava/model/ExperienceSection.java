package ru.javawebinar.basejava.model;

import java.util.Date;

public class ExperienceSection extends AllSection {
    private Date dateStart;
    private Date dateEnd;
    private String experience;

    public ExperienceSection(Date dateStart, Date dateEnd, String experience) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "AllSection{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", experience='" + experience + '\'' +
                '}';
    }
}
