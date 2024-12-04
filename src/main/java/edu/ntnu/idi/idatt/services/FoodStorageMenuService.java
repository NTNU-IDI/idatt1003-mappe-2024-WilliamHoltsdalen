package edu.ntnu.idi.idatt.services;

import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.GroceryBatch;
import edu.ntnu.idi.idatt.utils.InterfaceUtils;
import edu.ntnu.idi.idatt.views.TextUserInterface;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A service class allowing for a user interface, like {@link TextUserInterface}, to interact with
 * a {@link FoodStorage} object.
 *
 * <p>
 * Provides the following functionality:
 * <ul>
 * <li>Adding a grocery item
 * <li>Adding a grocery batch to an existing grocery item
 * <li>Consuming a grocery item
 * <li>Finding a grocery item by name
 * <li>Finding grocery items by category
 * <li>Finding grocery items expiring on a date
 * <li>Showing all grocery items
 * <li>Showing all grocery items expiring before a date
 * <li>Calculating the total value of all grocery items
 * <li>Removing all expired grocery items
 * </ul>
 *
 * @see TextUserInterface
 * @see FoodStorage
 *
 * @author WilliamHoltsdalen
 * @since 0.2
 */
public class FoodStorageMenuService {
  private static final String NAME_NULL_OR_BLANK_ERROR = "Name cannot be null or blank";

  private final FoodStorage foodStorage;
  private final LocalDate currentDate;

  /**
   * Constructs a new food storage menu service with the provided food storage and current date.
   *
   * @param foodStorage the food storage object to use
   * @param currentDate the current date object to use
   * @throws IllegalArgumentException if any of the provided objects are null.
   */
  public FoodStorageMenuService(FoodStorage foodStorage, LocalDate currentDate)
      throws IllegalArgumentException {
    if (foodStorage == null) {
      throw new IllegalArgumentException("Food storage cannot be null");
    }
    if (currentDate == null) {
      throw new IllegalArgumentException("Current date cannot be null");
    }

    this.foodStorage = foodStorage;
    this.currentDate = currentDate;
  }

  /**
   * Checks if a grocery with the provided name exists in the food storage.
   *
   * @param name the name of the grocery to check
   * @return true if a grocery with the provided name exists in the food storage, false otherwise
   */
  private boolean checkIfGroceryExists(String name) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NAME_NULL_OR_BLANK_ERROR);
    }
    return foodStorage.getAllGroceries().stream()
        .anyMatch(grocery -> grocery.getName().equalsIgnoreCase(name));
  }

  /**
   * Gets a grocery with the provided name from the food storage.
   *
   * @param name the name of the grocery to get
   * @return the grocery with the provided name, or null if no grocery with the provided name
   *         exists in the food storage
   * @throws IllegalArgumentException if the name is null or an empty string.
   */
  private Grocery getGroceryByName(String name) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NAME_NULL_OR_BLANK_ERROR);
    }
    try {
      return foodStorage.getGroceryByName(name);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  /**
   * Adds a grocery to the food storage.
   *
   * <p>
   * The method prompts the user to enter the name of the grocery. If the grocery already exists in
   * the food storage, the method prompts the user to add a batch to the grocery instead and calls
   * the {@code addGroceryBatch} method. If the grocery does not exist in the food storage, the
   * method prompts the user to enter the category, unit, amount, price per unit, and expiration
   * date of the grocery. The method then adds the grocery to the food storage.
   */
  public void caseAddGrocery() {
    System.out.print("Enter name: ");
    final String name = InterfaceUtils.stringInput();

    if (checkIfGroceryExists(name)) {
      System.out.println("Grocery already exists. Adding batch instead.");
      addGroceryBatch(name);
      return;
    }

    try {
      System.out.print("Enter category: ");
      final String category = InterfaceUtils.stringInput();
      System.out.print("Enter unit: ");
      final String unit = InterfaceUtils.stringInput();
      System.out.print("Enter amount: ");
      final double amount = InterfaceUtils.doubleInput();
      System.out.printf("Enter price (per %s) in NOK: ", unit);
      final double pricePerUnit = InterfaceUtils.doubleInput();
      System.out.print("Enter expiration date (yyyy-mm-dd): ");
      final LocalDate expDate = InterfaceUtils.dateInput();

      foodStorage.addGrocery(new Grocery(name, category, unit,
          new GroceryBatch(amount, pricePerUnit, expDate)));
      System.out.println("\nGrocery added successfully!");
    } catch (IllegalArgumentException e) {
      System.out.println("Failed to add grocery: ");
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * Adds a batch to a grocery in the food storage.
   *
   * <p>
   * The method prompts the user to enter the amount, price per unit, and expiration date of the
   * batch. The method then adds the batch to the grocery in the food storage with the provided
   * name.
   *
   * @param name the name of the grocery to add the batch to
   * @throws IllegalArgumentException if the name is null or an empty string, or if the grocery
   *         does not exist in the food storage.
   */
  public void addGroceryBatch(String name) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NAME_NULL_OR_BLANK_ERROR);
    }

    final Grocery grocery = getGroceryByName(name);
    if (grocery == null) {
      throw new IllegalArgumentException("Grocery not found");
    }

    System.out.printf("Enter amount (%s): ", grocery.getUnit());
    final double amount = InterfaceUtils.doubleInput();
    System.out.print("Enter price (per " + grocery.getUnit() + ") in NOK: ");
    final double pricePerUnit = InterfaceUtils.doubleInput();
    System.out.print("Enter expiration date (yyyy-mm-dd): ");
    final LocalDate expDate = InterfaceUtils.dateInput();
    try {
      grocery.addBatch(new GroceryBatch(amount, pricePerUnit, expDate));
    } catch (IllegalArgumentException e) {
      System.out.println("Failed to add batch: ");
      System.out.println(e.getMessage());
      return;
    }
    System.out.println("\nBatch added successfully!");
  }

  /**
   * Consumes a grocery in the food storage.
   *
   * <p>
   * The method prompts the user to enter the name of the grocery to consume an amount of. If the
   * grocery does not exist in the food storage, the method prints an error message and returns.
   * If the amount to consume is equal to the total amount of the grocery, the method removes the
   * grocery entirely from the food storage. If the amount to consume is less than the total amount
   * of the grocery, the method calls the {@code consume} method of the grocery object to update
   * the amount of the grocery. When the method is finished, it prints a success message.
   */
  public void caseConsumeGrocery() {
    System.out.print("Enter name of grocery to consume: ");
    final String name = InterfaceUtils.stringInput();

    final Grocery grocery = getGroceryByName(name);
    if (grocery == null) {
      System.out.println("Grocery called '" + name + "' not found.");
      return;
    }

    System.out.printf("Current amount: %.2f %s%n", grocery.getTotalAmount(), grocery.getUnit());
    System.out.printf("Enter amount to consume (%s): ", grocery.getUnit());
    final double amount = InterfaceUtils.doubleInput();
    if (amount == grocery.getTotalAmount()) {
      foodStorage.removeGrocery(grocery);
      System.out.println("Successfully removed grocery.");
      return;
    }

    try {
      grocery.consume(amount);
    } catch (IllegalArgumentException e) {
      System.out.println("Failed to consume grocery: ");
      System.out.println("Error: " + e.getMessage());
      return;
    }
    System.out.printf("Successfully consumed %.2f %s of %s!%n", amount, grocery.getUnit(),
        grocery.getName());
  }

  /**
   * Finds a grocery by name in the food storage, and prints it's details to the console.
   *
   * <p>
   * The method prompts the user to enter the name of the grocery to find. If the grocery is found,
   * the method prints the grocery to the console. If the grocery is not found, the method prints
   * an error message.
   */
  public void caseFindGroceryByName() {
    System.out.print("Enter name of grocery to find: ");
    final String name = InterfaceUtils.stringInput();
    final Grocery grocery = getGroceryByName(name);
    if (grocery == null) {
      System.out.println("Grocery called '" + name + "' not found.");
      return;
    }
    System.out.println("Grocery:\n");
    System.out.println(grocery);
  }

  /**
   * Finds groceries by category in the food storage, and prints them to the console.
   *
   * <p>
   * The method prompts the user to enter the category of groceries to find. If no groceries are
   * found in the category, the method prints an error message. Otherwise, the method prints the
   * groceries to the console.
   */
  public void caseFindGroceriesByCategory() {
    System.out.print("Enter category of groceries to find: ");
    final String category = InterfaceUtils.stringInput();
    final List<Grocery> groceries = foodStorage.getGroceriesByCategory(category);
    if (groceries.isEmpty()) {
      System.out.println("No groceries found in category: " + category);
      return;
    }
    System.out.printf("%nGroceries in category: %s%n%n", category);
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
  }

  /**
   * Finds groceries expiring on a given date in the food storage, and prints them to the console.
   *
   * <p>
   * The method prompts the user to enter the expiration date of the groceries to find. If no
   * groceries are found on the given date, the method prints an error message. Otherwise, the
   * method prints the groceries to the console.
   */
  public void caseFindGroceriesExpiringOnDate() {
    System.out.print("Enter expiration date (yyyy-mm-dd): ");
    final LocalDate expDate = InterfaceUtils.dateInput();
    final List<Grocery> groceries = foodStorage.getGroceriesExpiringOnDate(expDate);
    if (groceries.isEmpty()) {
      System.out.println("No groceries expire on: " + expDate);
      return;
    }
    System.out.printf("%nGroceries expiring on: %s%n%n", expDate);
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
  }

  /**
   * Shows all groceries in the food storage, and prints them to the console.
   *
   * <p>
   * The method prints all groceries in the food storage to the console. If there are no groceries
   * in the food storage, the method prints an error message.
   */
  public void caseShowAllGroceries() {
    final List<Grocery> groceries = foodStorage.getAllGroceriesAlphabetically();
    if (groceries.isEmpty()) {
      System.out.println("There are no groceries in the food storage.");
      return;
    }

    System.out.println("All groceries in the food storage:\n");
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
  }

  /**
   * Shows all expired groceries in the food storage, and prints them to the console.
   *
   * <p>
   * The method prints all expired groceries in the food storage to the console. If there are no
   * expired groceries in the food storage, the method prints an error message.
   */
  public void caseShowAllExpiredGroceries() {
    final List<Grocery> groceries = foodStorage.getGroceriesExpiringBeforeDate(currentDate);
    if (groceries.isEmpty()) {
      System.out.println("There are no expired groceries in the food storage.");
      return;
    }
    double totalValue = 0;
    System.out.println("Expired groceries:\n");
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
      for (GroceryBatch batch : grocery.getBatches()) {
        if (batch.getExpirationDate().isBefore(currentDate)) {
          totalValue += batch.getAmount() * batch.getPricePerUnit();
        }
      }
    }
    System.out.printf("%nTotal value of all expired grocery batches: %.2f NOK%n", totalValue);
  }

  /**
   * Shows groceries expiring before a given date in the food storage, and prints them to the
   * console.
   *
   * <p>
   * The method prompts the user to enter the expiration date of the groceries to find. If no
   * groceries are found that expire before the given date, the method prints an error message.
   * Otherwise, the method prints the groceries to the console.
   */
  public void caseShowGroceriesExpiringBeforeDate() {
    System.out.print("Enter expiration date (yyyy-mm-dd): ");
    final LocalDate expDate = InterfaceUtils.dateInput();

    final List<Grocery> groceries = foodStorage.getGroceriesExpiringBeforeDate(expDate);
    if (groceries.isEmpty()) {
      System.out.println("No groceries expire before: " + expDate);
      return;
    }
    System.out.println("Groceries expiring before " + expDate + ":\n");
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
  }

  /**
   * Calculates the total value of all groceries in the food storage, and prints it to the console.
   *
   * <p>
   * The method calculates the total value of all groceries in the food storage by iterating
   * through each grocery and batch in the food storage. It then adds the amount and price per unit
   * of each batch to the total value. The method prints the total value to the console.
   */
  public void caseCalculateGroceriesTotalValue() {
    double totalValue = 0;
    for (Grocery grocery : foodStorage.getAllGroceries()) {
      for (GroceryBatch batch : grocery.getBatches()) {
        totalValue += batch.getAmount() * batch.getPricePerUnit();
      }
    }

    System.out.printf("Total value of all groceries: %.2f NOK%n", totalValue);
  }

  /**
   * Removes all expired groceries from the food storage.
   *
   * <p>
   * The method iterates through all expired groceries in the food storage, and their batches,
   * and removes the batches of each grocery that have expired. If the grocery batch is expired on
   * the current date, the method consumes the whole amount of the grocery batch. If the grocery
   * batch is not expired, the method does not remove the grocery batch.
   */
  public void caseRemoveAllExpiredGroceries() {
    // Copying the list to allow for modification during iteration.
    final List<Grocery> expiredGroceriesForIteration = new ArrayList<>(
        foodStorage.getGroceriesExpiringBeforeDate(currentDate));
    if (expiredGroceriesForIteration.isEmpty()) {
      System.out.println("There are no expired groceries in the food storage.");
      return;
    }

    for (Grocery grocery : expiredGroceriesForIteration) {
      // Copying the list to allow for modification during iteration.
      final List<GroceryBatch> batchesForIteration = new ArrayList<>(grocery.getBatches());

      for (GroceryBatch batch : batchesForIteration) {
        if (batch.getExpirationDate().isBefore(currentDate)) {
          if (grocery.getBatches().size() == 1) {
            foodStorage.removeGrocery(grocery);
            System.out.printf("Removed %s, expired on %s%n", grocery.getName(),
                batch.getExpirationDate());
            break;
          }
          grocery.consume(batch.getAmount());
          System.out.printf("Removed batch from %s, expired on %s%n",
              grocery.getName(), batch.getExpirationDate());
        }
      }
    }
    System.out.println("All expired grocery batches removed from the food storage.");
  }
}