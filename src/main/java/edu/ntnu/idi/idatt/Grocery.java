package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A {@code Grocery} object represents a grocery item.
 * <p>
 * A grocery object has the following fields:
 * <ul>
 * <li>{@code name} A String representing the name of the grocery item. This field is immutable,
 *      set at object creation and cannot be changed afterward.
 * <li>{@code category} A String representing the category of the grocery item. This field is
 *      immutable and assigned at object creation.
 * <li>{@code totalAmount} A double representing the total quantity of the grocery item. This field is mutable
 *      and can be updated throughout the grocery item's lifecycle.
 * <li>{@code unit} A String representing the unit of measurement for the grocery item's amount.
 *      This field is immutable, set at object creation, and unmodifiable thereafter.
 * <li> {@code batches} A list of {@code GroceryBatch} objects representing the batches of the
 *      grocery item. This field is mutable and can be updated throughout the grocery
 *     item's lifecycle.
 * </ul>
 * <p>
 * Each field has an accessor method to get the value of the field.
 * The fields {@code totalAmount} and {@code batches} have mutator methods to change the values of
 * the fields.
 * <p>
 * The class constructor validates and initializes all fields. When given an invalid value
 * (such as a negative amount or price, a null or empty string, or a null expiration date), the
 * constructor will throw an {@code IllegalArgumentException}.
 */
public class Grocery {
  private final String name;
  private final String category;
  private double totalAmount;
  private final String unit;
  private final List<GroceryBatch> batches;

  /**
   * Creates a new grocery item.
   *
   * @param name specifies the name of the grocery item. Name must not equal {@code null} or an
   *             empty string.
   *
   * @param category specifies the category of the grocery item. Category must not equal
   *                 {@code null} or an empty string.
   * @param unit specifies the unit of the grocery item. Unit must not equal {@code null} or an
   *             empty string.
   * @throws IllegalArgumentException if any parameters violate the constraints specified.
   */
  public Grocery(String name, String category, String unit, GroceryBatch batch) throws IllegalArgumentException {
    // Guard clauses
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    if (category == null || category.isBlank()) {
      throw new IllegalArgumentException("Category cannot be null or blank");
    }
    if (unit == null || unit.isBlank()) {
      throw new IllegalArgumentException("Unit cannot be null or blank");
    }
    if (batch == null) {
      throw new IllegalArgumentException("Batch cannot be null");
    }

    // Assigning valid values
    this.name = name;
    this.category = category;
    this.unit = unit;
    this.batches = new ArrayList<>();
    addBatch(batch);
  }

  /**
   * @return The name of the grocery item.
   */
  public String getName() {
    return name;
  }

  /**
   * @return The category of the grocery item.
   */
  public String getCategory() {
    return category;
  }

  /**
   * @return The amount of the grocery item.
   */
  public double getTotalAmount() {
    return totalAmount;
  }

  /**
   * @return The unit of the grocery item.
   */
  public String getUnit() {
    return unit;
  }

  /**
   * @return The purchase history of the grocery item.
   */
  public List<GroceryBatch> getBatches() {
    return batches;
  }

  /**
   * Sets the amount of the grocery item.
   *
   * @param newAmount specifies the new amount of the grocery item. Amount must equal a positive
   *               number.
   *
   * @throws IllegalArgumentException if the amount equals zero or a negative number.
   */
  private void setTotalAmount(double newAmount) {
    if (newAmount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
    this.totalAmount = newAmount;
  }

  /**
   * Adds a new batch to the grocery item.
   * <p>
   * The batch is added to the batches list and the total amount of the grocery item is updated.
   * The batches list is sorted by expiration when updated.
   *
   * @param batch specifies the batch to add. Batch must not equal {@code null}.
   * @throws IllegalArgumentException if the batch is {@code null}.
   */
  public void addBatch(GroceryBatch batch) throws IllegalArgumentException {
    if (batch == null) {
      throw new IllegalArgumentException("Batch cannot be null");
    }
    batches.add(batch);
    batches.sort(Comparator.comparing(GroceryBatch::getExpirationDate));
    setTotalAmount(this.totalAmount + batch.getAmount());
  }

  /**
   *
   * @param amount specifies the amount of the grocery item to consume. Amount must equal a
   *               positive number.
   * @throws IllegalArgumentException if the amount to consume is greater than the total amount of the
   *         grocery item, or if the amount to consume is zero or a negative number.
   */
  public void consume(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
    if (amount > this.totalAmount) {
      throw new IllegalArgumentException("Amount to consume is greater than the total amount of the grocery item");
    }

    for (GroceryBatch batch : batches) {
      if (batch.getAmount() == amount) {
        batches.remove(batch);
        setTotalAmount(this.totalAmount - amount);
        return;
      } else if (batch.getAmount() < amount) {
        amount -= batch.getAmount();
        batches.remove(batch);
        setTotalAmount(this.totalAmount - batch.getAmount());
      } else {
        batch.setAmount(batch.getAmount() - amount);
        setTotalAmount(this.totalAmount - amount);
        return;
      }
    }
  }

  /**
   * Overrides the default {@code toString} method, and returns a reader-friendly string
   * representation of the grocery item.
   *
   * @return A string representation of the grocery item. Including name, category, amount, unit,
   *        expiration date, and batch history.
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder("Name: " + name + ", Category: " + category + ", Unit: "
        + unit + ", totalAmount: " + totalAmount + " " + unit);
    for (GroceryBatch batch : batches) {
      str.append("\n").append(batch.toString());
    }
    return str.toString();
  }
}
