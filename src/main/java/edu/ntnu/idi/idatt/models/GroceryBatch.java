package edu.ntnu.idi.idatt.models;

import java.time.LocalDate;

/**
 * A class representing a grocery batch.
 * <p>
 * Provides methods for getting the amount, price per unit, and expiration date of a grocery batch.
 * Also provides a method for setting the amount of a grocery batch.
 *
 * @see Grocery
 *
 * @author WilliamHoltsdalen
 * @since 0.1
 */
public class GroceryBatch {
  private double amount;
  private final double pricePerUnit;
  private final LocalDate expirationDate;

  /**
   * Constructs a new grocery batch with the provided amount, price per unit, and expiration date.
   * <p>
   * The method validates the provided parameters and initializes the grocery batch with the
   * provided values if they are all valid. If any of the provided parameters are negative or zero,
   * or if the expiration date is null, the method throws an {@code IllegalArgumentException}.
   *
   * @param amount the amount of the grocery batch
   * @param pricePerUnit the price per unit of the grocery batch
   * @param expirationDate the expiration date of the grocery batch
   *
   * @throws IllegalArgumentException if any of the provided parameters are negative or zero, or if
   *         the expiration date is null.
   */
  public GroceryBatch(double amount, double pricePerUnit, LocalDate expirationDate)
      throws IllegalArgumentException {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount cannot be zero or a negative number.");
    }
    if (pricePerUnit < 0) {
      throw new IllegalArgumentException("Price per unit cannot be a negative number.");
    }
    if (expirationDate == null) {
      throw new IllegalArgumentException("Expiration date cannot be null.");
    }

    this.amount = amount;
    this.pricePerUnit = pricePerUnit;
    this.expirationDate = expirationDate;
  }

  /**
   * Returns the amount of the grocery batch.
   *
   * @return the amount of the grocery batch
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Returns the price per unit of the grocery batch.
   *
   * @return the price per unit of the grocery batch
   */
  public double getPricePerUnit() {
    return pricePerUnit;
  }

  /**
   * Returns the expiration date of the grocery batch.
   *
   * @return the expiration date of the grocery batch
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Sets the amount of the grocery batch.
   * <p>
   * If the new amount is less than or equal to zero, the method throws an
   * {@code IllegalArgumentException}. If the new amount is greater than the current amount of the
   * grocery batch, the method throws an {@code IllegalArgumentException}. Otherwise, it sets the
   * amount of the grocery batch to the provided value.
   *
   * @param newAmount the new amount of the grocery batch
   *
   * @throws IllegalArgumentException if the new amount is less than or equal to zero, or if the new
   *         amount is greater than the current amount of the grocery batch.
   */
  public void setAmount(double newAmount) throws IllegalArgumentException {
    if (newAmount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
    if (newAmount > this.amount) {
      throw new IllegalArgumentException(
          "New amount cannot be greater than the current amount of the grocery batch");
    }
    this.amount = newAmount;
  }
}