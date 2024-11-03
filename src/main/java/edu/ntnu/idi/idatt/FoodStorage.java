package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FoodStorage {

  private static final String NO_GROCERY_FOUND = "Grocery not found";

  private final HashMap<String, Grocery> groceries;

  public FoodStorage() {
    this.groceries = new HashMap<>();
  }

  public List<Grocery> getGroceries() {
    return new ArrayList<>(groceries.values());
  }

  /**
   * Get a grocery object by name.
   *
   * @param name the name of the grocery object
   * @return the Grocery object with the name provided
   * @throws IllegalArgumentException if the grocery object does not exist
   */
  public Grocery getGrocery(String name) throws IllegalArgumentException {
    if (!groceries.containsKey(name)) {
      throw new IllegalArgumentException(NO_GROCERY_FOUND);
    }
    return groceries.get(name);
  }

  public void getExpiringGroceries(LocalDate date) throws IllegalArgumentException {
    List<Grocery> expiringGroceries = new ArrayList<>();
    for (Grocery grocery : groceries.values()) {
      GroceryBatch earliestExpBatch  = grocery.getBatches().getFirst();
        if (earliestExpBatch.getExpirationDate().isBefore(date)) {
          expiringGroceries.add(grocery);
          break;
        }
    }

    if (expiringGroceries.isEmpty()) {
      throw new IllegalArgumentException(NO_GROCERY_FOUND);
    }
    else {
      System.out.println("Expiring groceries:");
      for (Grocery grocery : expiringGroceries) {
        System.out.println(grocery.getName());
      }
    }
  }

  public void addGrocery(Grocery grocery) {
    // If the food storage already contains the grocery object, add a new batch to the existing
    // grocery object.
    if (groceries.containsKey(grocery.getName())) {
      for (GroceryBatch batch : grocery.getBatches()) {
        addGroceryBatch(grocery.getName(), batch);
      }
      return;
    }
    // If the food storage does not contain the grocery object, add the new grocery object.
    groceries.put(grocery.getName(), grocery);
  }

  /**
   * Removes a given grocery object from the food storage. If the food storage does not contain the
   * grocery object, the method will throw an {@code IllegalArgumentException}.
   *
   * @param grocery the grocery object.
   * @throws IllegalArgumentException if the given grocery object does not exist in the food
   *        storage.
   */
  private void removeGrocery(Grocery grocery) throws IllegalArgumentException {
    if (!groceries.containsKey(grocery.getName())) {
      throw new IllegalArgumentException(NO_GROCERY_FOUND);
    }
    groceries.remove(grocery.getName());
  }

  public void addGroceryBatch(String name, GroceryBatch batch) {
    // If the food storage does not contain the grocery object, throw an IllegalArgumentException.
    if (!groceries.containsKey(name)) {
          throw new IllegalArgumentException(NO_GROCERY_FOUND);
      }
    // Add the batch to the existing grocery object.
    Grocery existingGrocery = groceries.get(name);
    existingGrocery.addBatch(batch);
  }

  /**
   * Decrease the amount of a grocery item in the food storage.
   * <p>
   * Takes a {@code grocery} object and a double {@code amount} as parameters. {@code amount}
   * cannot equal zero or a negative number. If either of these cases occur, the specified
   * {@code amount} is less than the current amount of the grocery object, or the food storage
   * does not contain the grocery object, the method throws an {@code IllegalArgumentException}. If
   * the {@code amount} equals the amount of the grocery item the method {@link #removeGrocery}
   * will be invoked, to remove the grocery object from the food storage.
   *
   * @param grocery the grocery object.
   * @param amount the amount to amount the grocery amount by.
   * @throws IllegalArgumentException if the food storage does not contain the grocery
   *        object, current amount is less than given amount, or the given amount equals zero
   *        or a negative number.
   */
  public void consumeGrocery(Grocery grocery, double amount) throws IllegalArgumentException {
    String name = grocery.getName();
    if (!groceries.containsKey(name)) {
      throw new IllegalArgumentException(NO_GROCERY_FOUND);
    }
    if (groceries.get(name).getTotalAmount() < amount) {
      throw new IllegalArgumentException("Amount exceeds the current amount of the grocery");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number.");
    }

    // Remove the grocery object from the food storage if current amount equals the given amount.
    if (groceries.get(name).getTotalAmount() == amount) {
      removeGrocery(groceries.get(name));
    }
    // Decrease the amount of the grocery object
    grocery.consume(amount);
  }
}
