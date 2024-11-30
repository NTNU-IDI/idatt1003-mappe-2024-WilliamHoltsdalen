package edu.ntnu.idi.idatt.models;

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
  private static final String NO_GROCERY_FOUND_ERROR = "Grocery not found.";
  private static final String NULL_GROCERY_ERROR = "Grocery cannot be null.";
  private static final String GROCERY_EXISTS_ERROR = "Grocery already exists in storage.";
  private static final String INVALID_NAME_ERROR = "Invalid name. Cannot be null or an empty string.";
  private static final String INVALID_CATEGORY_ERROR = "Invalid category. Cannot be null or an empty string.";
  private static final String INVALID_AMOUNT_ERROR = "Invalid amount.";
  private static final String INVALID_EXPIRATION_DATE_ERROR = "Invalid expiration date.";

  /** A map of grocery objects. */
  private final HashMap<String, Grocery> groceries = new HashMap<>();

  /**
   * Returns a list of all grocery objects in the food storage.
   *
   * @return a list of all grocery objects in the food storage.
   */
  public List<Grocery> getAllGroceries() {
    return new ArrayList<>(groceries.values());
  }

  /**
   * Returns a list of all grocery objects in the food storage, sorted alphabetically by name.
   * <p>
   * Objects are sorted in ascending order.
   *
   * @return a list of all grocery objects in the food storage, sorted alphabetically by name.
   */
  public List<Grocery> getAllGroceriesAlphabetically() {
    List<Grocery> sortedGroceries = new ArrayList<>(groceries.values());
    sortedGroceries.sort(Comparator.comparing(Grocery::getName));
    return sortedGroceries;
  }

  /**
   * Returns a list of all grocery objects in the food storage, sorted by expiration date.
   * <p>
   * Objects are sorted in ascending order.
   *
   * @return a list of all grocery objects in the food storage, sorted by expiration date.
   */
  public List<Grocery> getAllGroceriesByExpirationDate() {
    List<Grocery> sortedGroceries = new ArrayList<>(groceries.values());
    sortedGroceries.sort(Comparator.comparing(grocery -> grocery.getBatches().getFirst().getExpirationDate()));
    return sortedGroceries;
  }

  /**
   * Returns the grocery object with the specified name.
   *
   * @param name the name of the grocery object.
   * @return the Grocery object with the specified name.
   * @throws IllegalArgumentException if the grocery object does not exist,
   */
  public Grocery getGroceryByName(String name) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(INVALID_NAME_ERROR);
    }
    if (!groceries.containsKey(name)) {
      throw new IllegalArgumentException(NO_GROCERY_FOUND_ERROR);
    }
    return groceries.get(name);
  }

  /**
   * Returns a list of all grocery objects in the food storage that belong to a specified category.
   *
   * @param category the category to filter the grocery objects by.
   * @return a list of all grocery objects in the food storage that belong to the specified
   *         category.
   */
  public List<Grocery> getGroceriesByCategory(String category) {
    if (category == null || category.isBlank()) {
      throw new IllegalArgumentException(INVALID_CATEGORY_ERROR);
    }
    return groceries.values().stream()
        .filter(grocery -> grocery.getCategory().equalsIgnoreCase(category))
        .toList();
  }

  /**
   * Returns a list of all grocery objects in the food storage that expire before a given date.
   * <p>
   * Does not return grocery objects that expire on the given date, only those that expire before.
   *
   * @param date the date to compare the expiration date of the grocery objects to.
   * @return a list of all grocery objects that expire before the given date. List can be empty.
   *
   * @throws IllegalArgumentException if the given date is null.
   */
  public List<Grocery> getGroceriesExpiringBeforeDate(LocalDate date) throws IllegalArgumentException {
    if (date == null) {
      throw new IllegalArgumentException(INVALID_EXPIRATION_DATE_ERROR);
    }
    return groceries.values().stream()
        .filter(grocery -> grocery.getBatches().getFirst().getExpirationDate().isBefore(date))
        .toList();
  }

  /**
   * Returns a list of all grocery objects in the food storage that expire on a given date.
   * <p>
   * Does not return grocery objects that expire before the given date, only those that expire on
   * the given date.
   *
   * @param date the date to compare the expiration date of the grocery objects to.
   * @return a list of all grocery objects that expire on the given date. List can be empty.
   *
   * @throws IllegalArgumentException if the given date object is null.
   */
  public List<Grocery> getGroceriesExpiringOnDate(LocalDate date) throws IllegalArgumentException {
    if (date == null) {
      throw new IllegalArgumentException(INVALID_EXPIRATION_DATE_ERROR);
    }
    return groceries.values().stream()
        .filter(grocery -> grocery.getBatches().getFirst().getExpirationDate().isEqual(date))
        .toList();
  }

  /**
   * Adds a grocery object to the food storage.
   *
   * @param grocery the grocery object to add.
   * @throws IllegalArgumentException if the grocery object already exists in the food storage.
   */
  public void addGrocery(Grocery grocery) throws IllegalArgumentException {
    if (grocery == null) {
      throw new IllegalArgumentException(NULL_GROCERY_ERROR);
    }
    if (groceries.containsKey(grocery.getName())) {
      throw new IllegalArgumentException(GROCERY_EXISTS_ERROR);
    }
    groceries.put(grocery.getName(), grocery);
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
      throw new IllegalArgumentException(NO_GROCERY_FOUND_ERROR);
    }
    Grocery existingGrocery = groceries.get(name);
    existingGrocery.addBatch(batch);
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
    if (grocery == null) {
      throw new IllegalArgumentException(NULL_GROCERY_ERROR);
    }
    if (!groceries.containsKey(grocery.getName())) {
      throw new IllegalArgumentException(NO_GROCERY_FOUND_ERROR);
    }
    groceries.remove(grocery.getName());
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
      throw new IllegalArgumentException(NO_GROCERY_FOUND_ERROR);
    }
    if (amount <= 0) {
      throw new IllegalArgumentException(INVALID_AMOUNT_ERROR + "Must be a positive number.");
    }
    if (groceries.get(name).getTotalAmount() < amount) {
      throw new IllegalArgumentException(INVALID_AMOUNT_ERROR + "Exceeds the current amount of the grocery");
    }
    if (groceries.get(name).getTotalAmount() == amount) {
      removeGrocery(groceries.get(name));
      return;
    }
    grocery.consume(amount);
  }
}