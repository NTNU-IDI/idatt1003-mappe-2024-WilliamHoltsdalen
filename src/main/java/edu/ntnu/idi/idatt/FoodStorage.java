package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * A class representing a food storage system.
 * <p>
 * The food storage contains a {@code map} of grocery objects. The class provides methods for adding
 * and removing grocery objects, adding grocery batches to existing grocery objects, and decreasing
 * the amount of a grocery object to reflect user consumption. The class also provides methods for
 * retrieving grocery objects, all grocery objects that expire before a given date, and all grocery
 * objects in the food storage.
 */
public class FoodStorage {
  /** The exception message for when a grocery object does not exist. */
  private static final String NO_GROCERY_FOUND = "Grocery not found";

  /** A map of grocery objects. */
  private final HashMap<String, Grocery> groceries = new HashMap<>();

  /**
   * Returns a list of all grocery objects in the food storage.
   *
   * @return a list of all grocery objects in the food storage.
   */
  public List<Grocery> getGroceries() {
    return new ArrayList<>(groceries.values());
  }

  /**
   * Returns a list of all grocery objects in the food storage, sorted alphabetically by name.
   * <p>
   * Objects are sorted in ascending order.
   *
   * @return a list of all grocery objects in the food storage, sorted alphabetically by name.
   */
  public List<Grocery> getAlphabeticalGroceries() {
    List<Grocery> sortedGroceries = new ArrayList<>(groceries.values());
    sortedGroceries.sort(Comparator.comparing(Grocery::getName));
    return sortedGroceries;
  }

  /**
   * Returns the grocery object with the specified name.
   *
   * @param name the name of the grocery object.
   * @return the Grocery object with the specified name.
   * @throws IllegalArgumentException if the grocery object does not exist,
   */
  public Grocery getGrocery(String name) throws IllegalArgumentException {
    if (!groceries.containsKey(name)) {
      throw new IllegalArgumentException(NO_GROCERY_FOUND);
    }
    return groceries.get(name);
  }

  /**
   * Returns a list of all grocery objects in the food storage that expire before a given date.
   * <p>
   * Does not return grocery objects that expire on the given date, only those that expire before.
   *
   * @param date the date to compare the expiration date of the grocery objects to.
   * @return a list of all grocery objects that expire before the given date.
   * @throws IllegalArgumentException if no grocery objects expire before the given date.
   */
  public List<Grocery> getExpiringGroceries(LocalDate date) throws IllegalArgumentException {
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
      return expiringGroceries;
      }
  }

  /**
   * Adds a grocery object to the food storage.
   * <p>
   * If the food storage already contains the grocery object, the method will add all the batches of
   * the new grocery object to the existing grocery object. If the food storage does not contain the
   * grocery object, the method will add it.
   *
   * @param grocery the grocery object to add.
   */
  public void addGrocery(Grocery grocery) {
    if (groceries.containsKey(grocery.getName())) {
      for (GroceryBatch batch : grocery.getBatches()) {
        addGroceryBatch(grocery.getName(), batch);
      }
      return;
    }
    groceries.put(grocery.getName(), grocery);
  }

  /**
   * Removes the specified object from the food storage.
   * <p>
   * If the food storage does not contain the grocery object, the method will throw an
   * {@code IllegalArgumentException}.
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

  /**
   * Adds a grocery batch to an existing grocery object in the food storage.
   * <p>
   * If the food storage does not contain the grocery object, the method will throw an
   * {@code IllegalArgumentException}.
   * @param name the name of the grocery object to add the batch to.
   * @param batch the grocery batch to add.
   * @throws IllegalArgumentException if the food storage does not contain the grocery object.
   */
  public void addGroceryBatch(String name, GroceryBatch batch) throws IllegalArgumentException {
    if (!groceries.containsKey(name)) {
          throw new IllegalArgumentException(NO_GROCERY_FOUND);
      }
    Grocery existingGrocery = groceries.get(name);
    existingGrocery.addBatch(batch);
  }

  /**
   * Updates the amount of a specified grocery object to reflect user consumption.
   * <p>
   * Takes a {@code grocery} object and a double {@code amount} as parameters. {@code amount}
   * cannot equal zero or a negative number. If {@code amount} equals zero, or a negative number,
   * the specified {@code amount} is less than the current amount of the grocery object, or the
   * food storage does not contain the grocery object, the method throws an
   * {@code IllegalArgumentException}. If the {@code amount} equals the amount of the grocery item,
   * the method {@link #removeGrocery} will be invoked, to remove the grocery object from the food
   * storage. Otherwise, the method {@link Grocery#consume} will be invoked to update the amount of
   * the grocery object.
   *
   * @param grocery the grocery object.
   * @param amount the amount to decrease the grocery amount by.
   * @throws IllegalArgumentException if the food storage does not contain the grocery
   *        object, current amount is less than given amount, or the given amount equals zero
   *        or a negative number.
   */
  public void consumeGrocery(Grocery grocery, double amount) throws IllegalArgumentException {
    String name = grocery.getName();
    if (!groceries.containsKey(name)) {
      throw new IllegalArgumentException(NO_GROCERY_FOUND);
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number.");
    }
    if (groceries.get(name).getTotalAmount() < amount) {
      throw new IllegalArgumentException("Amount exceeds the current amount of the grocery");
    }
    if (groceries.get(name).getTotalAmount() == amount) {
      removeGrocery(groceries.get(name));
      return;
    }
    grocery.consume(amount);
  }
}