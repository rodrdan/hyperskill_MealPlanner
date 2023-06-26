package mealplanner;

import java.util.*;

public class Meal {
    private static final Scanner keyboard = new Scanner(System.in);
    private static final List<String> categoryOptions = List.of("breakfast", "lunch", "dinner");

    /*
    private static Map<Integer, Map<String, String>> mealDatabase = new LinkedHashMap<>();
    private Map<String, String> mealInfo = new LinkedHashMap<>();
    private int id;
    private static Integer counter = 1;*/

    public Meal() {


        /*
        this.mealInfo.put("Category", addCategory());
        this.mealInfo.put("Name", addName());
        this.mealInfo.put("Ingredients", addIngredients());
        this.id = counter;
        counter++;
        mealDatabase.put(this.id, this.mealInfo);*/
    }

    private String addCategory() {
        String inputCategory;
        do {
            System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
            inputCategory = keyboard.nextLine();
        } while (!isCategoryValid(inputCategory));
        return inputCategory;
    }

    private boolean isCategoryValid(String category) {
        boolean check = categoryOptions.contains(category);
        if (!check) {
            System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
        }
        return check;
    }

    private String addName() {
        String inputName;
        do {
            System.out.println("Input the meal's name:");
            inputName = keyboard.nextLine();
        } while(!isNameValid(inputName));
        return inputName;
    }
    private boolean isNameValid(String name) {
        boolean check = true;
        if (name.isEmpty() || name.isBlank() || !name.matches("[a-zA-Z ]+")) {
            check = false;
            System.out.println("Wrong format. Use letters only!");
        }
        return check;
    }

    private String addIngredients() {
        String[] inputIngredients;
        do {
            System.out.println("Input the ingredients:");
            inputIngredients = keyboard.nextLine().split(",");
        } while (!isIngredientListValid(inputIngredients));
        return Arrays.toString(inputIngredients);
    }

    private boolean isIngredientListValid(String[] ingredients) {
        boolean check = true;
        for (String s : ingredients) {
            if (s.isEmpty() || s.isBlank() || !s.matches("[a-zA-Z ]+")) {
                check = false;
                System.out.println("Wrong format. Use letters only!");
            }
        }
        return check;
    }

    public static void printAll() {
        if (mealDatabase.isEmpty()) {
            System.out.println("No meals saved. Add a meal first.");
        } else {
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
                        System.out.println(meal.getKey() + ": " + meal.getValue());
                    }
                }
            }
        }
    }
}
