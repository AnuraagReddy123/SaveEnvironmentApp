package util;

import model.User;

public class UserApi {
//    private String firstName;
//    private String lastName;
      private String uId;
//    private String phoneNumber;
//    private double currentMoney;
//    private double targetMoney;
//    private double electricityBill;
    private User currentUser;
    private static UserApi instance;
    public static final String COLLECTIONS_NAME = "Users";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String TARGET_MONEY = "targetMoney";
    public static final String CURRENT_MONEY = "currentMoney";
    public static final String ELECTRICITY_BILL = "electricityBill";
    public static final String LOG_IN = "isSignIn";

    public static UserApi getInstance() {
        if (instance == null)
            instance = new UserApi();
        return instance;
    }

    public UserApi() {
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public static void setInstance(UserApi instance) {
//        UserApi.instance = instance;
//    }
//
    public String getUserId() {
        return uId;
    }
    public void setUserid(String uId) {
        this.uId = uId;
    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setTargetMoney(double targetMoney) {
//        this.targetMoney = targetMoney;
//    }
//
//    public double getTargetMoney() {
//        return targetMoney;
//    }
//
//    public void setCurrentMoney(double currentMoney) {
//        this.currentMoney = currentMoney;
//    }
//
//    public double getCurrentMoney() {
//        return currentMoney;
//    }
//
//    public void setElectricityBill(double electricityBill) {
//        this.electricityBill = electricityBill;
//    }
//
//    public double getElectricityBill() {
//        return electricityBill;
//    }
}
