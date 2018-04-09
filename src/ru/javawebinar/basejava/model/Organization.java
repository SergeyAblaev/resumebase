package ru.javawebinar.basejava.model;

import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * gkislin
 * 19.07.2016
 */
public class Organization {
    private final Link homePage;
    //private final LocalDate startDate;
    //private final LocalDate endDate;
    //private LocalDate startEndDate[][] ;
   // private Pair<LocalDate, LocalDate> startEndDate[];
    private List<Titles> titles;



    private final String description;




    public Organization(String name, String url, List<Titles> titles, String description) {
        Objects.requireNonNull(titles, "titles must not be null");
      //  Objects.requireNonNull(endDate, "endDate must not be null");
      //  Objects.requireNonNull(title, "title must not be null");
        this.homePage = new Link(name, url);
        this.titles = titles;
     //   this.endDate = endDate;
      //  this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
      //  if (!startDate.equals(that.startDate)) return false;
      //  if (!endDate.equals(that.endDate)) return false;
       // if (!title.equals(that.title)) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
      //  result = 31 * result + startEndDate.hashCode();
     //   result = 31 * result + endDate.hashCode();
        result = 31 * result + titles.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
             //   ", startDate=" + startDate +
             //   ", endDate=" + endDate +
              //  startEndDate.toString()+
             //   ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}