// * @author Ricky Hayes 15888703

package model;

import java.util.ArrayList;

public class PhoneList {

    private int PhoneId = 0;
    private String shortDesc = "";
    private String longDesc = "";
    private boolean isActive = false;
    private final ListHelper<PhoneList> helper = new ListHelper<>();

    public PhoneList(int myId, String myShortDesc, String myLongDesc, boolean myIsActive) {
        this.PhoneId = myId;
        this.shortDesc = myShortDesc;
        this.longDesc = myLongDesc;
        this.isActive = myIsActive;
    }

    public int getPhoneId() {
        return this.PhoneId;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public ArrayList<PhoneList> getPhoneList() {
        return (ArrayList<PhoneList>) helper.getList();
    }

}
