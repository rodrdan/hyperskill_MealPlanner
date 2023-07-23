package mealplanner;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql:meals_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1111";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                PGSimpleDataSource dataSource = new PGSimpleDataSource();
                dataSource.setURL(URL);
                dataSource.setUser(USER);
                dataSource.setPassword(PASSWORD);
                connection = dataSource.getConnection();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static ResultSet getData() {
        ResultSet data = null;
        try (Connection conn = DatabaseManager.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                data = statement.executeQuery("SELECT * FROM meals");
                System.out.println("Data retrieved.");
            } catch (SQLException e) {
                System.out.println("Data not retrieved due to an error.");
                e.printStackTrace();

            }
        } catch (SQLException e) {
            System.out.println("Data not retrieved due to an error.");
            e.printStackTrace();
        }
        return data;

    }

    /* public static boolean databaseIsEmpty() {
        boolean isEmpty;
        try (Connection conn = DatabaseManager.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                isEmpty = statement.execute("SELECT * FROM meals");
                return isEmpty;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } */


}
