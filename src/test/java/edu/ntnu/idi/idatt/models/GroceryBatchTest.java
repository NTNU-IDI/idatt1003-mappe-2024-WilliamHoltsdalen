package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for the GroceryBatch class.
 * <p>
 * Performs the following tests:
 * <ul>
 * <li>Positive tests:
 * <ul>
 * <li>that a grocery batch object can be created with valid parameters, and that all accessor
 * methods return the correct values.
 * <li>that the amount of a grocery batch object can be updated correctly.
 * <li>that the {@code toString} method returns the correct string representation of the grocery
 * batch object.
 * </ul>
 * <li>Negative tests:
 * <ul>
 * <li>that an exception is thrown when creating a grocery batch object with:
 * <ul>
 * <li>{@code amount} equal to zero or a negative number.
 * <li>{@code pricePerUnit} equal to a negative number.
 * <li>{@code expirationDate} equal to {@code null}.
 * </ul>
 * <li>that an exception is thrown when updating the amount of a grocery batch object with:
 * <ul>
 * <li>{@code amount} equal to zero or a negative number.
 * <li>{@code amount} greater than the current amount of the grocery batch object.
 * </ul>
 * </ul>
 * </ul>
 */
@DisplayName("Test cases for the GroceryBatch class")
class GroceryBatchTest {
  // ------------------------------ Positive tests ------------------------------

  /**
   * Test creating a grocery batch object with valid parameters, and that all accessor methods
   * return the correct values.
   */
  @Test
  @DisplayName("Test creating a grocery batch object with valid parameters, and that all accessor "
      + "methods return the correct values")
  void testCreateGroceryBatch() {
    double amount = 1.0;
    double pricePerUnit = 10.0;
    LocalDate expirationDate = LocalDate.of(2022, 1, 1);

    GroceryBatch groceryBatch = new GroceryBatch(amount, pricePerUnit, expirationDate);

    assertEquals(amount, groceryBatch.getAmount());
    assertEquals(pricePerUnit, groceryBatch.getPricePerUnit());
    assertEquals(expirationDate, groceryBatch.getExpirationDate());
  }

  /**
   * Test updating the amount of a grocery batch object.
   */
  @Test
  @DisplayName("Test updating the amount of a grocery batch object")
  void testUpdateAmount() {
    double amount = 1.0;
    double pricePerUnit = 10.0;
    LocalDate expirationDate = LocalDate.of(2022, 1, 1);

    GroceryBatch groceryBatch = new GroceryBatch(amount, pricePerUnit, expirationDate);
    groceryBatch.setAmount(0.5);

    assertEquals(0.5, groceryBatch.getAmount());

  }

  /**
   * Test that the {@code toString} method returns the correct string representation of the grocery
   * batch object.
   */
  @Test
  @DisplayName("Test that the toString method returns the correct string representation of the "
      + "grocery batch object")
  void testToStringReturnsCorrectString() {
    GroceryBatch groceryBatch = new GroceryBatch(1.0, 10.0,
        LocalDate.of(2022, 1, 1));

    assertEquals("Amount: 1.0, Price per unit: 10.0, Expiration date: 2022-01-01",
        groceryBatch.toString());
  }


  // ------------------------------ Negative tests ------------------------------

  /**
   * Test that an exception is thrown when creating a grocery batch object with {@code amount} equal
   * to zero or a negative number.
   */
  @Test
  @DisplayName("Test that an exception is thrown when creating a grocery batch object with amount "
      + "equal to zero or a negative number")
  void testCreateBatchWithInvalidAmountThrows() {
    double pricePerUnit = 10.0;
    LocalDate expirationDate = LocalDate.of(2022, 1, 1);

    assertThrows(IllegalArgumentException.class, () -> new GroceryBatch(0, pricePerUnit,
        expirationDate));
    assertThrows(IllegalArgumentException.class, () -> new GroceryBatch(-1, pricePerUnit,
        expirationDate));
  }

  /**
   * Test that an exception is thrown when creating a grocery batch object with {@code pricePerUnit}
   * equal to a negative number.
   */
  @Test
  @DisplayName("Test that an exception is thrown when creating a grocery batch object with price "
      + "per unit equal to a negative number")
  void testCreateBatchWithInvalidPricePerUnitThrows() {
    double amount = 1.0;
    double pricePerUnit = -10.0;
    LocalDate expirationDate = LocalDate.of(2022, 1, 1);

    assertThrows(IllegalArgumentException.class, () -> new GroceryBatch(amount, pricePerUnit,
        expirationDate));
  }

  /**
   * Test that an exception is thrown when creating a grocery batch object with {@code expirationDate}
   * equal to {@code null}.
   */
  @Test
  @DisplayName("Test that an exception is thrown when creating a grocery batch object with expiration "
      + "date equal to null")
  void testCreateBatchWithInvalidExpirationDateThrows() {
    double amount = 1.0;
    double pricePerUnit = 10.0;
    LocalDate expirationDate = null;

    assertThrows(IllegalArgumentException.class, () -> new GroceryBatch(amount, pricePerUnit,
        expirationDate));
  }

  /**
   * Test that an exception is thrown when updating the amount of a grocery batch object with
   * {@code amount} equal to zero or a negative number.
   */
  @Test
  @DisplayName("Test that an exception is thrown when updating the amount of a grocery batch object "
      + "with amount equal to zero or a negative number")
  void testUpdateAmountWithInvalidAmountThrows() {
    double amount = 1.0;
    double pricePerUnit = 10.0;
    LocalDate expirationDate = LocalDate.of(2022, 1, 1);

    GroceryBatch groceryBatch = new GroceryBatch(amount, pricePerUnit, expirationDate);

    assertThrows(IllegalArgumentException.class, () -> groceryBatch.setAmount(0));
    assertThrows(IllegalArgumentException.class, () -> groceryBatch.setAmount(-1));
  }

  /**
   * Test that an exception is thrown when updating the amount of a grocery batch object with
   * {@code amount} greater than the current amount of the grocery batch object.
   */
  @Test
  @DisplayName("Test that an exception is thrown when updating the amount of a grocery batch object "
      + "with amount greater than the current amount of the grocery batch object")
  void testUpdateAmountWithInsufficientAmountThrows() {
    double amount = 1.0;
    double pricePerUnit = 10.0;
    LocalDate expirationDate = LocalDate.of(2022, 1, 1);

    GroceryBatch groceryBatch = new GroceryBatch(amount, pricePerUnit, expirationDate);
    groceryBatch.setAmount(2);

    assertThrows(IllegalArgumentException.class, () -> groceryBatch.setAmount(3));
  }
}