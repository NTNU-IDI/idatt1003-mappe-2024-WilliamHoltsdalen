package edu.ntnu.idi.idatt.models;

import java.time.LocalDate;

/**
 * A class representing a grocery batch.
 *
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
  private double pricePerUnit;
  private LocalDate expirationDate;

  /**
   * Constructs a new grocery batch with the provided amount, price per unit, and expiration date.
   *
   * <p>
   * The method validates the provided parameters and initializes the grocery batch with the
   * provided values if they are all valid. If any of the provided parameters are negative or zero,
   * or if the expiration date is null, the method throws an {@code IllegalArgumentException}.
   *
   * @param amount the amount of the grocery batch
   * @param pricePerUnit the price per unit of the grocery batch
   * @param expirationDate the expiration date of the grocery batch
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

    setAmount(amount);
    setPricePerUnit(pricePerUnit);
    setExpirationDate(expirationDate);
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
   * Private method for setting the amount of the grocery batch.
   *
   * @param amount the amount of the grocery batch
   * @throws IllegalArgumentException if the amount is less than or equal to zero.
   */
  private void setAmount(double amount) throws IllegalArgumentException {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
    this.amount = amount;
  }

  /**
   * Updates the amount of the grocery batch.
   *
   * <p>
   * If the new amount is less than or equal to zero, the method throws an
   * {@code IllegalArgumentException}. If the new amount is greater than the current amount of the
   * grocery batch, the method throws an {@code IllegalArgumentException}. Otherwise it updates the
   * amount of the grocery batch to the provided value, by calling the private {@code setAmount}
   * method.
   *
   * @param newAmount the new amount of the grocery batch
   * @throws IllegalArgumentException if the new amount is less than or equal to zero, or if the new
   *         amount is greater than the current amount of the grocery batch.
   */
  public void updateAmount(double newAmount) throws IllegalArgumentException {
    if (newAmount <= 0) {
      throw new IllegalArgumentException("Amount must be a positive number");
    }
    if (newAmount > this.amount) {
      throw new IllegalArgumentException(
          "New amount cannot be greater than the current amount of the grocery batch");
    }
    setAmount(newAmount);
  }

  /**
   * Private method for setting the price per unit of the grocery batch.
   *
   * @param pricePerUnit the price per unit of the grocery batch
   * @throws IllegalArgumentException if the price per unit is less than zero.
   */
  private void setPricePerUnit(double pricePerUnit) throws IllegalArgumentException {
    if (pricePerUnit < 0) {
      throw new IllegalArgumentException("Price per unit cannot be a negative number.");
    }
    this.pricePerUnit = pricePerUnit;
  }

  /**
   * Private method for setting the expiration date of the grocery batch.
   *
   * @param expirationDate the expiration date of the grocery batch
   * @throws IllegalArgumentException if the expiration date is null.
   */
  private void setExpirationDate(LocalDate expirationDate) throws IllegalArgumentException {
    if (expirationDate == null) {
      throw new IllegalArgumentException("Expiration date cannot be null.");
    }
    this.expirationDate = expirationDate;
  }
}