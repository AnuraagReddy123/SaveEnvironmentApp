package util;

public class UserApi {
    private String firstName;
    private String lastName;
    private String uId;
    private String phoneNumber;
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

    public static void setInstance(UserApi instance) {
        UserApi.instance = instance;
    }

    public String getUserId() {
        return uId;
    }

    public void setUserid(String uId) {
        this.uId = uId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
