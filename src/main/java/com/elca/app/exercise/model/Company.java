package com.elca.app.exercise.model;

public class Company {
    private int id;
    private String name;
    private String Date;
    private int capital;
    private String country;
    private Boolean isHeadQuarter;

    public Company() {
    }

    public Company(int id, String name, String date, int capital, String country, Boolean isHeadQuarter) {
        this.id = id;
        this.name = name;
        Date = date;
        this.capital = capital;
        this.country = country;
        this.isHeadQuarter = isHeadQuarter;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Date='" + Date + '\'' +
                ", capital=" + capital +
                ", country='" + country + '\'' +
                ", isHeadQuarter=" + isHeadQuarter +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getHeadQuarter() {
        return isHeadQuarter;
    }

    public void setHeadQuarter(Boolean headQuarter) {
        isHeadQuarter = headQuarter;
    }
}
