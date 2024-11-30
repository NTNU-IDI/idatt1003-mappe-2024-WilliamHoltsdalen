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
    this.foodStorage = foodStorage;
  }

  public boolean checkIfGroceryExists(String name) {
    return foodStorage.getAllGroceries().stream()
        .anyMatch(grocery -> grocery.getName().equalsIgnoreCase(name));
  }

  public Grocery getGroceryByName(String name) {
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


    System.out.print("Enter category: ");
    final String category = InterfaceUtils.stringInput();
    System.out.print("Enter unit: ");
    final String unit = InterfaceUtils.stringInput();
    System.out.print("Enter amount: ");
    final double amount = InterfaceUtils.doubleInput();
    System.out.printf("Enter price (per %s): ", unit);
    final double pricePerUnit = InterfaceUtils.doubleInput();
    System.out.print("Enter expiration date (yyyy-mm-dd): ");
    final LocalDate expDate = InterfaceUtils.dateInput();

    foodStorage.addGrocery(new Grocery(name, category, unit, new GroceryBatch(amount, pricePerUnit, expDate)));
    System.out.println("\nGrocery added successfully!");
  }

  public void addGroceryBatch(String name) {
    final Grocery grocery = getGroceryByName(name);

    System.out.printf("Enter amount (%s): ", grocery.getUnit());
    final double amount = InterfaceUtils.doubleInput();
    System.out.print("Enter price (per " + grocery.getUnit() + "): ");
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

    try {
      grocery.consume(amount);
    } catch (IllegalArgumentException e) {
      System.out.println("Failed to consume grocery: ");
      System.out.println(e.getMessage());
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
    System.out.println("Expired groceries:");
    for (Grocery grocery : groceries) {
      System.out.println(grocery);
    }
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
