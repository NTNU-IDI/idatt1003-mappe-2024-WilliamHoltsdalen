package edu.ntnu.idi.idatt.services;

import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.GroceryBatch;
import java.time.LocalDate;

public class SettingsMenuService {
  final FoodStorage foodStorage;


  public SettingsMenuService(FoodStorage foodStorage) {
    this.foodStorage = foodStorage;
  }

  /**
   * Add some example grocery items to the food storage system. This method is used for testing and
   * demonstration purposes only.
   */
  public void addDemoData() {
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
  }

  public void removeAllData() {
    foodStorage.removeAllGroceries();
    System.out.println("All grocery objects removed from food storage.");
  }
}
