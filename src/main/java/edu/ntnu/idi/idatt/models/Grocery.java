package edu.ntnu.idi.idatt.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class representing a grocery item.
 * <p>
 * A grocery object has the following fields:
 * <ul>
 * <li>{@code name} A String representing the name of the grocery item. This field is immutable,
 *      set at object creation and cannot be changed afterward.
 * <li>{@code category} A String representing the category of the grocery item. This field is
 *      immutable and assigned at object creation.
 * <li>{@code totalAmount} A double representing the total quantity of the grocery item.
 *      This field is mutable and can be updated throughout the grocery item's lifecycle.
 * <li>{@code unit} A String representing the unit of measurement for the grocery item's amount.
 *      This field is immutable, set at object creation, and unmodifiable thereafter.
 * <li> {@code batches} A list of {@code GroceryBatch} objects representing the batches of the
 *      grocery item. This field is mutable and can be updated throughout the grocery
 *     item's lifecycle.
 * </ul>
 * <p>
 * Each field has accessor methods to get the values. The fields {@code totalAmount} and
 * {@code batches} have mutator methods to change the values of the fields.
 * <p>
 * The class constructor validates and initializes all fields. When given an invalid value
 * (such as a negative amount or price, a null or empty string, or a null expiration date), the
 * constructor will throw an {@code IllegalArgumentException}.
 */
public class Grocery implements FoodItem{
  private final String name;
  private final String category;
  private double totalAmount;
  private final String unit;
  private final List<GroceryBatch> batches;

  /**
   * Constructs a new grocery object, only if the parameters are valid.
   *
   * @param name specifies the name of the grocery item. Name must not equal {@code null} or an
   *             empty string.
   * @param category specifies the category of the grocery item. Category must not equal
   *                 {@code null} or an empty string.
   * @param unit specifies the unit of the grocery item. Unit must not equal {@code null} or an
   *             empty string.
   * @param batch specifies the {@code GroceryBatch} object of the grocery item. Must not
   *              equal {@code null}.
   * @throws IllegalArgumentException if any parameters violate the constraints specified.
   */
  public Grocery(String name, String category, String unit, GroceryBatch batch)
      throws IllegalArgumentException {
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

    this.name = name;
    this.category = category;
    this.unit = unit;
    this.batches = new ArrayList<>();
    addBatch(batch);
  }

  /**
   * Returns the name of the grocery item.
   *
   * @return The name of the grocery item.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns the category of the grocery item.
   *
   * @return The category of the grocery item.
   */
  @Override
  public String getCategory() {
    return category;
  }

  /**
   * Returns the total amount of the grocery item.
   *
   * @return The amount of the grocery item.
   */
  public double getTotalAmount() {
    return totalAmount;
  }

  /**
   * Returns the unit of the grocery item.
   *
   * @return The unit of the grocery item.
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Returns a list containing all the grocery batches of the grocery item.
   *
   * @return A list containing of GroceryBatch objects.
   */
  public List<GroceryBatch> getBatches() {
    return batches;
  }

  /**
   * Sets the total amount of the grocery item.
   *
   * @param newAmount specifies the new amount of the grocery item. Amount must equal a positive
   *               number.
   * @throws IllegalArgumentException if the amount equals zero or a negative number.
   */
  private void setTotalAmount(double newAmount) throws IllegalArgumentException {
    if (newAmount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
    this.totalAmount = newAmount;
  }

  /**
   * Adds a new batch to the grocery item.
   * <p>
   * The GroceryBatch object is added to the list of batches, and the total amount of the grocery
   * item is updated. The list of batches is sorted by expiration date when updated.
   *
   * @param batch specifies the batch to add. Batch must not equal {@code null}.
   * @throws IllegalArgumentException if the batch is {@code null}.
   */
  public void addBatch(GroceryBatch batch) throws IllegalArgumentException {
    if (batch == null) {
      throw new IllegalArgumentException("Batch cannot be null");
    }
    batches.add(batch);
    sortBatches();
    setTotalAmount(this.totalAmount + batch.getAmount());
  }

  /**
   * Consumes a specified amount of the grocery item.
   * The method will iterate through the batches of the grocery item, and consume the amount from
   * the batches that expire first. The method will remove the batch if the amount to consume is equal to the amount of the batch. If the amount to consume is greater than
   * the amount of the batch, the method will remove the batch entirely and update the remaining
   * amount to consume for the next batch. If the amount to consume is less than the amount of the
   * batch, the method will subtract the amount from the batch.
   *
   * @param amount specifies the amount of the grocery item to consume. Amount must equal a
   *               positive number.
   * @throws IllegalArgumentException if the amount to consume is greater than the total amount of
   *         the grocery item, or if the amount to consume is zero or a negative number.
   */
  public void consume(double amount) throws IllegalArgumentException {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number.");
    }
    if (amount > this.totalAmount) {
      throw new IllegalArgumentException(
          "Amount to consume is greater than the total amount of the grocery item. \nTotal amount: "
              + this.totalAmount + " " + unit);
    }

    while (batches.iterator().hasNext()) {
      GroceryBatch batch = batches.getFirst();
      if (batch.getAmount() == amount) {
        batches.remove(batch);
        setTotalAmount(this.totalAmount - amount);
        return;
      } else if (batch.getAmount() < amount) {
        amount -= batch.getAmount();
        setTotalAmount(this.totalAmount - batch.getAmount());
        batches.remove(batch);
      } else {
        batch.setAmount(batch.getAmount() - amount);
        setTotalAmount(this.totalAmount - amount);
        return;
      }
    }
  }

  /**
   * Sorts the batches of the grocery item by expiration date, in ascending order.
   */
  public void sortBatches() {
    batches.sort(Comparator.comparing(GroceryBatch::getExpirationDate));
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