package com.test.model;

import java.util.Objects;

public class Personal {
    private int id;
    private String firstName;
    private String lastName;
    private int jobId;
    private int bossID;
    private int salary;
    private int exp;

    public Personal() {
    }

    public Personal(int id, String firstName, String lastName, int jobId, int bossID, int salary, int exp) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobId = jobId;
        this.bossID = bossID;
        this.salary = salary;
        this.exp = exp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getBossID() {
        return bossID;
    }

    public void setBossID(int bossID) {
        this.bossID = bossID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personal)) return false;
        Personal personal = (Personal) o;
        return getId() == personal.getId() &&
                getJobId() == personal.getJobId() &&
                getBossID() == personal.getBossID() &&
                getSalary() == personal.getSalary() &&
                getExp() == personal.getExp() &&
                Objects.equals(getFirstName(), personal.getFirstName()) &&
                Objects.equals(getLastName(), personal.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getJobId(), getBossID(), getSalary(), getExp());
    }

    @Override
    public String toString() {
        return "Personal{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jobId=" + jobId +
                ", bossID=" + bossID +
                ", salary=" + salary +
                ", exp=" + exp +
                '}';
    }
}
