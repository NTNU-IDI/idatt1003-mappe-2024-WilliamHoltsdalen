package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.util.Date;

/**
 * Test class for the Grocery class
 * <p>
 * Performs the following tests:
 * <ul>
 * <li>Positive tests:
 * <ul>
 * <li>that a grocery item can be created with valid values.
 * <li>that the accessor-methods for the fields {@code name}, {@code category}, {@code amount},
 * {@code unit}, {@code expiration}, {@code price}, and {@code currency} return the correct
 * values.
 * <li>that the method {@code setAmount} sets the correct amount of the grocery item.
 * </ul>
 * <li>Negative tests:
 * <ul>
 * <li>that an exception is thrown when creating a grocery item with:
 * <ul>
 * <li>{@code name} that equals {@code null} or an empty string
 * <li>{@code category} that equals {@code null} or an empty string
 * <li>{@code amount} that equals zero or a negative value
 * <li>{@code unit} that equals {@code null} or an empty string
 * <li>{@code expirationDate} that equals {@code null}
 * <li>{@code price} that equals zero or a negative value
 * <li>{@code currency} that equals {@code null} or an empty string
 * </ul>
 * <li>that an exception is thrown when setting the amount to zero or a negative value.
 * </ul>
 * </ul>
 */
class GroceryTest {
  // ------------------------------ Positive tests ------------------------------

  /**
   * Test creating a grocery item instance with valid values.
   * <p>
   * Ensure accessor-methods for all fields return the correct values.
   */
  @Test
  void testCreateGroceryItemWithValidParameters() {
    // Arrange
    String name = "Milk";
    String category = "Dairy";
    int amount = 1;
    String unit = "liters";
    Date expirationDate = new Date();
    int price = 20;
    String currency = "NOK";

    // Act
    Grocery grocery = new Grocery(name, category, amount, unit, expirationDate, price, currency);

    // Assert
    assertEquals(name, grocery.getName());
    assertEquals(category, grocery.getCategory());
    assertEquals(amount, grocery.getAmount());
    assertEquals(unit, grocery.getUnit());
    assertEquals(expirationDate, grocery.getExpirationDate());
    assertEquals(price, grocery.getPrice());
    assertEquals(currency, grocery.getCurrency());
  }

  /**
   * Ensure that the method {@code setAmount} sets the correct amount of the grocery item.
   */
  @Test
  void testSetAmount() {
    Grocery grocery = new Grocery("Milk", "Dairy", 1, "liters",
                                  new Date(), 20, "NOK");
    grocery.setAmount(2);
    assertEquals(2, grocery.getAmount());
  }

  /**
   * Ensure that the method {@code toString} returns the correct string representation.
   */
  @Test
  void testToString() {
    Grocery grocery = new Grocery("Milk", "Dairy", 1, "liters",
                                  new Date(), 20, "NOK");

    assertEquals("Name: Milk, Category: Dairy, Amount: 1.0 liters, Price: 20.0 NOK, Expiration date: "
                 + grocery.getExpirationDate(), grocery.toString());
  }

  // ------------------------------ Negative tests ------------------------------

  /**
   * Test creating a grocery item instance with the parameter {@code name} set to {@code null} or
   * an empty string. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  void testCreateGroceryItemWithNullOrEmptyName() {
    Date expirationDate = new Date();
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        null, // Name set to null
        "Dairy",
        1,
        "liters",
        expirationDate,
        20,
        "NOK")
    );

    assertThrows(IllegalArgumentException.class, () ->
        new Grocery(
            "", // Name set to an empty string
            "Dairy",
            1,
            "liters",
            expirationDate,
            20,
            "NOK")
    );
  }

  /**
   * Test creating a grocery item instance with the parameter {@code category} set to {@code null}
   * or an empty string. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  void testCreateGroceryItemWithNullOrEmptyCategory() {
    Date expirationDate = new Date();
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        null, // Category set to null
        1,
        "liters",
        expirationDate,
        20,
        "NOK")
    );

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        "", // Category set to an empty string
        1,
        "liters",
        expirationDate,
        20,
        "NOK")
    );
  }

  /**
   * Test creating a grocery item instance with the parameter {@code amount} set to a negative
   * value or zero. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  void testCreateGroceryItemWithNegativeOrZeroAmount() {
    Date expirationDate = new Date();
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        "Dairy",
        -1, // Amount set to a negative value
        "liters",
        expirationDate,
        20,
        "NOK")
    );

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        "Dairy",
        0, // Amount set to 0
        "liters",
        expirationDate,
        20,
        "NOK")
    );
  }

  /**
   * Test creating a grocery item instance with the parameter {@code unit} set to {@code null} or
   * an empty string. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  void testCreateGroceryItemWithNullOrEmptyUnit() {
    Date expirationDate = new Date();
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        "Dairy",
        1,
        null, // Unit set to null
        expirationDate,
        20,
        "NOK")
    );

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        "Dairy",
        1,
        "", // Unit set to an empty string
        expirationDate,
        20,
        "NOK")
    );
  }

  /**
   * Test creating a grocery item instance with the parameter {@code expirationDate} set to
   * {@code null}. Check for thrown {@code IllegalArgumentException} in the case specified.
   */
  @Test
  void testCreateGroceryItemWithNullExpirationDate() {
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        "Dairy",
        1,
        "liters",
        null, // Expiration date set to null
        20,
        "NOK")
    );
  }

  /**
   * Test creating a grocery item instance with the parameter {@code price} set to a negative
   * value or zero. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  void testCreateGroceryItemWithNegativeOrZeroPrice() {
    Date expirationDate = new Date();
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        "Dairy",
        1,
        "liters",
        expirationDate,
        -1, // Price set to a negative value
        "NOK")
    );

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        "Dairy",
        1,
        "liters",
        expirationDate,
        0, // Price set to 0
        "NOK")
    );
  }

  /**
   * Test creating a grocery item instance with the parameter {@code currency} set to {@code null}
   * or an empty string. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  void testCreateGroceryItemWithNullOrEmptyCurrency() {
    Date expirationDate = new Date();
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        "Dairy",
        1,
        "liters",
        expirationDate,
        20,
        null) // Currency set to null
    );

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk",
        "Dairy",
        1,
        "liters",
        expirationDate,
        20,
        "") // Currency set to an empty string
    );
  }

  /**
   * Ensure the method {@code setAmount} throws an {@code IllegalArgumentException} when setting
   * the amount to zero or a negative value.
   */
  @Test
  void testSetAmountToZeroOrNegativeValue() {
    Grocery grocery = new Grocery("Milk", "Dairy", 1, "liters",
                                  new Date(), 20, "NOK");
    assertThrows(IllegalArgumentException.class, () -> grocery.setAmount(0));
    assertThrows(IllegalArgumentException.class, () -> grocery.setAmount(-1));
  }

}