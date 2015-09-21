// * @author Ricky Hayes 15888703

package model;

import java.util.ArrayList;

class ListHelper<T> {

    private ArrayList<T> myItems = null;

    public ListHelper() {
        myItems = new ArrayList<>();
    }

    public ArrayList<T> getList() {
        return this.myItems;
    }

    public void addItem(T myItem) {
        myItems.add(myItem);
    }

    public int getListSize(ArrayList<T> myList) {
        return myList.size();
    }

}
