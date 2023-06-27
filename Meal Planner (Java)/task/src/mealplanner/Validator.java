package mealplanner;

import java.util.List;

public class Validator {
    private static final List<String> categoryOptions = List.of("breakfast", "lunch", "dinner");
    public static boolean isNameValid(String name) {
        boolean check = true;
        if (name.isEmpty() || name.isBlank() || !name.matches("[a-zA-Z ]+")) {
            check = false;
            System.out.println("Wrong format. Use letters only!");
        }
        return check;
    }

    public static boolean isIngredientListValid(String ingredients) {
        boolean check = true;
        if (ingredients.isEmpty() || ingredients.isBlank() || !ingredients.matches("[a-zA-Z, ]+")) {
            check = false;
            System.out.println("Wrong format. Use letters only!");
        }

        /*
        boolean check = true;
        for (String s : ingredients) {
            if (s.isEmpty() || s.isBlank() || !s.matches("[a-zA-Z ]+")) {
                check = false;
                System.out.println("Wrong format. Use letters only!");
            }
        } */
        return check;
    }

    public static boolean isCategoryValid(String category) {
        boolean check = categoryOptions.contains(category);
        if (!check) {
            System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
        }
        return check;
    }
}
