package mealplanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    while (true) {
      System.out.println("What would you like to do (add, show, exit)?");
      String choice = keyboard.nextLine();
      switch (choice) {
        case "add":
          new Meal();
          System.out.println("The meal has been added!");
          break;
        case "show":
          try {
            DatabaseManager.createTables();
          } catch (SQLException e) {
            e.printStackTrace();
          }
          DatabaseManager.printAll();
          break;
        case "exit":
          System.out.println("Bye!");
          System.exit(0);
          break;
        default:
          break;
      }
    }
  }
}


