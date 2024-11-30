package edu.ntnu.idi.idatt.views;

import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.GroceryBatch;
import edu.ntnu.idi.idatt.services.GroceryMenuService;
import edu.ntnu.idi.idatt.services.SettingsMenuService;
import edu.ntnu.idi.idatt.utils.InterfaceUtils;
import java.time.LocalDate;

/**
 * Functions as a simple text-based user interface for a food storage system.
 * <p>
 * A food storage system is initialized, within the {@code init} method. Some example grocery items
 * are added to the food storage within the {@code start} method. Details about the grocery items
 * are then printed to the console.
 */
public class TextUserInterface {
  private FoodStorage foodStorage;

  /**
   * Initialize the text user interface application.
   * <p>
   * This method initializes the food storage system.
   */
  public void init() {
    // Initialize the application
    this.foodStorage = new FoodStorage();

    // Add some example grocery items to the food storage system
    SettingsMenuService settingsMenuService = new SettingsMenuService(foodStorage);
    settingsMenuService.addDemoData();
  }

  /**
   * Start the text user interface application.
   * <p>
   * This method adds some grocery items to the food storage system and prints them to the console.
   */
  public void start() {

    InterfaceUtils.printWelcomeMessage();

    handleMainMenu();
    InterfaceUtils.exitApplication();
  }



  public void handleMainMenu() {
    boolean finished = false;
    while (!finished) {
      InterfaceUtils.promptMainMenu();
      final int choice = InterfaceUtils.integerInput();

      switch (choice) {
        case 1 -> handleGroceryMenu();
        case 2 -> handleCookbookMenu();
        case 3 -> System.out.println("Meal suggestions not implemented yet.");
        case 4 -> handleSettingsMenu();
        case 0 -> finished = true;
        default -> System.out.println("Invalid choice");
      }
    }
  }

  public void handleGroceryMenu() {
    final GroceryMenuService groceryMenuService = new GroceryMenuService(foodStorage);
    boolean finished = false;

    while (!finished) {
      InterfaceUtils.promptGroceryMenu();
      final int choice = InterfaceUtils.integerInput();
      switch (choice) {
        case 1 -> groceryMenuService.caseAddGrocery();
        case 2 -> groceryMenuService.caseConsumeGrocery();
        case 3 -> groceryMenuService.caseFindGroceryByName();
        case 4 -> groceryMenuService.caseFindGroceriesByCategory();
        case 5 -> groceryMenuService.caseFindGroceriesExpiringOnDate();
        case 6 -> groceryMenuService.caseShowAllGroceries();
        case 7 -> groceryMenuService.caseShowAllExpiredGroceries();
        case 8 -> groceryMenuService.caseShowGroceriesExpiringBeforeDate();
        case 0 -> finished = true;
        default -> System.out.println("Invalid choice");
      }
    }
    System.out.println("Returning to main menu");
  }

  public void handleCookbookMenu() {
    boolean finished = false;
    while (!finished) {
      InterfaceUtils.promptCookbookMenu();
      final int choice = InterfaceUtils.integerInput();
      switch (choice) {
        case 1 -> System.out.println("Search for a recipe by name feature not implemented yet.");
        case 2 -> System.out.println("Search recipes by keyword feature not implemented yet.");
        case 3 -> System.out.println("Add a new recipe feature not implemented yet.");
        case 4 -> System.out.println("Edit a recipe feature not implemented yet.");
        case 5 -> System.out.println("Remove a recipe feature not implemented yet.");
        case 6 -> System.out.println("Show all recipes feature not implemented yet.");
        case 0 -> finished = true;
        default -> System.out.println("Invalid choice");
      }
    }
    System.out.println("Returning to main menu");
  }

  public void handleSettingsMenu() {
    final SettingsMenuService settingsMenuService = new SettingsMenuService(foodStorage);
    boolean finished = false;

    while (!finished) {
      InterfaceUtils.promptSettingsMenu();
      final int choice = InterfaceUtils.integerInput();
      switch (choice) {
        case 1 -> settingsMenuService.addDemoData();
        case 2 -> settingsMenuService.removeAllData();
        case 0 -> finished = true;
        default -> System.out.println("Invalid choice");
      }
    }
    System.out.println("Returning to main menu");
  }
}
