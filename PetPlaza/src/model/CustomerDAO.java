package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.DAOHelper;
import model.ListHelper;

public class CustomerDAO<E> extends DAOHelper {

    //ListHelper can be used by any variable that needs to 
    //return an ArrayList. 
    private final ListHelper<E> listHelper = new ListHelper<>();
    private Customer Customer;

    public CustomerDAO() throws SQLException {

    }



    public List<E> getCustomerData(String data) throws SQLException {

        List<E> allItems;
        allItems = fetchCustomerData(data);

        return allItems;
    }

    @SuppressWarnings("unchecked")
    private List<E> fetchCustomerData(String data) throws
            SQLException {

        String myProc;

        myProc = "{Call fetch_customer_by_last(?)}";

        try (Connection conn = super.getConnection(); //DriverManager.getConnection(url,"","");
                CallableStatement procCall = conn.prepareCall(myProc);) {

            procCall.setString(1, data);
            try {
                ResultSet rs = procCall.executeQuery();
                if (rs != null) {
                    while (rs.next()) {//scroll through the returned result set

                        Customer = new Customer(rs.getInt("customerId"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("streetAddress"),
                                rs.getString("apartment"),
                                rs.getString("city"),
                                rs.getString("postalCode"),
                                rs.getInt("stateId"), rs.getString("emailAddress"), rs.getInt("typeOfPhoneId"), rs.getString("phoneId"), rs.getBoolean("phoneIsActive"), rs.getBoolean("customerIsActive"));

                        //add the each object to an ArrayList
                        listHelper.addItem((E) Customer);

                    }//end of while
                }//end of if(rs != null)
            } catch (SQLException ex) {
                System.out.println(ex + " ...Something happened while "
                        + "trying to execute the query");
            }
        }//end of try
        catch (SQLException badSQL) {
            for (Throwable t : badSQL) {
                System.out.println(t.getMessage());
            }
        }//end catch
        //myList will either be empty, nothing returned, or contain data
        return (ArrayList<E>) listHelper.getList();

    }//end fetchData

}//end of class
