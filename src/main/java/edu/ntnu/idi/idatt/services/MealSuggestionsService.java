package edu.ntnu.idi.idatt.services;

import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.InterfaceUtils;
import edu.ntnu.idi.idatt.views.TextUserInterface;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * A service class allowing for a user interface, like {@link TextUserInterface}, to interact with
 * a {@link FoodStorage} and {@link Cookbook} object.
 *
 * <p>
 * Provides the following functionality:
 * <ul>
 * <li>Suggesting meals based on groceries expiring before a date
 * <li>Suggesting meals based on groceries in the food storage
 * <li>Suggesting a random meal, based on groceries in the food storage
 * </ul>
 *
 * @see TextUserInterface
 * @see FoodStorage
 * @see Cookbook
 *
 * @author WilliamHoltsdalen
 * @since 1.0
 */
public class MealSuggestionsService {
  FoodStorage foodStorage;
  Cookbook cookbook;
  Random random = new Random();

  /**
   * Constructs a new meal suggestions service with the provided food storage and cookbook.
   *
   * @param foodStorage the food storage object to use
   * @param cookbook the cookbook object to use
   * @throws IllegalArgumentException if any of the provided objects are null.
   */
  public MealSuggestionsService(FoodStorage foodStorage, Cookbook cookbook)
      throws IllegalArgumentException {
    if (foodStorage == null) {
      throw new IllegalArgumentException("Food storage cannot be null");
    }
    if (cookbook == null) {
      throw new IllegalArgumentException("Cookbook cannot be null");
    }

    this.foodStorage = foodStorage;
    this.cookbook = cookbook;
  }

  /**
   * Suggests meals based on groceries that expire before a given date.
   *
   * <p>
   * The method prompts the user to enter a date, and then suggests meals based on recipes that can
   * be made with groceries that expire before the given date. The method then prints the suggested
   * meals to the console. If no groceries expire before the given date, the method prints a
   * message indicating that no meals can be suggested.
   */
  public void caseSuggestMealFromExpiringGroceries() {
    System.out.println("The meal suggestions based on expiration date work as follows:");
    System.out.println("You enter a date, and recipes you can make, with groceries in the food"
        + "storage that expire *before* that date, are suggested.");

    System.out.print("Enter expiration date (yyyy-mm-dd): ");
    final LocalDate expDate = InterfaceUtils.dateInput();
    final List<Grocery> expiringGroceries = foodStorage.getGroceriesExpiringBeforeDate(expDate);

    final Map<Recipe, List<Grocery>> possibleRecipes = findPossibleRecipes(expiringGroceries);

    if (possibleRecipes.isEmpty()) {
      System.out.println("No meal suggestions available for the given date.");
      return;
    }

    System.out.println("Meal suggestions:");
    int suggestionNumber = 1;
    for (Map.Entry<Recipe, List<Grocery>> entry : possibleRecipes.entrySet()) {
      final Recipe recipe = entry.getKey();
      final List<Grocery> matchingGroceries = entry.getValue();

      System.out.printf("""
            -------------------------
            Meal suggestion %d:
            Recipe: %s
            Grocery batches expiring before %s:
            """, suggestionNumber++, recipe.getName(), expDate);

      matchingGroceries.forEach(grocery ->
          System.out.printf("- %s (expires: %s, amount: %.2f %s)%n",
              grocery.getName(),
              grocery.getBatches().getFirst().getExpirationDate(), // Tar den tidligste batchen
              grocery.getBatches().getFirst().getAmount(),
              grocery.getUnit())
      );
      System.out.print("\n");
    }
  }

  /**
   * Suggests meals based on existing groceries in the food storage.
   *
   * <p>
   * The method calls the {@code findPossibleRecipes} method to find recipes that can be made with
   * existing groceries in the food storage. If there are recipes that can be made with existing
   * groceries, the method prints the meal suggestions.If there are no recipes that can be made with
   * existing groceries, the method prints a message indicating that no meals can be suggested.
   */
  public void caseSuggestMealsFromExistingGroceries() {
    System.out.print("Possible meals based on groceries in the food storage: ");
    final List<Grocery> groceriesList = foodStorage.getAllGroceriesAlphabetically();
    final Map<Recipe, List<Grocery>> possibleRecipes = findPossibleRecipes(groceriesList);

    if (possibleRecipes.isEmpty()) {
      System.out.println("No meal suggestions available for the given date.");
      return;
    }
    System.out.println("Meal suggestions:");
    int suggestionNumber = 1;
    for (Map.Entry<Recipe, List<Grocery>> entry : possibleRecipes.entrySet()) {
      final Recipe recipe = entry.getKey();
      final List<Grocery> matchingGroceries = entry.getValue();

      System.out.printf("""
          -------------------------
          Meal suggestion %d:
          - - - - - - - - - - - - -
          Recipe: %s
          Ingredients:
          """, suggestionNumber++, recipe.getName());
      matchingGroceries.forEach(grocery ->
          System.out.printf("- %s (Need: %.2f %s, Amount in storage: %.2f %s)%n",
            grocery.getName(),
            recipe.getIngredient(grocery.getName()).getAmount(),
            recipe.getIngredient(grocery.getName()).getUnit(),
            grocery.getTotalAmount(),
            grocery.getUnit())
      );
      System.out.print("\n");
    }
  }

  /**
   * Suggests a random meal based on existing groceries in the food storage.
   *
   * <p>
   * The method calls the {@code findPossibleRecipes} method to find recipes that can be made with
   * existing groceries in the food storage. If there are recipes that can be made with existing
   * groceries, the method randomly selects one of the recipes and prints the meal suggestion. If
   * there are no recipes that can be made with existing groceries, the method prints a message
   * indicating that no meals can be suggested.
   */
  public void caseSuggestRandomMeal() {
    if (cookbook.getRecipes().isEmpty()) {
      System.out.println("No recipes in the cookbook.");
      return;
    }
    if (foodStorage.getAllGroceries().isEmpty()) {
      System.out.println("No groceries in the food storage.");
      return;
    }

    final Map<Recipe, List<Grocery>> possibleRecipes = findPossibleRecipes(foodStorage
        .getAllGroceriesAlphabetically());

    if (possibleRecipes.isEmpty()) {
      System.out.println("No meal suggestions available for the groceries in the food storage.");
      return;
    }

    final Object[] recipeArray = possibleRecipes.keySet().toArray();
    final Recipe randomRecipe = (Recipe) recipeArray[random.nextInt(recipeArray.length)];

    System.out.printf("""
            -------------------------
            Random meal suggestion:
            - - - - - - - - - - - - -
            Recipe: %s
            Ingredients:
            """, randomRecipe.getName());
    possibleRecipes.get(randomRecipe).forEach(grocery ->
        System.out.printf("- %s (Need: %.2f %s, Amount in storage: %.2f %s)%n",
            grocery.getName(),
            randomRecipe.getIngredient(grocery.getName()).getAmount(),
            randomRecipe.getIngredient(grocery.getName()).getUnit(),
            grocery.getTotalAmount(),
            grocery.getUnit())
    );
    System.out.print("\n");
  }

  /**
   * Finds possible recipes that can be made with the provided groceries.
   *
   * <p>
   * The method takes a list of groceries and returns a map of recipes to groceries. The map
   * contains recipes that can be made with the provided groceries, and the groceries that can be
   * used to make the recipe. The method also checks if the ingredients of the recipe are available
   * in the groceries list. If an ingredient is not available, the method skips the recipe.
   *
   * @param groceriesList the list of groceries to find recipes for
   * @return a map of recipes to groceries, or an empty map if no recipes can be made with the
   *         provided groceries, or if the provided groceries list is empty.
   */
  private Map<Recipe, List<Grocery>> findPossibleRecipes(List<Grocery> groceriesList) {
    if (groceriesList.isEmpty()) {
      return new HashMap<>();
    }

    final Map<Recipe, List<Grocery>> recipeToGroceriesMap = cookbook.getRecipes()
        .stream()
          .filter(recipe -> recipe.getIngredients().stream()
            .allMatch(ingredient -> {
              Grocery matchingGrocery = groceriesList.stream()
                  .filter(grocery -> grocery.getName().equals(ingredient.getName()))
                  .findFirst()
                  .orElse(null);

              return matchingGrocery != null
                  && matchingGrocery.getTotalAmount() >= ingredient.getAmount();
            })
          ).collect(Collectors.toMap(recipe -> recipe, recipe -> groceriesList.stream()
            .filter(grocery -> recipe.getIngredients().stream()
                .anyMatch(ingredient -> ingredient.getName().equals(grocery.getName())
                    && grocery.getTotalAmount() >= ingredient.getAmount()))
            .sorted(Comparator.comparing(Grocery::getName))
            .sorted(Comparator.comparing(grocery -> grocery.getBatches().getFirst()
                .getExpirationDate()))
            .toList()
          ));

    recipeToGroceriesMap.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    return recipeToGroceriesMap;
  }
}