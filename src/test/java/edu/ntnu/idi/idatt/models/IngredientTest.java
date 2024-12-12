package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Ingredient class.
 * <p>
 * Performs the following tests:
 * <ul>
 * <li>Positive tests:
 * <ul>
 * <li>that an ingredient can be created with valid values.
 * <li>that the accessor methods for all fields return the correct values.
 * <li>that the method {@code setAmount} sets the correct amount of the ingredient.
 * <li>that the method {@code toReadableString} returns the correct string representation.
 * </ul>
 * <li>Negative tests:
 * <ul>
 * <li>that an exception is thrown when creating an ingredient with:
 * <ul>
 * <li>{@code name} that equals {@code null} or an empty string.
 * <li>{@code category} that equals {@code null} or an empty string.
 * <li>{@code unit} that equals {@code null} or an empty string.
 * <li>{@code amount} that equals zero or a negative number.
 * </ul>
 * <li>that an exception is thrown when trying to set an amount of an ingredient that equals zero or
 *     a negative number.
 * </ul>
 * </ul>
 */
@DisplayName("Test cases for the Ingredient class")
class IngredientTest {
  /**
   * Positive tests for the Ingredient class.
   */
  @Nested
  @DisplayName("Positive tests for the Ingredient class")
  class PositiveIngredientTests {

    /**
     * Test creating an ingredient with valid parameters, and ensure accessor methods for all fields
     * return the correct values.
     */
    @Test
    @DisplayName("Test creating an ingredient with valid parameters, and that the accessor methods"
        + "return the correct values.")
    void testCreateIngredient() {
      Ingredient ingredient = new Ingredient("Milk", "Dairy", "liters", 1.0);

      assertEquals("Milk", ingredient.getName());
      assertEquals("Dairy", ingredient.getCategory());
      assertEquals("liters", ingredient.getUnit());
      assertEquals(1.0, ingredient.getAmount());
    }

    /**
     * Test that the method {@code setAmount} sets the correct amount of the ingredient.
     */
    @Test
    @DisplayName("Test that the method setAmount sets the correct amount of the ingredient.")
    void testSetAmount() {
      Ingredient ingredient = new Ingredient("Milk", "Dairy", "liters", 1.0);
      ingredient.setAmount(2.0);
      assertEquals(2.0, ingredient.getAmount());
    }

    /**
     * Test that the method {@code toReadableString} returns the correct string representation.
     */
    @Test
    @DisplayName("Test that the method toReadableString returns the correct string representation.")
    void testToReadableString() {
      Ingredient ingredient = new Ingredient("Milk", "Dairy", "liters", 1.0);
      assertEquals("Milk (Dairy): 1.00 liters", ingredient.toReadableString());
    }
  }

  /**
   * Negative tests for the Ingredient class.
   */
  @Nested
  @DisplayName("Negative tests for the Ingredient class")
  class NegativeIngredientTests {

    /**
     * Test creating an ingredient instance with the parameter {@code name} set to {@code null} or
     * an empty string, and ensure an {@code IllegalArgumentException} is thrown.
     */
    @Test
    @DisplayName("Test creating an ingredient instance with the parameter name set to null or an"
        + "empty string, and ensure an IllegalArgumentException is thrown.")
    void testNullOrEmptyNameThrows() {
      assertThrows(IllegalArgumentException.class, () -> new Ingredient(
          null, "Dairy", "liters", 1.0));

      assertThrows(IllegalArgumentException.class, () -> new Ingredient(
          "", "Dairy", "liters", 1.0));
    }

    /**
     * Test creating an ingredient instance with the parameter {@code category} set to {@code null}
     * or an empty string, and ensure an {@code IllegalArgumentException} is thrown.
     */
    @Test
    @DisplayName(
        "Test creating an ingredient instance with the parameter category set to null or an"
            + "empty string, and ensure an IllegalArgumentException is thrown.")
    void testNullOrEmptyCategoryThrows() {
      assertThrows(IllegalArgumentException.class, () -> new Ingredient(
          "Milk", null, "liters", 1.0));

      assertThrows(IllegalArgumentException.class, () -> new Ingredient(
          "Milk", "", "liters", 1.0));
    }

    /**
     * Test creating an ingredient instance with the parameter {@code unit} set to {@code null} or
     * an empty string, and ensure an {@code IllegalArgumentException} is thrown.
     */
    @Test
    @DisplayName("Test creating an ingredient instance with the parameter unit set to null or an"
        + "empty string, and ensure an IllegalArgumentException is thrown.")
    void testNullOrEmptyUnitThrows() {
      assertThrows(IllegalArgumentException.class, () -> new Ingredient(
          "Milk", "Dairy", null, 1.0));

      assertThrows(IllegalArgumentException.class, () -> new Ingredient(
          "Milk", "Dairy", "", 1.0));
    }

    /**
     * Test creating an ingredient instance with the parameter {@code amount} set to zero or a
     * negative number, and ensure an {@code IllegalArgumentException} is thrown.
     */
    @Test
    @DisplayName("Test creating an ingredient instance with the parameter amount set to zero or a"
        + "negative number, and ensure an IllegalArgumentException is thrown.")
    void testNegativeAmountThrows() {
      assertThrows(IllegalArgumentException.class, () -> new Ingredient(
          "Milk", "Dairy", "liters", 0.0));

      assertThrows(IllegalArgumentException.class, () -> new Ingredient(
          "Milk", "Dairy", "liters", -1.0));
    }

    /**
     * Test setting the amount of an ingredient to zero or a negative number, and ensure an
     * {@code IllegalArgumentException} is thrown.
     */
    @Test
    @DisplayName("Test setting the amount of an ingredient to zero or a negative number, and ensure"
        + " an IllegalArgumentException is thrown.")
    void testNegativeAmountSetThrows() {
      Ingredient ingredient = new Ingredient("Milk", "Dairy", "liters", 1.0);
      assertThrows(IllegalArgumentException.class, () -> ingredient.setAmount(0.0));
      assertThrows(IllegalArgumentException.class, () -> ingredient.setAmount(-1.0));
    }
  }
}