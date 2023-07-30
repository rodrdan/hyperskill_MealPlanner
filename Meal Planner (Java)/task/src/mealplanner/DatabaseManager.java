package mealplanner;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql:meals_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1111";
    private static Connection connection;
    private static ResultSet dataMeals = null;
    private static ResultSet dataIngredients = null;


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

    public static void createTables() {
        try (Connection conn = DatabaseManager.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS meals (" +
                        "category VARCHAR(30)," +
                        "meal VARCHAR(100)," +
                        "meal_id SERIAL PRIMARY KEY" +
                        ");");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS ingredients (" +
                        "ingredient VARCHAR(50)," +
                        "ingredient_id INT PRIMARY KEY," +
                        "meal_id INT" +
                        ");");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getData() {
        try (Connection conn = DatabaseManager.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                dataMeals = statement.executeQuery("SELECT * FROM meals");
                dataIngredients = statement.executeQuery("SELECT * FROM ingredients");
                System.out.println("Data retrieved.");
            } catch (SQLException e) {
                System.out.println("Data not retrieved due to an error.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Data not retrieved due to an error.");
            e.printStackTrace();
        }
    }

    public static void printAll() {
        int mealID;
        try {
            while (dataMeals.next()) {
                System.out.println("Category: " + dataMeals.getString(1));
                System.out.println("Name: " + dataMeals.getString(2));
                mealID = dataMeals.getInt(3);
                System.out.println("Ingredients: ");
                while (true) {
                    if (dataIngredients.getInt(3) == mealID) {
                        System.out.println(dataIngredients.getString(1));
                    } else {
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
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
