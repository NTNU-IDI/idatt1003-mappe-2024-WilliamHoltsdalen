package edu.ntnu.idi.idatt.utils;

import java.time.LocalDate;
import java.util.Scanner;

public final class InterfaceUtils {

  private static final Scanner scanner = new Scanner(System.in);

  // Prevent instantiation, as this is a utility class
  private InterfaceUtils() {}

  public static void printWelcomeMessage() {
    printMenuSpacing();
    System.out.println(
              """
              |------------------------------------|
              | Welcome to the Food Storage System!|
              |------------------------------------|
              """);
  }

  public static void printGoodbyeMessage() {
    printMenuSpacing();
    System.out.println(
              """
              |---------------------------------------------|
              | Thank you for using the Food Storage System!|
              |---------------------------------------------|
              """);
  }

  public static void printErrorMessage(String errorMessage) {
    System.out.println("Error: " + errorMessage);
  }

  public static void printMenuSpacing() {
    System.out.print("\n\n");
  }

  public static void printSubMenuSpacing() {
    System.out.print("\n");
  }

  public static int integerInput() {
    try {
      return Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException nfe) {
      printErrorMessage("Please enter a valid number");
      System.out.print("Try again: ");
      return integerInput();
    }
  }

  public static double doubleInput() {
    try {
      return Double.parseDouble(scanner.nextLine());
    } catch (NumberFormatException nfe) {
      printErrorMessage("Please enter a valid number");
      System.out.print("Try again: ");
      return doubleInput();
    }
  }

  public static String stringInput() {
    if (scanner.hasNextLine()) {
      return scanner.nextLine();
    }
    printErrorMessage("Please enter a valid string");
    System.out.print("Try again: ");
    return stringInput();
  }

  public static LocalDate dateInput() {
    try {
      return LocalDate.parse(scanner.nextLine());
    } catch (Exception e) {
      printErrorMessage("Please enter a valid date (yyyy-mm-dd)");
      System.out.print("Try again: ");
      return dateInput();
    }
  }

  public static void promptMainMenu() {
    printMenuSpacing();
    System.out.print(
              """
                       Main menu
              ---------------------------
              1. Open grocery menu
              2. Open cookbook menu
              3. Explore meal suggestions
              4. Open settings
              0. Exit application
              ---------------------------
              Your choice:\s""");
  }

  public static void promptGroceryMenu() {
    printMenuSpacing();
    System.out.print(
              """
                                     Grocery menu
              --  --  --  --  --  --  --  --  --  --  --  --  --  --  --
              1. Add a new grocery
              2. Consume amount of a grocery
              3. Find a grocery by name
              4. Find groceries by category
              5. Find groceries expiring on a given date
              6. Show all groceries
              7. Show all expired groceries
              8. Show all groceries that expire before a given date
              0. Return to main menu
              --  -- --  --  --  --  --  --  --  --  --  --  --  -- --
              Your choice:\s""");
  }

  public static void promptCookbookMenu() {
    printMenuSpacing();
    System.out.print(
              """
                         Cookbook menu
              --  --  --  --  --  --  --  --  --
              1. Find a recipe by name
              2. Find recipes by ingredients
              3. Add a new recipe
              4. Edit a recipe
              5. Remove a recipe
              6. Show all recipes
              0. Return to main menu
              --  -- --  --  --  --  --  --  --
              Your choice:\s""");
  }

  public static void promptRecipeEditMenu() {
    printSubMenuSpacing();
    System.out.print(
            """
                What would you like to edit?
            - - - - - - - - - - - - - - - - - -
            1. Edit the name
            2. Edit the description
            3. Edit the instructions
            4. Add an ingredient
            5. Edit amount of an ingredient
            6. Remove an ingredient
            7. Edit the servings
            0. Cancel
            - - - - - - - - - - - - - - - - - -
            Your choice:\s""");
  }

  public static void promptMealSUggestionsMenu() {
    printMenuSpacing();
    System.out.print(
            """
                           Meal suggestions menu
            --  -- --  --  --  --  --  --  --  --  --  --  --  --
            1. Suggest meals from expiring groceries
            2. Suggest meals from groceries in the food storage
            3. Suggest random meal
            0. Return to main menu
            --  -- --  --  --  --  --  --  --  --  --  --  -- --
            Your choice:\s""");
  }

  public static void promptSettingsMenu() {
    printMenuSpacing();
    System.out.print(
            """
                 Settings menu
            --  -- --  --  --  --  --
            1. Add demo data
            2. Remove all data
            0. Return to main menu
            --  -- --  --  --  --  --
            Your choice:\s""");
  }

  public static void exitApplication() {
    scanner.close();
    printGoodbyeMessage();
    System.exit(0);
  }
}
