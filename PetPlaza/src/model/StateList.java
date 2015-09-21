// * @author Ricky Hayes 15888703

package model;

import java.util.ArrayList;

public class StateList {

    private int stateId = 0;
    private String shortDesc = "";
    private String longDesc = "";
    private boolean isActive = false;

    private final ListHelper<StateList> helper = new ListHelper<>();

    StateList(int myId, String myShortDesc, String myLongDesc, boolean myIsActive) {
        this.stateId = myId;
        this.shortDesc = myShortDesc;
        this.longDesc = myLongDesc;
        this.isActive = myIsActive;

    }

    public int getStateId() {
        return this.stateId;
    }

    public String getShortDesc() {
        return this.shortDesc;
    }

    public String getLongDesc() {
        return this.longDesc;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public ArrayList<StateList> getStateList() {
        return (ArrayList<StateList>) helper.getList();
    }

}
