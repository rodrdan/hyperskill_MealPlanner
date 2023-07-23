package mealplanner;
import java.sql.*;
import java.util.*;

public class Meal {
    private static final Scanner keyboard = new Scanner(System.in);
    /*
    private static Map<Integer, Map<String, String>> mealDatabase = new LinkedHashMap<>();
    private Map<String, String> mealInfo = new LinkedHashMap<>();
    private int id;
    private static Integer counter = 1;
     */

    public Meal() {
        createTables();
        addCategory();
        addName();
        addIngredients();
        /*
        this.mealInfo.put("Category", addCategory());
        this.mealInfo.put("Name", addName());
        this.mealInfo.put("Ingredients", addIngredients());
        this.id = counter;
        counter++;
        mealDatabase.put(this.id, this.mealInfo);*/
    }
    private void createTables() {
        try (Connection conn = DatabaseManager.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS meals (" +
                        "category VARCHAR(30)," +
                        "meal VARCHAR(100)," +
                        "meal_id SERIAL PRIMARY KEY");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS ingredients (" +
                        "ingredient (VARCHAR 50)," +
                        "ingredient_id SERIAL PRIMARY KEY (INTEGER)," +
                        "meal_id (INTEGER)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addCategory() {
        String inputCategory;
        do {
            System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
            inputCategory = keyboard.nextLine();
        } while (!Validator.isCategoryValid(inputCategory));
        try (Connection conn = DatabaseManager.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("INSERT INTO meals (category)" +
                        "VALUES (" + inputCategory + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addName() {
        String inputName;
        do {
            System.out.println("Input the meal's name:");
            inputName = keyboard.nextLine();
        } while(!Validator.isNameValid(inputName));
        try (Connection conn = DatabaseManager.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("INSERT INTO meals (meal)" +
                        "VALUES (" + inputName + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addIngredients() {
        String inputIngredients;
        do {
            System.out.println("Input the ingredients:");
            //inputIngredients = keyboard.nextLine().split(",");
            inputIngredients = keyboard.nextLine();
        } while (!Validator.isIngredientListValid(inputIngredients));
        try (Connection conn = DatabaseManager.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("INSERT INTO meals (ingredients)" +
                        "VALUES (" + inputIngredients + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void printAll() {
        try {
            ResultSet data = DatabaseManager.getData();
            if (data.next() == false) {


        }


            System.out.println("No meals saved. Add a meal first.");
        } else {



            /*
            for (var entry : mealDatabase.entrySet()) {
                Map<String, String> mealInfo = entry.getValue();
                for (var meal : mealInfo.entrySet()) {
                    if (meal.getKey().equals("Ingredients")) {
                        System.out.println(meal.getKey() + ":");
                        String ingredients = meal.getValue();
                        String[] ingredientsArray = ingredients.split(",");
                        for (String s : ingredientsArray) {
                            System.out.println(s.trim().replace("[", "").replace("]", ""));
                        }
                    } else {
                        System.out.println(meal.getKey() + ": " + meal.getValue());*/
                    }
                }
            }
        }
    }
}
