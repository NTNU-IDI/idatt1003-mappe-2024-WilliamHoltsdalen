package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.GroceryBatch;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Grocery class.
 * <p>
 * Performs the following tests:
 * <ul>
 * <li>Positive tests:
 * <ul>
 * <li>that a grocery object can be created with valid values.
 * <li>that the accessor methods for all fields return the correct values.
 * <li>that the method {@code addBatch} adds a batch to the grocery object.
 * <li>that the method {@code consume} updates the amount of the grocery object correctly.
 * <li>that the method {@code toString} returns the correct string representation.
 * </ul>
 * <li>Negative tests:
 * <ul>
 * <li>that an exception is thrown when creating a grocery object with:
 * <ul>
 * <li>{@code name} that equals {@code null} or an empty string.
 * <li>{@code category} that equals {@code null} or an empty string.
 * <li>{@code unit} that equals {@code null} or an empty string.
 * <li>{@code batch} that equals {@code null}.
 * </ul>
 * <li>that an exception is thrown when adding a GroceryBatch object that equals {@code null}.
 * <li>that an exception is thrown when trying to consume an amount of the grocery object that equals
 *     zero or a negative number.
 * <li>that an exception is thrown when trying to consume an amount greater than the total amount
 *     of the grocery object.
 * </ul>
 * </ul>
 */
@DisplayName("Test cases for the Grocery class")
class GroceryTest {
  // ------------------------------ Positive tests ------------------------------

  /**
   * Test creating a grocery object with valid parameters, and ensure accessor methods for all
   * fields return the correct values.
   */
  @Test
  @DisplayName("Test creating a grocery object with valid parameters, and ensure accessors return the"
      + " correct values")
  void testCreateGrocery() {
    GroceryBatch batch = new GroceryBatch(1.0, 20.0, LocalDate.now());
    Grocery grocery = new Grocery("Milk", "Dairy", "liters", batch);

    List<GroceryBatch> batches = new ArrayList<>();
    batches.add(batch);

    assertEquals("Milk", grocery.getName());
    assertEquals("Dairy", grocery.getCategory());
    assertEquals("liters", grocery.getUnit());
    assertEquals(1.0, grocery.getTotalAmount());
    assertEquals(batches, grocery.getBatches());
  }

  /**
   * Ensure that the method {@code addBatch} adds a batch to the grocery object.
   */
  @Test
  @DisplayName("Test adding a batch to the grocery object, and ensure the batch is added "
      + "correctly.")
  void testAddGroceryBatch() {
    Grocery grocery = new Grocery("Milk", "Dairy", "liters", new GroceryBatch(3, 20, LocalDate.now()));
    GroceryBatch batch = new GroceryBatch(2, 20, LocalDate.now().plusDays(1));

    grocery.addBatch(batch);

    assertEquals(2, grocery.getBatches().size());
    assertEquals(batch, grocery.getBatches().get(1));
  }

  /**
   * Ensure that the method {@code consume} sets the correct amount of the grocery object.
   */
  @Test
  @DisplayName("Test consuming an amount of the grocery object, and ensure the amount is updated "
      + "correctly.")
  void testConsumeGrocery() {
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters", new GroceryBatch(3, 20, LocalDate.now()));
    grocery1.consume(2);

    Grocery grocery2 = new Grocery("Milk", "Dairy", "liters", new GroceryBatch(3, 20, LocalDate.now().plusDays(1)));
    grocery2.addBatch(new GroceryBatch(2, 20, LocalDate.now()));
    grocery2.consume(3);

    assertEquals(1, grocery1.getTotalAmount());
    assertEquals(2, grocery2.getTotalAmount());
  }

  /**
   * Ensure that the method {@code sortBatches} sorts the batches of the grocery object in ascending
   * order based on the expiration date. The method {@code sortBatches} should be called after each
   * batch is added to the grocery object, and therefore does not need to be called explicitly.
   */
  @Test
  @DisplayName("Test sorting the batches of the grocery object in ascending order based on the "
      + "expiration date.")
  void testSortBatches() {
    Grocery grocery = new Grocery("Milk", "Dairy", "liters", new GroceryBatch(3, 20, LocalDate.now()));

    grocery.addBatch(new GroceryBatch(5, 20, LocalDate.now().plusDays(1)));
    grocery.addBatch(new GroceryBatch(2, 20, LocalDate.now().minusDays(1)));

    assertEquals(LocalDate.now().minusDays(1), grocery.getBatches().get(0).getExpirationDate());
    assertEquals(LocalDate.now(), grocery.getBatches().get(1).getExpirationDate());
    assertEquals(LocalDate.now().plusDays(1), grocery.getBatches().get(2).getExpirationDate());
  }

  /**
   * Ensure that the method {@code toString} returns the correct string representation of the
   * grocery object.
   */
  @Test
  @DisplayName("Test that the method toString returns the correct string representation of the "
      + "grocery object and its batches.")
  void testToStringReturnsCorrectString() {
    Grocery grocery = new Grocery("Milk", "Dairy", "liters", new GroceryBatch(3, 20, LocalDate.now()));
    grocery.addBatch(new GroceryBatch(2, 20, LocalDate.now().plusDays(1)));

    assertEquals("Name: Milk, Category: Dairy, Unit: liters, Total amount: 5.0 liters"
                  + "\nBatches:"
                  + "\nAmount: 3.0, Price per unit: 20.0, Expiration date: " + LocalDate.now()
                  + "\nAmount: 2.0, Price per unit: 20.0, Expiration date: " + LocalDate.now().plusDays(1),
                  grocery.toString());
  }

  // ------------------------------ Negative tests ------------------------------

  /**
   * Test creating a grocery object with the parameter {@code name} set to {@code null} or
   * an empty string. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  @DisplayName("Test creating a grocery object with the parameter name set to null or an empty "
      + "string, and ensure an IllegalArgumentException is thrown.")
  void testNullOrEmptyNameThrows() {
    LocalDate expirationDate = LocalDate.now();
    GroceryBatch batch = new GroceryBatch(3, 20, expirationDate);

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        null, "Dairy", "liters", batch));
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "", "Dairy", "liters", batch));
  }

  /**
   * Test creating a grocery object with the parameter {@code category} set to {@code null}
   * or an empty string, and ensure an {@code IllegalArgumentException} is thrown.
   */
  @Test
  @DisplayName("Test creating a grocery object with the parameter category set to null or an empty "
      + "string, and ensure an IllegalArgumentException is thrown.")
  void testNullOrEmptyCategoryThrows() {
    LocalDate expirationDate = LocalDate.now();
    GroceryBatch batch = new GroceryBatch(3, 20, expirationDate);

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", null, "liters", batch));
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", "", "liters", batch));
  }

  /**
   * Test creating a grocery object with the parameter {@code unit} set to {@code null} or an empty
   * string, and ensure an {@code IllegalArgumentException} is thrown.
   */
  @Test
  @DisplayName("Test creating a grocery object with the parameter unit set to null or an empty "
      + "string, and ensure an IllegalArgumentException is thrown.")
  void testNullOrEmptyUnitThrows() {
    LocalDate expirationDate = LocalDate.now();
    GroceryBatch batch = new GroceryBatch(3, 20, expirationDate);

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", "Dairy", null, batch));
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", "Dairy", "", batch));
  }

  /**
   * Test creating a grocery object with the parameter {@code batch} set to {@code null} and check for
   * thrown {@code IllegalArgumentException}.
   */
  @Test
  @DisplayName("Test creating a grocery object with the parameter batch set to null, and ensure an "
      + "IllegalArgumentException is thrown.")
  void testNullBatchThrows() {
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", "Dairy", "liters", null));
  }

  /**
   * Ensure the method {@code addBatch} throws an {@code IllegalArgumentException} when trying to add a
   * {@code null} batch.
   */
  @Test
  @DisplayName("Test adding a null batch to the grocery object, and ensure an IllegalArgumentException "
      + "is thrown.")
  void testAddNullBatchThrows() {
    Grocery grocery = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(3, 20, LocalDate.now()));
    assertThrows(IllegalArgumentException.class, () -> grocery.addBatch(null));
  }

  /**
   * Ensure the method {@code consume} throws an {@code IllegalArgumentException} when trying to consume an
   * amount that equals zero or a negative number.
   */
  @Test
  @DisplayName("Test consuming an amount that equals zero or a negative number, and ensure an "
      + "IllegalArgumentException is thrown.")
  void testConsumeZeroOrNegativeAmountThrows() {
    Grocery grocery = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(3, 20, LocalDate.now()));
    assertThrows(IllegalArgumentException.class, () -> grocery.consume(0));
    assertThrows(IllegalArgumentException.class, () -> grocery.consume(-1));
  }

  /**
   * Ensure that the method {@code consume} throws an {@code IllegalArgumentException} when trying to consume
   * an amount greater than the total amount of the grocery object.
   */
  @Test
  @DisplayName("Test consuming an amount greater than the total amount of the grocery object, and "
      + "ensure an IllegalArgumentException is thrown.")
  void testConsumeAmountGreaterThanTotalAmountThrows() {
    Grocery grocery = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(3, 20, LocalDate.now()));
    assertThrows(IllegalArgumentException.class, () -> grocery.consume(4));
  }
}