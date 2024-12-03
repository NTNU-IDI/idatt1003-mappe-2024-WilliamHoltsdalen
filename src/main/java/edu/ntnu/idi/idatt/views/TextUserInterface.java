package edu.ntnu.idi.idatt.views;

import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.services.CookbookMenuService;
import edu.ntnu.idi.idatt.services.FoodStorageMenuService;
import edu.ntnu.idi.idatt.services.MealSuggestionsService;
import edu.ntnu.idi.idatt.services.SettingsMenuService;
import edu.ntnu.idi.idatt.utils.InterfaceUtils;
import java.time.LocalDate;

/**
 * A text-based user interface for a food storage system. This class provides the main navigation
 * structure for the user interface, including menus and sub-menus. It also handles the input and
 * output of the user interface, using methods from the {@link InterfaceUtils} class.
 *
 * <p>
 * The {@code init} method initializes an empty food storage system and a cookbook. It also sets the
 * current date of the application to the current date of the system.
 *
 * <p>
 * The {@code start} method starts the application, allowing the user to navigate through the
 * different menus and sub-menus.
 *
 * @see InterfaceUtils
 *
 * @author WilliamHoltsdalen
 * @since 0.1
 */
public class TextUserInterface {
  private static final String INVALID_CHOICE_ERROR = "Invalid choice";
  private static final String RETURNING_TO_MAIN_MENU = "Returning to main menu";

  private FoodStorage foodStorage;
  private Cookbook cookbook;
  private LocalDate currentDate;

  /**
   * Initializes the text user interface application, by creating a new food storage, cookbook, and
   * current date.
   *
   * <p>
   * If an error occurs during initialization, the method passes the error message to the
   * {@code stopByError} method, which is responsible for stopping the application and
   * printing the error message..
   */
  public void init() {
    try {
      this.foodStorage = new FoodStorage();
      this.cookbook = new Cookbook();
      this.currentDate = LocalDate.now();
    } catch (Exception e) {
      stopByError(e.getMessage());
    }
  }

  /**
   * Start the text user interface application.
   *
   * <p>
   * If an error occurs during the start process, the method passes the error message to the
   * {@code stopByError} method, which is responsible for stopping the application and
   * printing the error message.
   */
  public void start() {
    try {
      InterfaceUtils.printWelcomeMessage();
      handleMainMenu();
      InterfaceUtils.exitApplication();
    } catch (Exception e) {
      stopByError(e.getMessage());
    }
  }

  /**
   * Stops the application by printing the provided error message and exiting the application
   * smoothly.
   *
   * @param errorMessage the error message to print
   */
  private void stopByError(String errorMessage) {
    System.out.println("An error occurred: " + errorMessage);
    System.out.println("Exiting application...");
    System.exit(0);
  }

  /**
   * Handles the main menu of the application. This method prompts the user to select a menu option
   * and then calls the appropriate method based on the selected option.
   *
   * @see FoodStorageMenuService
   * @see CookbookMenuService
   * @see MealSuggestionsService
   * @see SettingsMenuService
   */
  private void handleMainMenu() {
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
        default -> System.out.println(INVALID_CHOICE_ERROR);
      }
    }
  }

  /**
   * Handles the food storage menu of the application. This method prompts the user to select a menu
   * option and then calls the appropriate method based on the selected option.
   *
   * @see FoodStorageMenuService
   */
  private void handleFoodStorageMenu() {
    final FoodStorageMenuService foodStorageMenuService = new FoodStorageMenuService(foodStorage,
        currentDate);
    boolean finished = false;

    while (!finished) {
      InterfaceUtils.promptFoodStorageMenu();
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
        case 10 -> foodStorageMenuService.caseRemoveAllExpiredGroceries();
        case 0 -> finished = true;
        default -> System.out.println(INVALID_CHOICE_ERROR);
      }
    }
    System.out.println(RETURNING_TO_MAIN_MENU);
  }

  /**
   * Handles the cookbook menu of the application. This method prompts the user to select a menu
   * option and then calls the appropriate method based on the selected option.
   *
   * @see CookbookMenuService
   */
  private void handleCookbookMenu() {
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
        default -> System.out.println(INVALID_CHOICE_ERROR);
      }
    }
    System.out.println(RETURNING_TO_MAIN_MENU);
  }

  /**
   * Handles the meal suggestions menu of the application. This method prompts the user to select a
   * menu option and then calls the appropriate method based on the selected option.
   *
   * @see MealSuggestionsService
   */
  private void handleMealSuggestionsMenu() {
    final MealSuggestionsService mealSuggestionsService = new MealSuggestionsService(foodStorage,
        cookbook);
    boolean finished = false;

    while (!finished) {
      InterfaceUtils.promptMealSuggestionsMenu();
      final int choice = InterfaceUtils.integerInput();
      switch (choice) {
        case 1 -> mealSuggestionsService.caseSuggestMealFromExpiringGroceries();
        case 2 -> mealSuggestionsService.caseSuggestMealsFromExistingGroceries();
        case 3 -> mealSuggestionsService.caseSuggestRandomMeal();
        case 0 -> finished = true;
        default -> System.out.println(INVALID_CHOICE_ERROR);
      }
    }
    System.out.println(RETURNING_TO_MAIN_MENU);
  }

  /**
   * Handles the settings menu of the application. This method prompts the user to select a menu
   * option and then calls the appropriate method based on the selected option.
   *
   * @see SettingsMenuService
   */
  private void handleSettingsMenu() {
    final SettingsMenuService settingsMenuService = new SettingsMenuService(foodStorage, cookbook,
        currentDate);
    boolean finished = false;

    while (!finished) {
      InterfaceUtils.promptSettingsMenu();
      final int choice = InterfaceUtils.integerInput();
      switch (choice) {
        case 1 -> settingsMenuService.caseAddDemoData();
        case 2 -> settingsMenuService.caseRemoveDemoData();
        case 3 -> settingsMenuService.caseShowCurrentDate();
        case 4 -> currentDate = settingsMenuService.caseGetNewDate();
        case 0 -> finished = true;
        default -> System.out.println(INVALID_CHOICE_ERROR);
      }
    }
    System.out.println(RETURNING_TO_MAIN_MENU);
  }
}
