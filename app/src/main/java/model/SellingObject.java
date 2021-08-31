package model;

public class SellingObject {
    private String firstName,lastName;
    private String phoneNumber, productDescription, price;

    public SellingObject(String firstName, String lastName, String phoneNumber, String productDescription, String price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.productDescription = productDescription;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


}
