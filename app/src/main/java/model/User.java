package model;

public class User {

    private String firstName,lastName;
    private String phoneNumber;
    private double electricityBill;
    private double targetMoney,currentMoney;

    public User() {

    }

    public User (String firstName,String lastName,String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public double getElectricityBill(){
        return electricityBill;
    }

    public Double getTargetMoney(){
        return targetMoney;
    }

    public Double getCurrentMoney() {
        return currentMoney;
    }

    public void setElectricityBill(double electricityBill) {
        this.electricityBill = electricityBill;
    }

    public void setTargetMoney(double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }
}
