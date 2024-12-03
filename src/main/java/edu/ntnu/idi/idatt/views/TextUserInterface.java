package edu.ntnu.idi.idatt.views;

import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.services.CookbookMenuService;
import edu.ntnu.idi.idatt.services.FoodStorageMenuService;
import edu.ntnu.idi.idatt.services.MealSuggestionsService;
import edu.ntnu.idi.idatt.services.SettingsMenuService;
import edu.ntnu.idi.idatt.utils.InterfaceUtils;

/**
 * Functions as a simple text-based user interface for a food storage system.
 * <p>
 * A food storage system is initialized, within the {@code init} method. Some example grocery items
 * are added to the food storage within the {@code start} method. Details about the grocery items
 * are then printed to the console.
 */
public class TextUserInterface {
  private FoodStorage foodStorage;
  private Cookbook cookbook;

  /**
   * Initialize the text user interface application.
   * <p>
   * This method initializes the food storage system.
   */
  public void init() {
    // Initialize the application
    this.foodStorage = new FoodStorage();
    this.cookbook = new Cookbook();

    // Add some example grocery items to the food storage system
    SettingsMenuService settingsMenuService = new SettingsMenuService(foodStorage, cookbook);
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
        case 1 -> handleFoodStorageMenu();
        case 2 -> handleCookbookMenu();
        case 3 -> handleMealSuggestionsMenu();
        case 4 -> handleSettingsMenu();
        case 0 -> finished = true;
        default -> System.out.println("Invalid choice");
      }
    }
  }

  public void handleFoodStorageMenu() {
    final FoodStorageMenuService foodStorageMenuService = new FoodStorageMenuService(foodStorage);
    boolean finished = false;

    while (!finished) {
      InterfaceUtils.promptGroceryMenu();
      final int choice = InterfaceUtils.integerInput();
      switch (choice) {
        case 1 -> foodStorageMenuService.caseAddGrocery();
        case 2 -> foodStorageMenuService.caseConsumeGrocery();
        case 3 -> foodStorageMenuService.caseFindGroceryByName();
        case 4 -> foodStorageMenuService.caseFindGroceriesByCategory();
        case 5 -> foodStorageMenuService.caseFindGroceriesExpiringOnDate();
        case 6 -> foodStorageMenuService.caseShowAllGroceries();
        case 7 -> foodStorageMenuService.caseShowAllExpiredGroceries();
        case 8 -> foodStorageMenuService.caseShowGroceriesExpiringBeforeDate();
        case 9 -> foodStorageMenuService.caseCalculateGroceriesTotalValue();
        case 0 -> finished = true;
        default -> System.out.println("Invalid choice");
      }
    }
    System.out.println("Returning to main menu");
  }

  public void handleCookbookMenu() {
    final CookbookMenuService cookbookMenuService = new CookbookMenuService(cookbook);
    boolean finished = false;
    while (!finished) {
      InterfaceUtils.promptCookbookMenu();
      final int choice = InterfaceUtils.integerInput();
      switch (choice) {
        case 1 -> cookbookMenuService.caseFindRecipeByName();
        case 2 -> cookbookMenuService.caseSearchRecipesByIngredients();
        case 3 -> cookbookMenuService.caseAddNewRecipe();
        case 4 -> cookbookMenuService.caseEditRecipe();
        case 5 -> cookbookMenuService.caseRemoveRecipe();
        case 6 -> cookbookMenuService.caseShowAllRecipes();
        case 0 -> finished = true;
        default -> System.out.println("Invalid choice");
      }
    }
    System.out.println("Returning to main menu");
  }

  public void handleMealSuggestionsMenu() {
    final MealSuggestionsService mealSuggestionsService = new MealSuggestionsService(foodStorage, cookbook);
    boolean finished = false;

    while (!finished) {
      InterfaceUtils.promptMealSUggestionsMenu();
      final int choice = InterfaceUtils.integerInput();
      switch (choice) {
        case 1 -> mealSuggestionsService.caseSuggestMealFromExpiringGroceries();
        case 2 -> mealSuggestionsService.caseSuggestMealsFromExistingGroceries();
        case 3 -> mealSuggestionsService.caseSuggestRandomMeal();
        case 0 -> finished = true;
        default -> System.out.println("Invalid choice");
      }
    }
  }

  public void handleSettingsMenu() {
    final SettingsMenuService settingsMenuService = new SettingsMenuService(foodStorage, cookbook);
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
