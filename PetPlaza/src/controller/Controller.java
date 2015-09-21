package controller;

import java.util.ArrayList;
import model.PhoneList;
import model.StateList;
import view.JFrameInterface;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import model.Customer;
import model.CustomerList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import model.CustomerDAO;
import model.ListDAO;

/**
 *
 * @author Ricky Hayes 15888703
 */
public class Controller<E> {

    private JFrameInterface view;
    private ArrayList<StateList> stateList;
    private ArrayList<PhoneList> phoneList;
    private HashMap<Integer, String> phoneDesc;
    private ArrayList<Customer> Customers;
    private int customerIdTemp;

    public Controller(JFrameInterface view) {
        this.view = view;
        MyButtonListener listen = new MyButtonListener();
        populateCBO();
        this.view.addMyListener(listen);

    }

    private ArrayList<Customer> getCustomerList() throws SQLException {
        try {
            CustomerDAO<E> customer = new CustomerDAO<>();
            Customers = (ArrayList<Customer>) customer.getCustomerData(view.gettxtLastName());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Customers;
    }

    public void getCustomersList() {
        try {
            Customers = getCustomerList();
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                System.out.println(t.getMessage());
            }
        }

        Customers.stream().forEach((myCustomer) -> {
            view.settxtFirstName(myCustomer.getFirstName());
            view.settxtLastName(myCustomer.getLastName());
            view.settxtStreet(myCustomer.getStreet());
            view.settxtApt(myCustomer.getApt());
            view.settxtCity(myCustomer.getCity());
            view.settxtZip(myCustomer.getZip());
            view.settxtEmail(myCustomer.getEmail());
            view.settxtPhone(myCustomer.getPhone());


        });

    }

    public static void main(String args[]) {
        /*Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameInterface().setVisible(true);
            }
        });
    }

    private ArrayList<StateList> getStateList() throws SQLException {
        try {
            ListDAO<E> states = new ListDAO<>();
            ListDAO<E> phoneTypes = new ListDAO<>();

            stateList = (ArrayList<StateList>) states.getTypeData("States");

            phoneList = (ArrayList<PhoneList>) phoneTypes.getTypeData("phoneTyoe");

            phoneDesc = new HashMap<>();

            phoneList.stream().forEach((phoneType) -> {
                phoneDesc.put(phoneType.getPhoneId(),
                        phoneType.getLongDesc());
            });
            System.out.println("Desc: " + phoneDesc);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return stateList;

    }

    private void populateCBO() {
        try {
            stateList = getStateList();
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                System.out.println(t.getMessage());
            }
        }
        stateList.stream().forEach((state) -> {
            this.view.getjCmbState().addItem(state.getLongDesc());
        });
        for (StateList state : stateList) {
            if (state.getLongDesc().equals("WI")) {
                this.view.getjCmbState()
                        .setSelectedItem(state.getLongDesc());
                break;
            }
        }

        phoneList.stream().forEach((phone) -> {
            this.view.getjCmbPhone().addItem(phone.getLongDesc());
        });
        for (PhoneList phone : phoneList) {
            if (phone.getLongDesc().equals("Primary")) {
                this.view.getjCmbPhone().setSelectedItem(phone.getLongDesc());
            }
        }
    }

    private ArrayList<Customer> UpdateCustomer(Customer userInfo) throws SQLException {

        try {
            ListDAO myListDAO = new ListDAO();
            myListDAO.updateTypeData(userInfo);
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                System.out.println(t.getMessage());
            }
        }
        return Customers;
    }

    private Customer getInfo() {
        int customerId = 0;
        String firstName = "";
        String lastName = "";
        String street = "";
        String apt = "";
        String city = "";
        String tempState = this.view.getjCmbState().getSelectedItem().toString();
        int stateid = 0;
        String postal = "";
        String email = "";
        String phone = "";
        String comments = "";
        String tempPhoneType = this.view.getjCmbPhone().getSelectedItem().toString();
        int phoneType = 0;
        String address = "";
        boolean canText = false;
        boolean isActive = false;
        for (StateList state : stateList) {
            if (state.getLongDesc().equals(tempState)) {
                stateid = state.getStateId();
            }
        }
        for (PhoneList phonet : phoneList) {
            if (phonet.getLongDesc().equals(tempPhoneType)) {
                phoneType = phonet.getPhoneId();
            }
        }

        StringBuilder sb = new StringBuilder();
        customerId = customerIdTemp;
        firstName = view.gettxtFirstName();
        lastName = view.gettxtLastName();
        street = view.gettxtStreet();
        apt = view.gettxtApt();
        city = view.gettxtCity();
        postal = view.gettxtZip();
        email = view.gettxtEmail();
        phone = view.gettxtPhone();
        comments = view.gettxtComments();
        canText = view.getjChkbxText().isSelected();
        isActive = view.getjChkbxActive().isSelected();

        if (!Validator.hasText(firstName)) {
            sb.append("Please enter your first name.");
            sb.append("/n");
        }

        if (!Validator.hasText(lastName)) {
            sb.append("Please enter your last name.");
            sb.append("/n");
        }

        if (!Validator.hasText(street)) {
            sb.append("Please enter your street address.");
            sb.append("/n");
        }

        if (!Validator.hasText(city)) {
            sb.append("Please enter your city.");
            sb.append("/n");
        }

        if (!Validator.isZipValid(postal)) {
            sb.append("Please enter your postal code (zip code).");
            sb.append("/n");
        }

        if (!Validator.isValidEmail(email)) {
            sb.append("Please enter a valid Email.");
            sb.append("/n");
        }

        if (!Validator.isPhoneNumberValid(phone)) {
            sb.append("Please enter a valid phone number.");
            sb.append("/n");
        }

        if (sb.length() == 0) {
            Customer userInfo = new Customer(customerId, firstName, lastName, street, apt, city, postal, stateid, email, phoneType, phone, canText, isActive);
            return userInfo;

        } else {
            JOptionPane.showMessageDialog(null, sb.toString());
            return null;
        }
    }

    private ArrayList<Customer> addCustomerLIst(Customer userInfo) throws SQLException {

        try {
            ListDAO myListDAO = new ListDAO();
            myListDAO.putTypeData(userInfo);
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                System.out.println(t.getMessage());
            }
        }
        return Customers;
    }

    private void createMessage(Customer userInfo) {
        StringBuilder sbb = new StringBuilder();

        sbb.append(
                "Name: " + userInfo.getFirstName() + " " + userInfo.getLastName() + " ");
        sbb.append(
                "Address: " + userInfo.getStreet() + " " + userInfo.getCity() + ", " + userInfo.getState() + ", " + userInfo.getZip());
        sbb.append("Thank you for signing up.");
        if (view.getjChkbxText().isSelected()) {
            sbb.append(" And thank you for signing up for text.");
        }
        view.displayMessage(sbb.toString());
    }

    class MyButtonListener implements ActionListener {

        String clear = "";

        @Override

        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(view.getjBtnSave())) {

                if (view.getjRdoAdd().isSelected()) {
                    Customer userInfo = getInfo();
                    if (userInfo != null) {
                        try {
                            addCustomerLIst(userInfo);
                            createMessage(userInfo);
                        } catch (SQLException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if (view.getjRdoView().isSelected()) {
                    getCustomersList();
                }
                if (view.getjRdoUpdate().isSelected()) {
                    Customer userInfo = getInfo();
                    if (userInfo != null) {
                        try {
                            UpdateCustomer(userInfo);
                            createMessage(userInfo);
                        } catch (SQLException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            if (e.getSource().equals(view.getbtnExit())) {
                System.exit(0);
            }
            if (e.getSource().equals(view.getbtnClear())) {
                view.settxtApt(clear);
                view.settxtCity(clear);
                view.settxtComments(clear);
                view.settxtEmail(clear);
                view.settxtFirstName(clear);
                view.settxtLastName(clear);
                view.settxtPhone(clear);
                view.settxtStreet(clear);
                view.settxtZip(clear);
                view.setjChkbxText(null);
            }
            if (e.getSource().equals(view.gettxtFirstName())) {
                view.settxtFirstName(clear);
            }
        }
    }
}
