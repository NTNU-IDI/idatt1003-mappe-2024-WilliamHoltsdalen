package edu.ntnu.idi.idatt.models;

import java.time.LocalDate;

public class GroceryBatch {
  private double amount;
  private final double pricePerUnit;
  private final LocalDate expirationDate;


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

  public double getAmount() {
    return amount;
  }

  public double getPricePerUnit() {
    return pricePerUnit;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setAmount(double newAmount) {
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