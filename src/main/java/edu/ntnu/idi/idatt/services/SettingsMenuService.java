package edu.ntnu.idi.idatt.services;

import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.GroceryBatch;
import edu.ntnu.idi.idatt.models.Ingredient;
import edu.ntnu.idi.idatt.models.Recipe;
import java.time.LocalDate;

public class SettingsMenuService {
  final FoodStorage foodStorage;
  final Cookbook cookbook;


  public SettingsMenuService(FoodStorage foodStorage, Cookbook cookbook) {
    this.foodStorage = foodStorage;
    this.cookbook = cookbook;
  }

  /**
   * Add some example grocery items to the food storage system. This method is used for testing and
   * demonstration purposes only.
   */
  public void addDemoData() {

    try {
      // Create some grocery items
      final Grocery milk = new Grocery("Milk", "Dairy", "liters",
          new GroceryBatch(1, 5, LocalDate.now()));
      final Grocery apple = new Grocery("Apple", "Fruit", "pieces",
          new GroceryBatch(3, 2, LocalDate.now()));
      final Grocery banana = new Grocery("Banana", "Fruit", "pieces",
          new GroceryBatch(10, 1, LocalDate.now()));
      final Grocery grapes = new Grocery("Grapes", "Fruit", "pieces",
          new GroceryBatch(20, 0.5, LocalDate.now()));
      final Grocery salt = new Grocery("Salt", "Spices", "grams",
          new GroceryBatch(500, 0.1, LocalDate.now()));

      // Add more grocery batches to the grocery items
      milk.addBatch(new GroceryBatch(5, 5, LocalDate.now()));
      apple.addBatch(new GroceryBatch(7, 1, LocalDate.now()));
      banana.addBatch(new GroceryBatch(10, 1, LocalDate.now()));
      grapes.addBatch(new GroceryBatch(40, 1, LocalDate.now()));
      salt.addBatch(new GroceryBatch(50, 0.15, LocalDate.now()));

      // Add the grocery items to the food storage
      foodStorage.addGrocery(milk);
      foodStorage.addGrocery(apple);
      foodStorage.addGrocery(banana);
      foodStorage.addGrocery(grapes);
      foodStorage.addGrocery(salt);

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
      recipe1.addIngredient(new Ingredient("Water", "Water", "l", 1));
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

      cookbook.addRecipe(recipe1);
      cookbook.addRecipe(recipe2);
      cookbook.addRecipe(recipe3);

    } catch (IllegalArgumentException e) {
      System.out.println("Demo data could not be added.");
      return;
    }
    System.out.println("Demo data added successfully");
  }

  public void removeAllData() {
    foodStorage.removeAllGroceries();
    System.out.println("All grocery objects removed from the food storage.");
    cookbook.removeAllRecipes();
    System.out.println("All recipe objects removed from the cookbook.");

  }
}
