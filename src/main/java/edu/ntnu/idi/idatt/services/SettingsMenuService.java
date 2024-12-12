package edu.ntnu.idi.idatt.services;

import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.GroceryBatch;
import edu.ntnu.idi.idatt.models.Ingredient;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.InterfaceUtils;
import edu.ntnu.idi.idatt.views.TextUserInterface;
import java.time.LocalDate;

/**
 * A service class allowing for a user interface, like {@link TextUserInterface}, to interact with
 * a {@link FoodStorage} and {@link Cookbook} object.
 *
 * <p>
 * Provides the following functionality:
 * <ul>
 * <li>Adding demo data
 * <li>Removing demo data
 * <li>Showing the current date
 * <li>Changing the current date
 * </ul>
 *
 * @see TextUserInterface
 * @see FoodStorage
 * @see Cookbook
 *
 * @author WilliamHoltsdalen
 * @since 0.2
 *
 */
public class SettingsMenuService {
  private static final String FOODSTORAGE_NULL_ERROR = "Food storage cannot be null";
  private static final String COOKBOOK_NULL_ERROR = "Cookbook cannot be null";
  private static final String CURRENTDATE_NULL_ERROR = "Current date cannot be null";

  private FoodStorage foodStorage;
  private Cookbook cookbook;
  private LocalDate currentDate;

  /**
   * Constructs a new settings menu service with the provided food storage, cookbook, and current
   * date.
   *
   * @param foodStorage the food storage object to use
   * @param cookbook the cookbook object to use
   * @param currentDate the current date object to use
   * @throws IllegalArgumentException if any of the provided objects are null.
   */
  public SettingsMenuService(FoodStorage foodStorage, Cookbook cookbook, LocalDate currentDate)
      throws IllegalArgumentException {
    if (foodStorage == null) {
      throw new IllegalArgumentException(FOODSTORAGE_NULL_ERROR);
    }
    if (cookbook == null) {
      throw new IllegalArgumentException(COOKBOOK_NULL_ERROR);
    }
    if (currentDate == null) {
      throw new IllegalArgumentException(CURRENTDATE_NULL_ERROR);
    }

    setFoodStorage(foodStorage);
    setCookbook(cookbook);
    setCurrentDate(currentDate);
  }

  /**
   * Sets the food storage object to use.
   *
   * @param foodStorage the food storage object to use
   * @throws IllegalArgumentException if the provided food storage is null.
   */
  private void setFoodStorage(FoodStorage foodStorage) throws IllegalArgumentException {
    if (foodStorage == null) {
      throw new IllegalArgumentException(FOODSTORAGE_NULL_ERROR);
    }
    this.foodStorage = foodStorage;
  }

  /**
   * Sets the cookbook object to use.
   *
   * @param cookbook the cookbook object to use
   * @throws IllegalArgumentException if the provided cookbook is null.
   */
  private void setCookbook(Cookbook cookbook) throws IllegalArgumentException {
    if (cookbook == null) {
      throw new IllegalArgumentException(COOKBOOK_NULL_ERROR);
    }
    this.cookbook = cookbook;
  }

  /**
   * Sets the current date to use.
   *
   * @param currentDate the current date to use
   * @throws IllegalArgumentException if the provided current date is null.
   */
  private void setCurrentDate(LocalDate currentDate) throws IllegalArgumentException {
    if (currentDate == null) {
      throw new IllegalArgumentException(CURRENTDATE_NULL_ERROR);
    }
    this.currentDate = currentDate;
  }


  /**
   * Adds some grocery items to the food storage system. This method is primarily used for testing
   * and demonstration purposes.
   *
   * <p>
   * The method creates some grocery items, some with multiple batches with varying expiration
   * dates and some with a single batch. It then adds these grocery items to the food storage.
   * The method also adds some example recipes to the cookbook, some of which can be made using the
   * previously created grocery items.
   */
  public void caseAddDemoData() {
    try {
      // Create some grocery items
      final Grocery milk = new Grocery("Milk", "Dairy", "liters",
          new GroceryBatch(1, 5, currentDate));
      final Grocery apple = new Grocery("Apple", "Fruit", "pieces",
          new GroceryBatch(3, 2, currentDate));
      final Grocery banana = new Grocery("Banana", "Fruit", "pieces",
          new GroceryBatch(10, 1, currentDate));
      final Grocery grapes = new Grocery("Grape", "Fruit", "pieces",
          new GroceryBatch(20, 0.5, currentDate));
      final Grocery salt = new Grocery("Salt", "Spices", "tsp",
          new GroceryBatch(50, 0.1, currentDate.plusDays(1)));
      final Grocery chicken = new Grocery("Chicken", "Meat", "kg",
          new GroceryBatch(2, 145, currentDate.plusDays(1)));
      final Grocery rice = new Grocery("Rice", "Rice", "kg",
          new GroceryBatch(10, 35, currentDate.plusDays(20)));
      final Grocery soySauce = new Grocery("Soy Sauce", "Sauces", "ml",
          new GroceryBatch(250, 0.32, currentDate.plusDays(1)));
      final Grocery oil = new Grocery("Oil", "Oil", "ml",
          new GroceryBatch(100, 0.1, currentDate.plusDays(1)));
      final Grocery sugar = new Grocery("Sugar", "Spices", "grams",
          new GroceryBatch(100, 0.2, currentDate.plusDays(1)));
      final Grocery bread = new Grocery("Bread", "Bread", "pieces",
          new GroceryBatch(0.6, 49.90, currentDate.minusDays(5)));

      // Add more grocery batches to the grocery items
      milk.addBatch(new GroceryBatch(5, 5, currentDate));
      apple.addBatch(new GroceryBatch(7, 1, currentDate));
      banana.addBatch(new GroceryBatch(10, 1, currentDate));
      grapes.addBatch(new GroceryBatch(40, 1, currentDate));
      salt.addBatch(new GroceryBatch(50, 0.15, currentDate));
      chicken.addBatch(new GroceryBatch(1, 145, currentDate));
      bread.addBatch(new GroceryBatch(1, 27.49, currentDate.plusDays(4)));

      // Add the grocery items to the food storage
      foodStorage.addGrocery(milk);
      foodStorage.addGrocery(apple);
      foodStorage.addGrocery(banana);
      foodStorage.addGrocery(grapes);
      foodStorage.addGrocery(salt);
      foodStorage.addGrocery(chicken);
      foodStorage.addGrocery(rice);
      foodStorage.addGrocery(soySauce);
      foodStorage.addGrocery(oil);
      foodStorage.addGrocery(sugar);
      foodStorage.addGrocery(bread);

      final Recipe recipe1 = new Recipe(
          "Pasta Carbonara",
          "A classic Italian pasta dish",
          """
              1. Boil water in a large pot. Add salt and pasta. Cook until al dente.
              2. In a separate pan, fry bacon until crispy. Add garlic and cook until fragrant.
              3. In a bowl, whisk together eggs, cheese, and pepper.
              4. Drain pasta, reserving some of the cooking water. Add pasta to the pan with bacon.
              5. Remove pan from heat. Add egg mixture and stir quickly to coat the pasta.
              6. Add reserved cooking water as needed to create a creamy sauce.
              7. Serve immediately, garnished with more cheese and pepper.
              """,
          3);

      recipe1.addIngredient(new Ingredient("Pasta", "Pasta", "kg", 0.4));
      recipe1.addIngredient(new Ingredient("Bacon", "Meat", "kg", 0.2));
      recipe1.addIngredient(new Ingredient("Eggs", "Poultry", "pieces", 3));
      recipe1.addIngredient(new Ingredient("Cheese", "Cheese", "grams", 1));
      recipe1.addIngredient(new Ingredient("Pepper", "Spices", "tsp", 1));
      recipe1.addIngredient(new Ingredient("Water", "Water", "liters", 1));
      recipe1.addIngredient(new Ingredient("Salt", "Spices", "tsp", 1));

      final Recipe recipe2 = new Recipe(
          "Steak with Potatoes",
          "A classic British dish",
          """
              1. Preheat oven to 400°F (200°C).
              2. Season steak with salt and pepper. Heat oil in a pan over high heat.
              3. Sear steak on both sides until browned, about 3 minutes per side.
              4. Transfer steak to a baking sheet and cook in the oven until desired doneness.
              5. In the same pan, cook potatoes until golden brown.
              6. Serve steak with potatoes and your favorite sauce.
              """,
          2);

      recipe2.addIngredient(new Ingredient("Steak", "Meat", "kg", 0.6));
      recipe2.addIngredient(new Ingredient("Potatoes", "Vegetables", "kg", 0.5));
      recipe2.addIngredient(new Ingredient("Oil", "Oil", "ml", 50));
      recipe2.addIngredient(new Ingredient("Salt", "Spices", "tsp", 1));
      recipe2.addIngredient(new Ingredient("Pepper", "Spices", "tsp", 1));

      final Recipe recipe3 = new Recipe(
          "Chicken with Rice",
          "A classic Chinese dish",
          """
              1. Heat oil in a pan over high heat.
              2. Add chicken and cook until browned.
              3. Add rice and cook until done.
              4. Add soy sauce and cook until done.
              5. Serve immediately.
              """,
          2);

      recipe3.addIngredient(new Ingredient("Chicken", "Meat", "kg", 0.8));
      recipe3.addIngredient(new Ingredient("Rice", "Rice", "kg", 0.35));
      recipe3.addIngredient(new Ingredient("Oil", "Oil", "ml", 50));
      recipe3.addIngredient(new Ingredient("Soy Sauce", "Sauces", "ml", 80));

      final Recipe recipe4 = new Recipe(
          "Fruit salad",
          "A refreshing fruit salad, perfect for summer",
          """
              1. Wash and cut the fruit into bite-sized pieces.
              2. Mix the fruit with a little bit of sugar.
              3. Serve immediately.
              """,
          1);

      recipe4.addIngredient(new Ingredient("Apple", "Fruit", "pieces", 0.5));
      recipe4.addIngredient(new Ingredient("Banana", "Fruit", "pieces", 0.5));
      recipe4.addIngredient(new Ingredient("Grape", "Fruit", "pieces", 5));
      recipe4.addIngredient(new Ingredient("Sugar", "Spices", "grams", 30));

      cookbook.addRecipe(recipe1);
      cookbook.addRecipe(recipe2);
      cookbook.addRecipe(recipe3);
      cookbook.addRecipe(recipe4);

    } catch (IllegalArgumentException e) {
      System.out.println("Demo data could not be added.");
      return;
    }
    System.out.println("Demo data added successfully");
  }

  /**
   * Removes all grocery items and recipes from the food storage and cookbook.
   */
  public void caseRemoveAllData() {
    foodStorage.removeAllGroceries();
    System.out.println("All grocery objects removed from the food storage.");
    cookbook.removeAllRecipes();
    System.out.println("All recipe objects removed from the cookbook.");
  }

  /**
   * Prints the current date of the application to the console.
   */
  public void caseShowCurrentDate() {
    System.out.println("Current date: " + currentDate);
  }

  /**
   * Gets a new date from the user and returns it as a {@code LocalDate} object.
   *
   * <p>
   * The method prints a message to the console asking the user to enter a new date in the format
   * yyyy-mm-dd. It then reads the user's input.
   *
   * @return the new date as a {@code LocalDate} object
   */
  public LocalDate caseGetNewDate() {
    System.out.print("Enter a new date in the format yyyy-mm-dd: ");
    final LocalDate newDate = InterfaceUtils.dateInput();
    this.currentDate = newDate; // Update the current date object for this service class
    return newDate;
  }
}