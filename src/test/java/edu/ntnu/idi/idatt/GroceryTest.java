package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.GroceryBatch;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Grocery class.
 * <p>
 * Performs the following tests:
 * <ul>
 * <li>Positive tests:
 * <ul>
 * <li>that a grocery item can be created with valid values.
 * <li>that the accessor methods for all fields return the correct values.
 * <li>that the method {@code addBatch} adds a batch to the grocery item.
 * <li>that the method {@code consume} updates the amount of the grocery item correctly.
 * <li>that the method {@code toString} returns the correct string representation.
 * </ul>
 * <li>Negative tests:
 * <ul>
 * <li>that an exception is thrown when creating a grocery item with:
 * <ul>
 * <li>{@code name} that equals {@code null} or an empty string.
 * <li>{@code category} that equals {@code null} or an empty string.
 * <li>{@code unit} that equals {@code null} or an empty string.
 * <li>{@code batch} that equals {@code null}.
 * </ul>
 * <li>that an exception is thrown when adding a GroceryBatch object that equals {@code null}.
 * <li>that an exception is thrown when trying to consume an amount of the grocery item that equals
 *     zero or a negative number.
 * <li>that an exception is thrown when trying to consume an amount greater than the total amount
 *     of the grocery item.
 * </ul>
 * </ul>
 */
class GroceryTest {
  // ------------------------------ Positive tests ------------------------------

  /**
   * Test creating a grocery object with valid parameters, and ensure accessor methods for all
   * fields return the correct values.
   */
  @Test
  void testCreateGrocery() {
    GroceryBatch batch = new GroceryBatch(1.0, 20.0, LocalDate.now());
    Grocery grocery = new Grocery("Milk", "Dairy", "liters", batch);

    List<GroceryBatch> batches = new ArrayList<>();
    batches.add(batch);
    // Assert
    assertEquals("Milk", grocery.getName());
    assertEquals("Dairy", grocery.getCategory());
    assertEquals(1.0, grocery.getTotalAmount());
    assertEquals("liters", grocery.getUnit());
    assertEquals(batches, grocery.getBatches());

  }

  /**
   * Ensure that the method {@code consume} sets the correct amount of the grocery item.
   */
  @Test
  void testConsume() {
    Grocery grocery = new Grocery("Milk", "Dairy", "liters", new GroceryBatch(3, 20, LocalDate.now()));
    grocery.consume(2);
    assertEquals(1, grocery.getTotalAmount());
  }

  /**
   * Ensure that the method {@code toString} returns the correct string representation.
   */
  @Test
  void testToString() {
    Grocery grocery = new Grocery("Milk", "Dairy", "liters", new GroceryBatch(3, 20, LocalDate.now()));


    assertEquals("Name: Milk, Category: Dairy, Unit: liters, totalAmount: 3.0 liters" +
                  "\nAmount: 3.0, Price per unit: 20.0, Expiration date: " + LocalDate.now()
                 , grocery.toString());
  }

  // ------------------------------ Negative tests ------------------------------

  /**
   * Test creating a grocery item instance with the parameter {@code name} set to {@code null} or
   * an empty string. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  void nullOrEmptyName() {
    LocalDate expirationDate = LocalDate.now();
    GroceryBatch batch = new GroceryBatch(3, 20, expirationDate);

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        null, "Dairy", "liters",
        batch));

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "", "Dairy", "liters",
        batch));
  }

  /**
   * Test creating a grocery item instance with the parameter {@code category} set to {@code null}
   * or an empty string. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  void nullOrEmptyCategory() {
    LocalDate expirationDate = LocalDate.now();
    GroceryBatch batch = new GroceryBatch(3, 20, expirationDate);

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", null, "liters",
        batch));

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", "", "liters",
        batch));

  }

  /**
   * Test creating a grocery item instance with the parameter {@code unit} set to {@code null}
   * or an empty string. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  void nullOrEmptyUnit() {
    LocalDate expirationDate = LocalDate.now();
    GroceryBatch batch = new GroceryBatch(3, 20, expirationDate);

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", "Dairy", null,
        batch));

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", "Dairy", "",
        batch));

  }

  /**
   * Test creating a grocery item instance with the parameter {@code batch} set to {@code null}.
   * Check for thrown {@code IllegalArgumentException} in the case specified.
   */
  @Test
  void nullBatch() {
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", "Dairy", "liters",
        null));

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", "Dairy", "liters",
        null));

  }

  /**
   * Ensure the method {@code addBatch} throws an {@code IllegalArgumentException} when adding a
   * {@code null} batch.
   */
  @Test
  void addNullBatch() {
    Grocery grocery = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(3, 20, LocalDate.now()));
    assertThrows(IllegalArgumentException.class, () -> grocery.addBatch(null));
  }

  /**
   * Ensure the method {@code consume} throws an {@code IllegalArgumentException} when consuming an
   * amount that equals zero or a negative number.
   */
  @Test
  void consumeZeroOrNegativeAmount() {
    Grocery grocery = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(3, 20, LocalDate.now()));
    assertThrows(IllegalArgumentException.class, () -> grocery.consume(0));
    assertThrows(IllegalArgumentException.class, () -> grocery.consume(-1));
  }
}