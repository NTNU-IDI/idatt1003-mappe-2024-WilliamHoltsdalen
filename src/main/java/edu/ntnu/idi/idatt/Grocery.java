package edu.ntnu.idi.idatt;

import java.time.LocalDate;

/**
 * A {@code Grocery} object represents a grocery item.
 * <p>
 * A grocery object has the following fields:
 * <ul>
 * <li>{@code name} A String representing the name of the grocery item. This field is immutable,
 *      set at object creation and cannot be changed afterward.
 * <li>{@code category} A String representing the category of the grocery item. This field is
 *      immutable and assigned at object creation.
 * <li>{@code amount} A double representing the quantity of the grocery item. This field is mutable
 *      and can be updated throughout the grocery item's lifecycle.
 * <li>{@code unit} A String representing the unit of measurement for the grocery item's amount.
 *      This field is immutable, set at object creation, and unmodifiable thereafter.
 * <li>{@code expirationDate} A LocalDate representing the expiration date of the grocery item.
 *      This field is immutable and does not change after object creation.
 * <li>{@code price} A double representing the price of the grocery item. This field is immutable
 *      and remains constant after creation.
 * <li>{@code currency} A String representing the currency associated with the price. This field
 *      is immutable, linked to the fixed price of the item.
 * </ul>
 * <p>
 * Each field has an accessor method to get the value of the field.
 * The field {@code amount} has a mutator method to set the value of the field.
 * <p>
 * The class constructor validates and initializes all fields. When given an invalid value
 * (such as a negative amount or price, a null or empty string, or a null expiration date), the
 * constructor will throw an {@code IllegalArgumentException}.
 */
public class Grocery {
  private final String name;
  private final String category;
  private double amount;
  private final String unit;
  private final LocalDate expirationDate;
  private final double price;
  private final String currency;

  /**
   * Creates a new grocery item.
   *
   * @param name specifies the name of the grocery item. Name must not equal {@code null} or an
   *             empty string.
   *
   * @param category specifies the category of the grocery item. Category must not equal
   *                 {@code null} or an empty string.
   * @param amount specifies the amount of the grocery item. Amount must equal a positive number.
   * @param unit specifies the unit of the grocery item. Unit must not equal {@code null} or an
   *             empty string.
   * @param expirationDate specifies the expiration date of the grocery item. Expiration date must
   *                       not equal {@code null}.
   * @param price specifies the price of the grocery item. Price must equal a positive number.
   * @param currency specifies the currency of the grocery item. Currency must not equal
   *                 {@code null} or an empty string.
   *
   * @throws IllegalArgumentException if any parameters violate the constraints specified.
   */
  public Grocery(String name, String category, double amount, String unit, LocalDate expirationDate,
                 double price, String currency) throws IllegalArgumentException {
    // Guard clauses
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank");
    }
    if (category == null || category.isBlank()) {
      throw new IllegalArgumentException("Category cannot be null or blank");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
    if (unit == null || unit.isBlank()) {
      throw new IllegalArgumentException("Unit cannot be null or blank");
    }
    if (expirationDate == null) {
      throw new IllegalArgumentException("Expiration date cannot be null");
    }
    if (price <= 0) {
      throw new IllegalArgumentException("Price must be a positive number");
    }
    if (currency == null || currency.isBlank()) {
      throw new IllegalArgumentException("Currency cannot be null or blank");
    }

    // Assigning valid values
    this.name = name;
    this.category = category;
    this.amount = amount;
    this.unit = unit;
    this.expirationDate = expirationDate;
    this.price = price;
    this.currency = currency;
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
  public double getAmount() {
    return amount;
  }

  /**
   * @return The unit of the grocery item.
   */
  public String getUnit() {
    return unit;
  }

  /**
   * @return The expiration date of the grocery item.
   */
  public LocalDate getExpDate() {
    return expirationDate;
  }

  /**
   * @return The price of the grocery item.
   */
  public double getPrice() {
    return price;
  }

  /**
   * @return The currency of the grocery item.
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets the amount of the grocery item.
   *
   * @param amount specifies the new amount of the grocery item. Amount must equal a positive
   *               number.
   *
   * @throws IllegalArgumentException if the amount violates the constraints specified.
   */
  public void setAmount(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
    this.amount = amount;
  }

  /**
   * @return A string representation of the grocery item. Including {@code name}, {@code category},
   *         {@code amount}, {@code unit}, {@code expirationDate}, {@code price}, and {@code currency}.
   */
  @Override
  public String toString() {
    return "Name: " + name + ", Category: " + category + ", Amount: "
        + amount + " " + unit + ", Price: " + price + " " + currency + ", Expiration date: "
        + expirationDate ;
  }
}
