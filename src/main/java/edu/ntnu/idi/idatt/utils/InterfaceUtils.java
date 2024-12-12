package edu.ntnu.idi.idatt.utils;

import edu.ntnu.idi.idatt.views.TextUserInterface;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * A utility class containing methods for interacting with the user, meant to be used in conjunction
 * with a text based user interface, like {@link TextUserInterface}.
 *
 * <p>
 * Provides methods for printing welcome and goodbye messages, handling errors, and for
 * interacting with the user through the console, such as inputting integers, doubles strings, and
 * dates. Also provides methods for printing menu and sub-menus, and for exiting the application.
 *
 * @see TextUserInterface
 *
 * @author WilliamHoltsdalen
 * @since 0.2
 */
public final class InterfaceUtils {
  private static final String TRY_AGAIN_ERROR = "Try again: ";
  /** The scanner used to read input from the console. */
  private static final Scanner scanner = new Scanner(System.in);

  /** Prevent instantiation, as this is a utility class. */
  private InterfaceUtils() {}

  /**
   * Prints the welcome message to the console.
   */
  public static void printWelcomeMessage() {
    printMenuSpacing();
    System.out.println(
              """
              |------------------------------------|
              | Welcome to the Food Storage System!|
              |------------------------------------|
              """);
  }

  /**
   * Prints the goodbye message to the console.
   */
  public static void printGoodbyeMessage() {
    printMenuSpacing();
    System.out.println(
              """
              |---------------------------------------------|
              | Thank you for using the Food Storage System!|
              |---------------------------------------------|
              """);
  }

  /**
   * Prints an error message to the console.
   *
   * @param errorMessage the error message to print
   */
  public static void printErrorMessage(String errorMessage) {
    System.out.println("Error: " + errorMessage);
  }

  /**
   * Prints a menu spacing to the console, which is two newlines.
   */
  public static void printMenuSpacing() {
    System.out.print("\n\n");
  }

  /**
   * Prints a sub-menu spacing to the console, which is one newline.
   */
  public static void printSubMenuSpacing() {
    System.out.print("\n");
  }

  /**
   * Reads an integer input from the console. If the input is not a valid integer, the method will
   * print an error message and prompt the user to try again. It will continue to prompt the user
   * until a valid integer is entered.
   *
   * @return the integer input read from the console
   */
  public static int integerInput() {
    try {
      int input = Integer.parseInt(scanner.nextLine());
      if (!ValidationUtils.isValidPositiveInteger(input)) {
        throw new IllegalArgumentException();
      }
      return input;
    } catch (Exception e) {
      printErrorMessage("Please enter a valid integer (positive whole number).");
      System.out.print(TRY_AGAIN_ERROR);
      return integerInput();
    }
  }

  /**
   * Reads a double input from the console. If the input is not a valid double, the method will
   * print an error message and prompt the user to try again. It will continue to prompt the user
   * until a valid double is entered.
   *
   * @return the double input read from the console
   */
  public static double doubleInput() {
    try {
      double input = Double.parseDouble(scanner.nextLine());
      if (!ValidationUtils.isValidPositiveDouble(input)) {
        throw new IllegalArgumentException();
      }
      return input;
    } catch (Exception e) {
      printErrorMessage("Please enter a valid number (positive).");
      System.out.print(TRY_AGAIN_ERROR);
      return doubleInput();
    }
  }

  /**
   * Reads a string input from the console. If the input is not a valid string, the method will
   * print an error message and prompt the user to try again. It will continue to prompt the user
   * until a valid string is entered.
   *
   * @return the string input read from the console
   */
  public static String stringInput() {
    try {
      String input = scanner.nextLine();
      if (!ValidationUtils.isValidString(input)) {
        throw new IllegalArgumentException();
      }
      return input.trim();
    } catch (Exception e) {
      printErrorMessage("Please enter a valid text string.");
      System.out.print(TRY_AGAIN_ERROR);
      return stringInput();
    }
  }

  /**
   * Reads a date input from the console. If the input is not a valid date, the method will
   * print an error message and prompt the user to try again. It will continue to prompt the user
   * until a valid date is entered.
   *
   * @return the date input read from the console
   */
  public static LocalDate dateInput() {
    try {
      return LocalDate.parse(scanner.nextLine());
    } catch (Exception e) {
      printErrorMessage("Please enter a valid date (yyyy-mm-dd)");
      System.out.print(TRY_AGAIN_ERROR);
      return dateInput();
    }
  }

  /**
   * Prompts the user with a main menu, allowing them to select a menu option.
   */
  public static void promptMainMenu() {
    printMenuSpacing();
    System.out.print(
              """
                       Main menu
              ---------------------------
              1. Open food storage menu
              2. Open cookbook menu
              3. Explore meal suggestions
              4. Open settings
              0. Exit application
              ---------------------------
              Your choice:\s""");
  }

  /**
   * Prompts the user with a grocery menu, allowing them to select a menu option.
   */
  public static void promptFoodStorageMenu() {
    printMenuSpacing();
    System.out.print(
              """
                                  Food storage menu
              --  --  --  --  --  --  --  --  --  --  --  --  --  --  --
              1.  Add a new grocery
              2.  Consume amount of a grocery
              3.  Find a grocery by name
              4.  Find groceries by category
              5.  Find groceries expiring on a given date
              6.  Show all groceries
              7.  Show all expired groceries
              8.  Show all groceries that expire before a given date
              9.  Calculate total value of all groceries
              10. Remove all expired grocery batches
              0.  Return to main menu
              --  --  --  --  --  --  --  --  --  --  --  --  --  --  --
              Your choice:\s""");
  }

  /**
   * Prompts the user with a cookbook menu, allowing them to select a menu option.
   */
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

  /**
   * Prompts the user with a recipe editing menu, allowing them to select a menu option.
   */
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

  /**
   * Prompts the user with a meal suggestions menu, allowing them to select a menu option.
   */
  public static void promptMealSuggestionsMenu() {
    printMenuSpacing();
    System.out.print(
            """
                           Meal suggestions menu
            --  -- --  --  --  --  --  --  --  --  --  --  --  --
            1. Suggest meals from expiring groceries
            2. Suggest meals from groceries in the food storage
            3. Suggest random meal
            0. Return to main menu
            --  -- --  --  --  --  --  --  --  --  --  --  --  --
            Your choice:\s""");
  }

  /**
   * Prompts the user with a settings menu, allowing them to select a menu option.
   */
  public static void promptSettingsMenu() {
    printMenuSpacing();
    System.out.print(
            """
                 Settings menu
            --  -- --  --  --  --  --
            1. Add demo data
            2. Remove all data
            3. Show current date
            4. Change current date
            0. Return to main menu
            --  -- --  --  --  --  --
            Your choice:\s""");
  }

  /**
   * Exits the application by closing the scanner and printing the goodbye message, and then exiting
   * the application.
   */
  public static void exitApplication() {
    scanner.close();
    printGoodbyeMessage();
    System.exit(0);
  }
}