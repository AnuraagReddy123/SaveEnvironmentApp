package util;

import com.google.firebase.firestore.DocumentReference;

public class UserApi {
    private String firstName;
    private String lastName;
    private String uId;
    private String phoneNumber;
    private static UserApi instance;

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

    public void setUid(String uId) {
        this.uId = uId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
