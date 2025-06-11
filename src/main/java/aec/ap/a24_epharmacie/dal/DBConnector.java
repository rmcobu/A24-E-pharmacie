package aec.ap.a24_epharmacie.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private final String DB_HOST = "mysql-databasercb.alwaysdata.net:3306";
    private final String DB_NAME = "databasercb_pharm";
    private final String DB_USER = "389742_rcb";
    private final String DB_PASSWORD = "$389742_RCB.";
    private final String DB_URL = "jdbc:mariadb://" + DB_HOST + "/" +DB_NAME;

    private Connection connection = null;

    private static DBConnector instance = null;

    public static DBConnector getInstance() {
        if (DBConnector.instance == null)
            DBConnector.instance = new DBConnector();
        return DBConnector.instance;
    }

    private DBConnector() {

        try {
            this.connection = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}