package com.blueticks.saveenvironment;

public class Idea {
    private String firstName;
    private String lastName;
    private String text;

    public Idea(String firstName, String lastName, String text) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.text = text;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getText() {
        return text;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setText(String text) {
        this.text = text;
    }
}
