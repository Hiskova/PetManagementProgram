 //* @author Ricky Hayes 15888703

package model;


import java.util.ArrayList;

public class Customer {

    private int customerId = 0;
    private String fName = "";
    private String lName = "";
    private String str = "";
    private String apt = "";
    private String city = "";
    private String zip = "";
    private int st = 0;
    private String email = "";
    private int phoneType = 0;
    private String phone = "";
    private boolean canText = false;
    private boolean isActive = false;
    private final ListHelper<Customer> helper = new ListHelper<>();

    public Customer(int customerId, String FirstName, String LastName, String Street, String Apt, String City, String Zip, int State, String Email, int PhoneType, String Phone, Boolean canText, Boolean isActive) {               //
        this.customerId = customerId;
        this.fName = FirstName;
        this.lName = LastName;
        this.str = Street;
        this.apt = Apt;
        this.city = City;
        this.st = State;
        this.zip = Zip;
        this.email = Email;
        this.phone = Phone;
        this.phoneType = PhoneType;
        this.canText = canText;
        this.isActive = isActive;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public String getStreet() {
        return str;
    }

    public String getApt() {
        return apt;
    }

    public String getCity() {
        return city;
    }

    public int getState() {
        return st;
    }

    public String getZip() {
        return zip;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getPhoneType() {
        return phoneType;
    }

    public Boolean getCanText() {
        return canText;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public ArrayList<Customer> getCustomerl() {
        return helper.getList();
    }
}
