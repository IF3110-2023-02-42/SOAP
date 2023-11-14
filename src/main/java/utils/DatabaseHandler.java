package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {
    private static DatabaseHandler instance = null;
    private Connection con = null;
    private final String DB_URL_KEY = "db.url";
    private final String DB_USER_KEY = "db.user";
    private final String DB_PASS_KEY = "db.password";

    private DatabaseHandler() {
        try {
            ConfigHandler ch = ConfigHandler.getInstance();
            String url = ch.get(DB_URL_KEY);
            String user = ch.get(DB_USER_KEY);
            String pass = ch.get(DB_PASS_KEY);
            System.out.println("Trying to connect to database at " + url + " with user " + user + " and pass " + pass);

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    public Connection getConnection() {
        System.out.println("Connection: " + this.con);
        return this.con;
    }
    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }

        return instance;
    }
    protected void finalize () throws SQLException {
        this.con.close();
    }

    public PreparedStatement prepareQuery(String query, Object... params) throws SQLException {
        try {
            Connection connection = instance.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt;
        } catch (SQLException err) {
            err.printStackTrace();
            throw err;
        }
    }
}
