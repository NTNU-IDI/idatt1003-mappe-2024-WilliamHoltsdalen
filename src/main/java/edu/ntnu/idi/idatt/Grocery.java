package edu.ntnu.idi.idatt;

import java.util.Date;

/**
 * Represents a grocery item.
 *
 * <p>A grocery object has the following properties:</p>
 *
 * <ul>
 *   <li>name - String: A product name is textual, and String is the best datatype for storing text.</li>
 *   <li>category - String: A product category is textual, and String is the best datatype for storing text.</li>
 *   <li>amount - double - The amount of a product is numerical, often with decimals, so double is sufficient int this case.</li>
 *   <li>unit - String: A product unit is textual, and String is the best datatype for storing text.</li>
 *   <li>expirationDate - Date: An expiration date represents a point in time, and the Date class is appropriate for this.</li>
 *   <li>price - double: A product price is numerical, often with decimals, so double is sufficient in this case.</li>
 *   <li>currency - String: A currency is represented textually, and String is the best datatype for storing text.</li>
 * </ul>
 *
 * <p>Each property has an accessor method to get the value of the property.</p>
 *
 *
 */
public class Grocery {
  private final String name;
  private final String category;
  private double amount;
  private String unit;
  private final Date expirationDate;
  private final double price;
  private final String currency;

  /**
   * Creates a new grocery item.
   *
   * @param name specifies the name of the grocery item. Name must not equal {@code null} or an empty string.
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
  public Grocery(String name, String category, int amount, String unit, Date expirationDate,
                 int price, String currency) {
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
   * Returns the name of the grocery item.
   *
   * @return The name of the grocery item.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the category of the grocery item.
   *
   * @return The category of the grocery item.
   */
  public String getCategory() {
    return category;
  }

  /**
   * Returns the amount of the grocery item.
   *
   * @return The amount of the grocery item.
   */
  public double getAmount() {
    return amount;
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
   * Returns the expiration date of the grocery item.
   *
   * @return The expiration date of the grocery item.
   */
  public Date getExpirationDate() {
    return expirationDate;
  }

  /**
   * Returns the price of the grocery item.
   *
   * @return The price of the grocery item.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Returns the currency of the grocery item.
   *
   * @return The currency of the grocery item.
   */
  public String getCurrency() {
    return currency;
  }

}
