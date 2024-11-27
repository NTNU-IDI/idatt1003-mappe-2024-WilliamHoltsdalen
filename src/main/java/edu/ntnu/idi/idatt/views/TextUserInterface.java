package edu.ntnu.idi.idatt.views;

import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.GroceryBatch;
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
    foodStorage = new FoodStorage();
  }

  /**
   * Start the text user interface application.
   * <p>
   * This method adds some grocery items to the food storage system and prints them to the console.
   */
  public void start() {

    // Create some grocery items
    Grocery milk = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now()));
    Grocery apple = new Grocery("Apple", "Fruit", "pieces",
        new GroceryBatch(3, 2, LocalDate.now()));
    Grocery banana = new Grocery("Banana", "Fruit", "pieces",
        new GroceryBatch(10, 1, LocalDate.now()));
    Grocery grapes = new Grocery("Grapes", "Fruit", "pieces",
        new GroceryBatch(20, 0.5, LocalDate.now()));
    Grocery salt = new Grocery("Salt", "Spices", "grams",
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

    // Print the grocery items
    System.out.println("Grocery items:");
    for (Grocery grocery : foodStorage.getGroceries()) {
      System.out.println(grocery + "\n");
    }
  }
}
