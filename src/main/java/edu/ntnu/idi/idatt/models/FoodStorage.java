package edu.ntnu.idi.idatt.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class FoodStorage {
  /** The exception message for when a grocery object does not exist. */
  private static final String NO_GROCERY_FOUND_ERROR = "Grocery not found.";
  private static final String NULL_GROCERY_ERROR = "Grocery cannot be null.";
  private static final String GROCERY_EXISTS_ERROR = "Grocery already exists in storage.";
  private static final String INVALID_NAME_ERROR = "Invalid name. Cannot be null or an empty string.";
  private static final String INVALID_CATEGORY_ERROR = "Invalid category. Cannot be null or an empty string.";
  private static final String INVALID_EXPIRATION_DATE_ERROR = "Invalid expiration date.";

  /** A map of grocery objects. The key is the name of the grocery, and the value is the grocery object. */
  private final HashMap<String, Grocery> groceries;

  /**
   * Constructs a new empty food storage.
   */
  public FoodStorage() {
    this.groceries = new HashMap<>();
  }


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
   * @throws IllegalArgumentException if the category is null or an empty string.
   */
  public List<Grocery> getGroceriesByCategory(String category) throws IllegalArgumentException {
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
   * Removes the specified object from the food storage.
   * <p>
   * If the food storage does not contain the grocery object, the method will throw an
   * {@code IllegalArgumentException}.
   *
   * @param grocery the grocery object.
   * @throws IllegalArgumentException if the given grocery object does not exist in the food
   *        storage.
   */
  public void removeGrocery(Grocery grocery) throws IllegalArgumentException {
    if (grocery == null) {
      throw new IllegalArgumentException(NULL_GROCERY_ERROR);
    }
    if (!groceries.containsKey(grocery.getName())) {
      throw new IllegalArgumentException(NO_GROCERY_FOUND_ERROR);
    }
    groceries.remove(grocery.getName());
  }

  /**
   * Removes all grocery objects from the food storage.
   */
  public void removeAllGroceries() {
    groceries.clear();
  }
}