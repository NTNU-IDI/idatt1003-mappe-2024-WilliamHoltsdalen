package edu.ntnu.idi.idatt.services;

import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.InterfaceUtils;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class MealSuggestionsService {
  FoodStorage foodStorage;
  Cookbook cookbook;
  Random random;

  public MealSuggestionsService(FoodStorage foodStorage, Cookbook cookbook) {
    if (foodStorage == null) {
      throw new IllegalArgumentException("Food storage cannot be null");
    }
    if (cookbook == null) {
      throw new IllegalArgumentException("Cookbook cannot be null");
    }

    this.foodStorage = foodStorage;
    this.cookbook = cookbook;
    this.random = new Random();
  }

  public void caseSuggestMealFromExpiringGroceries() {
    System.out.println("The meal suggestions based on expiration date work as follows:");
    System.out.println("You enter a date, and recipes you can make, with groceries in the food"
        + "storage that expire *before* that date, are suggested.");

    System.out.print("Enter expiration date (yyyy-mm-dd): ");
    final LocalDate expDate = InterfaceUtils.dateInput();
    List<Grocery> expiringGroceries = foodStorage.getGroceriesExpiringBeforeDate(expDate);

    Map <Recipe, List<Grocery>> recipeToGroceriesMap = findPossibleRecipes(expiringGroceries);

    if (recipeToGroceriesMap.isEmpty()) {
      System.out.println("No meal suggestions available for the given date.");
      return;
    }

    System.out.println("Meal suggestions:");
    int suggestionNumber = 1;
    for (Map.Entry<Recipe, List<Grocery>> entry : recipeToGroceriesMap.entrySet()) {
      Recipe recipe = entry.getKey();
      List<Grocery> matchingGroceries = entry.getValue();

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

  public void caseSuggestMealsFromExistingGroceries() {
    System.out.print("Possible meals based on groceries in the food storage: ");
    List<Grocery> groceriesList = foodStorage.getAllGroceriesAlphabetically();
    Map<Recipe, List<Grocery>> recipeToGroceriesMap = findPossibleRecipes(groceriesList);

    if (recipeToGroceriesMap.isEmpty()) {
      System.out.println("No meal suggestions available for the given date.");
      return;
    }
    System.out.println("Meal suggestions:");
    int suggestionNumber = 1;
    for (Map.Entry<Recipe, List<Grocery>> entry : recipeToGroceriesMap.entrySet()) {
      Recipe recipe = entry.getKey();
      List<Grocery> matchingGroceries = entry.getValue();

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

  public void caseSuggestRandomMeal() {
    if (cookbook.getRecipes().isEmpty()) {
      System.out.println("No recipes in the cookbook.");
      return;
    }
    if (foodStorage.getAllGroceries().isEmpty()) {
      System.out.println("No groceries in the food storage.");
      return;
    }

    Map<Recipe, List<Grocery>> possibleRecipes = findPossibleRecipes(foodStorage.getAllGroceriesAlphabetically());

    if (possibleRecipes.isEmpty()) {
      System.out.println("No meal suggestions available for the groceries in the food storage.");
      return;
    }

    Object[] recipeArray = possibleRecipes.keySet().toArray();
    Recipe randomRecipe = (Recipe) recipeArray[random.nextInt(recipeArray.length)];

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

  private Map<Recipe, List<Grocery>> findPossibleRecipes(List<Grocery> groceriesList) throws IllegalArgumentException {
    if (groceriesList.isEmpty()) {
      return new HashMap<>();
    }

    Map<Recipe, List<Grocery>> recipeToGroceriesMap = cookbook.getRecipes().stream()
      .filter(recipe -> recipe.getIngredients().stream()
        .allMatch(ingredient -> {
          Grocery matchingGrocery = groceriesList.stream()
            .filter(grocery -> grocery.getName().equals(ingredient.getName()))
            .findFirst()
            .orElse(null);

          return matchingGrocery != null && matchingGrocery.getTotalAmount() >= ingredient.getAmount();
        })
    ).collect(Collectors.toMap(recipe -> recipe, recipe -> groceriesList.stream()
      .filter(grocery -> recipe.getIngredients().stream()
        .anyMatch(ingredient -> ingredient.getName().equals(grocery.getName())
          && grocery.getTotalAmount() >= ingredient.getAmount())
      )
      .sorted(Comparator.comparing(Grocery::getName))
      .sorted(Comparator.comparing(grocery -> grocery.getBatches().getFirst().getExpirationDate()))
      .toList()
      ));

    recipeToGroceriesMap.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    return recipeToGroceriesMap;
  }
}