package mealplanner;
import org.postgresql.ds. PGSimpleDataSource;
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


}
