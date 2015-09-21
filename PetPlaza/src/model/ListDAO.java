// * @author Ricky Hayes 15888703

package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ListDAO<E> extends DAOHelper {

    private final ListHelper<E> listHelper = new ListHelper<>();
    private StateList states;
    private PhoneList PhoneList;

    public ListDAO() throws SQLException {

    }

    public List<E> getTypeData(String myListType) throws SQLException {

        String typeOfList;
        typeOfList = myListType;
        List<E> allItems;
        allItems = fetchTypeData(typeOfList);

        return allItems;
    }

    public boolean putTypeData(Customer userInfo) throws SQLException {
        boolean success = false;
        success = insertCustomer(userInfo);

        return success;
    }

    public boolean updateTypeData(Customer userInfo) throws SQLException {
        boolean success = false;
        success = updateCustomer(userInfo);

        return success;
    }

    @SuppressWarnings("unchecked")
    private List<E> fetchTypeData(String typeOfList) throws
            SQLException {

        String myProcedure;
        if (typeOfList.equals("States")) {
            myProcedure = "{Call fetch_state_list_active()}";
        } else {
            myProcedure = "{Call fetch_type_Of_phone_active()}";
        }

        try (Connection conn = super.getConnection();
                CallableStatement procedureCall = conn.prepareCall(myProcedure);
                ResultSet rs = procedureCall.executeQuery();) {

            if (rs != null) {
                while (rs.next()) {
                    if (typeOfList.equals("States")) {
                        states = new StateList(rs.getInt("id"),
                                rs.getString("shortDesc"),
                                rs.getString("longDesc"),
                                rs.getBoolean("isActive"));
                        listHelper.addItem((E) states);
                    } else {
                        PhoneList = new PhoneList(rs.getInt("id"),
                                rs.getString("shortDesc"),
                                rs.getString("longDesc"),
                                rs.getBoolean("isActive"));
                        listHelper.addItem((E) PhoneList);

                    }
                }
            }
        } catch (SQLException badSQL) {
            for (Throwable t : badSQL) {
                System.out.println(t.getMessage());
            }
        }
        return (ArrayList<E>) listHelper.getList();

    }

    private boolean insertTypeData(String shortDesc, String longDesc,
            String tableName) throws SQLException {
        boolean success = false;
        String myProcedure = "";
        int result = 0;

        switch (tableName) {
            case "StateList":
                myProcedure = "{Call insert_state_list(?,?,?)}";
                break;
            case "PhoneList":
                myProcedure = "{Call insert_type_of_phone(?,?,?)}";
                break;
            default:
                myProcedure = "";
                break;
        }

        try (Connection conn = super.getConnection();
                CallableStatement procedureCall = conn.prepareCall(myProcedure);) {
            procedureCall.setString(2, shortDesc);
            procedureCall.setString(3, longDesc);
            procedureCall.registerOutParameter(1, Types.INTEGER);
            procedureCall.execute();
            result = procedureCall.getInt(1);
            System.out.println(result);
            if (result > 0) {
                success = true;
            }
        } catch (SQLException badSQL) {
            for (Throwable t : badSQL) {
                System.out.println(t.getMessage());
            }
        }
        return success;
    }

    private boolean insertCustomer(Customer userInfo) throws SQLException {
        boolean success = false;
        String myProcedure = "{call insert_customer(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        int result = 0;

        try (Connection conn = super.getConnection();
                CallableStatement procedureCall = conn.prepareCall(myProcedure);) {
            procedureCall.setString(2, userInfo.getFirstName());
            procedureCall.setString(3, userInfo.getLastName());
            procedureCall.setString(4, userInfo.getStreet());
            procedureCall.setString(5, userInfo.getApt());
            procedureCall.setString(6, userInfo.getCity());
            procedureCall.setString(7, userInfo.getZip());
            procedureCall.setInt(8, userInfo.getState());
            procedureCall.setString(9, userInfo.getEmail());
            procedureCall.setInt(10, userInfo.getPhoneType());
            procedureCall.setString(11, userInfo.getPhone());
            procedureCall.setBoolean(12, userInfo.getCanText());
            procedureCall.setBoolean(13, userInfo.getIsActive());
            procedureCall.registerOutParameter(1, Types.INTEGER);
            procedureCall.execute();
            result = procedureCall.getInt(1);
            System.out.println(result);
            if (result > 0) {
                success = true;
            }
        } catch (SQLException badSQL) {
            for (Throwable t : badSQL) {
                System.out.println(t.getMessage());
            }
        }
        return success;
    }

    private boolean updateCustomer(Customer userInfo) throws SQLException {
        boolean success = false;
        String myProcedure = "{call update_customer(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        int result = 0;

        try (Connection conn = super.getConnection();
                CallableStatement procedureCall = conn.prepareCall(myProcedure);) {
            procedureCall.setInt(1, userInfo.getCustomerId());
            procedureCall.setString(2, userInfo.getFirstName());
            procedureCall.setString(3, userInfo.getLastName());
            procedureCall.setString(4, userInfo.getStreet());
            procedureCall.setString(5, userInfo.getApt());
            procedureCall.setString(6, userInfo.getCity());
            procedureCall.setString(7, userInfo.getZip());
            procedureCall.setInt(8, userInfo.getState());
            procedureCall.setString(9, userInfo.getEmail());
            procedureCall.setInt(10, userInfo.getPhoneType());
            procedureCall.setString(11, userInfo.getPhone());
            procedureCall.setBoolean(12, userInfo.getCanText());
            procedureCall.setBoolean(13, userInfo.getIsActive());
            procedureCall.execute();
            result = procedureCall.getInt(1);
            System.out.println(result);
            if (result > 0) {
                success = true;
            }
        } catch (SQLException badSQL) {
            for (Throwable t : badSQL) {
                System.out.println(t.getMessage());
            }
        }
        return success;
    }

}
