package edu.ntnu.idi.idatt.services;

import edu.ntnu.idi.idatt.models.FoodStorage;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.GroceryBatch;
import edu.ntnu.idi.idatt.utils.InterfaceUtils;
import java.time.LocalDate;
import java.util.List;

public class GroceryMenuService {
  private final FoodStorage foodStorage;

  public GroceryMenuService(FoodStorage foodStorage) {
    if (foodStorage == null) {
      throw new IllegalArgumentException("Food storage cannot be null");
    }

    this.foodStorage = foodStorage;
  }

  private boolean checkIfGroceryExists(String name) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    return foodStorage.getAllGroceries().stream()
        .anyMatch(grocery -> grocery.getName().equalsIgnoreCase(name));
  }

  private Grocery getGroceryByName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    try {
      return foodStorage.getGroceryByName(name);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

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

      foodStorage.addGrocery(new Grocery(name, category, unit, new GroceryBatch(amount, pricePerUnit, expDate)));
      System.out.println("\nGrocery added successfully!");
    } catch (IllegalArgumentException e) {
      System.out.println("Failed to add grocery: ");
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void addGroceryBatch(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }

    final Grocery grocery = getGroceryByName(name);

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

    System.out.printf("Successfully consumed %.2f %s of %s!%n", amount, grocery.getUnit(), grocery.getName());
  }

  public void caseFindGroceryByName() {
    System.out.print("Enter name of grocery to find: ");
    final String name = InterfaceUtils.stringInput();
    final Grocery grocery = getGroceryByName(name);
    if (grocery == null) {
      System.out.println("Grocery called '" + name + "' not found.");
      return;
    }
    System.out.println("Grocery: ");
    System.out.println(grocery);
  }

  public void caseFindGroceriesByCategory() {
    System.out.print("Enter category of groceries to find: ");
    final String category = InterfaceUtils.stringInput();
    final List<Grocery> groceries = foodStorage.getGroceriesByCategory(category);
    if (groceries.isEmpty()) {
      System.out.println("No groceries found in category: " + category);
      return;
    }
    System.out.println("Groceries in category: " + category);
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
  }

  public void caseFindGroceriesExpiringOnDate() {
    System.out.print("Enter expiration date (yyyy-mm-dd): ");
    final LocalDate expDate = InterfaceUtils.dateInput();
    final List<Grocery> groceries = foodStorage.getGroceriesExpiringOnDate(expDate);
    if (groceries.isEmpty()) {
      System.out.println("No groceries expire on: " + expDate);
      return;
    }
    System.out.println("Groceries expiring on: " + expDate);
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
  }

  public void caseShowAllGroceries() {
    final List<Grocery> groceries = foodStorage.getAllGroceriesAlphabetically();
    if (groceries.isEmpty()) {
      System.out.println("There are no groceries in the food storage.");
      return;
    }
    System.out.println("Groceries:");
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
  }

  public void caseShowAllExpiredGroceries() {
    final List<Grocery> groceries = foodStorage.getGroceriesExpiringBeforeDate(LocalDate.now());
    if (groceries.isEmpty()) {
      System.out.println("There are no groceries that are expired in the food storage.");
      return;
    }
    double totalValue = 0;
    System.out.println("Expired groceries:");
    for (Grocery grocery : groceries) {
      System.out.println("\n" + grocery);
      for (GroceryBatch batch : grocery.getBatches()) {
        if (batch.getExpirationDate().isBefore(LocalDate.now())) {
          totalValue += batch.getAmount() * batch.getPricePerUnit();
        }
      }
    }
    System.out.printf("%nTotal value of all expired grocery batches: %.2f NOK%n", totalValue);
  }

  public void caseShowGroceriesExpiringBeforeDate() {
    System.out.print("Enter expiration date (yyyy-mm-dd): ");
    final LocalDate expDate = InterfaceUtils.dateInput();

    final List<Grocery> groceries = foodStorage.getGroceriesExpiringBeforeDate(expDate);
    if (groceries.isEmpty()) {
      System.out.println("No groceries expire before: " + expDate);
      return;
    }
    System.out.println("Groceries expiring before " + expDate + ":");
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
  }
}