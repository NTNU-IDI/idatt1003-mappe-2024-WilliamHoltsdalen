package edu.ntnu.idi.idatt;

import java.time.LocalDate;

/**
 *
 */
public class TextUserInterface {

  /**
   *
   */
  public void init() {
    // Initialize the application
  }

  /**
   *
   */
  public void start() {
    // Start the application

    // Simple testing

    FoodStorage foodStorage = new FoodStorage();

    Grocery milk = new Grocery("Milk", "Dairy", "liters");
    Grocery apple = new Grocery("Apple", "Fruit", "pieces");
    Grocery banana = new Grocery("Banana", "Fruit", "pieces");
    Grocery grapes = new Grocery("Grapes", "Fruit", "pieces");
    Grocery salt = new Grocery("Salt", "Spices", "grams");

    milk.addBatch(new GroceryBatch(1, 5, LocalDate.now()));
    milk.addBatch(new GroceryBatch(2, 10, LocalDate.now()));
    apple.addBatch(new GroceryBatch(3, 2, LocalDate.now()));
    banana.addBatch(new GroceryBatch(10, 1, LocalDate.now()));
    grapes.addBatch(new GroceryBatch(20, 0.5, LocalDate.now()));
    salt.addBatch(new GroceryBatch(500, 0.1, LocalDate.now()));

    foodStorage.addGrocery(milk);
    foodStorage.addGrocery(apple);
    foodStorage.addGrocery(banana);
    foodStorage.addGrocery(grapes);
    foodStorage.addGrocery(salt);


    foodStorage.addGrocery(new Grocery("Milk", "Dairy", "liters"));
    foodStorage.addGroceryBatch("Apple", new GroceryBatch(4, 2, LocalDate.of(2021, 10, 10)));

    foodStorage.addGrocery(new Grocery("Salt", "Spices", "grams"));


    System.out.println("Grocery items:");
    for (Grocery grocery : foodStorage.getGroceries()) {
      System.out.println(grocery+"\n");
    }
  }
}
