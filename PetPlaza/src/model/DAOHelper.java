package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOHelper {

    private static final String DATABASE_URL
            = "jdbc:mysql://localhost:3306/a1_kennel";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "sesame";

    private static Connection CONN = null;

    protected Connection getConnection() throws SQLException {
        try {
            CONN = DriverManager.getConnection(DATABASE_URL,
                    USER_NAME, PASSWORD);
        } catch (ExceptionInInitializerError ex) {
            System.out.println(ex.getMessage());
        }
        return CONN;
    }

}
