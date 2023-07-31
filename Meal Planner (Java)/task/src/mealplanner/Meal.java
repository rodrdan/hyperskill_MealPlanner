package mealplanner;
import java.sql.*;
import java.util.*;

public class Meal {
    private static final Scanner keyboard = new Scanner(System.in);
    private Integer mealID;
    /*
    private static Map<Integer, Map<String, String>> mealDatabase = new LinkedHashMap<>();
    private Map<String, String> mealInfo = new LinkedHashMap<>();
    private int id;
    private static Integer counter = 1;
     */

    public Meal() {
        try {
            addCategory();
            addName(mealID);
            addIngredients(mealID);
            DatabaseManager.getData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        this.mealInfo.put("Category", addCategory());
        this.mealInfo.put("Name", addName());
        this.mealInfo.put("Ingredients", addIngredients());
        this.id = counter;
        counter++;
        mealDatabase.put(this.id, this.mealInfo);*/
    }

    private void addCategory() throws SQLException {
        String inputCategory;
        ResultSet resultSet;
        do {
            System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
            inputCategory = keyboard.nextLine();
        } while (!Validator.isCategoryValid(inputCategory));
        Connection conn = DatabaseManager.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO meals (category)" +
                "VALUES ('" + inputCategory + "')");
        resultSet = statement.executeQuery("SELECT * FROM meals");
        this.mealID = resultSet.getInt(3);
    }

    private void addName(Integer mealID) throws SQLException {
        String inputName;
        do {
            System.out.println("Input the meal's name:");
            inputName = keyboard.nextLine();
        } while(!Validator.isNameValid(inputName));
        Connection conn = DatabaseManager.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate("UPDATE meals" +
                "SET meal = " + inputName +
                "WHERE meal_id = " + mealID + ")");
    }

    private void addIngredients(Integer mealID) throws SQLException {
        String inputIngredients;
        do {
            System.out.println("Input the ingredients:");
            inputIngredients = keyboard.nextLine();
        } while (!Validator.isIngredientListValid(inputIngredients));
        String[] ingredientsArray = inputIngredients.split(",");
        Connection conn = DatabaseManager.getConnection();
        Statement statement = conn.createStatement();
        for (String s : ingredientsArray) {
            statement.executeUpdate("INSERT INTO ingredients (ingredient, meal_id)" +
                    "VALUES (" + inputIngredients + "," + mealID + ")");
        }
    }


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
