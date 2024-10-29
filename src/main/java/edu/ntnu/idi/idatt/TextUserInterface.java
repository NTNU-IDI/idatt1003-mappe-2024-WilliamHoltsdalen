package edu.ntnu.idi.idatt;

import java.util.Date;

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

    // Simple testing of the Grocery class with some examples
    Grocery milk = new Grocery("Milk", "Dairy", 1, "liters",
                                  new Date(), 20, "NOK");

    Grocery apple = new Grocery("Apple", "Fruit", 2, "pieces",
                                  new Date(), 10, "NOK");

    Grocery banana = new Grocery("Banana", "Fruit", 3, "pieces",
                                  new Date(), 15, "NOK");

    Grocery grapes = new Grocery("Grapes", "Fruit", 1.5, "kilograms",
                                  new Date(), 50, "NOK");


    System.out.println("Grocery items:");
    System.out.println(milk);
    System.out.println(apple);
    System.out.println(banana);
    System.out.println(grapes);
  }
}
