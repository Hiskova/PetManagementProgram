// * @author Ricky Hayes 15888703
package model;

import java.util.ArrayList;

public class CustomerList {

    private int customerId = 0;
    private String fName = "";
    private String lName = "";
    private String streetAddress = "";
    private String apt = "";
    private String city = "";
    private String postal = "";
    private int stateId = 0;
    private boolean isactive = false;

    private final ListHelper<CustomerList> helper = new ListHelper<>();

    public CustomerList() {

    }

    public CustomerList(int myId, String myFName, String myLName, String myAdd, String myApt, String myCity, String myPostal, int myState, boolean myIsActive) {
        this.customerId = myId;
        this.fName = myFName;
        this.lName = myLName;
        this.streetAddress = myAdd;
        this.apt = myApt;
        this.city = myCity;
        this.postal = myPostal;
        this.stateId = myState;
        this.isactive = myIsActive;

    }

    public int getCID() {
        return this.customerId;
    }

    public String getFName() {
        return this.fName;
    }

    public String getLName() {
        return this.lName;
    }

    public String getStreet() {
        return this.streetAddress;
    }

    public String getApt() {
        return this.apt;
    }

    public String getCity() {
        return this.city;
    }

    public String getPostal() {
        return this.postal;
    }

    public int getStateID() {
        return this.stateId;
    }

    public boolean getIsActive() {
        return this.isactive;
    }

    public ArrayList<CustomerList> getCustomerList() {
        return helper.getList();
    }
}
