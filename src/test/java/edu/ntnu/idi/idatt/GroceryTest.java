import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.Grocery;

import org.junit.jupiter.api.Test;
import java.util.Date;

/**
 * Test class for the Grocery class
 * <p>
 * The following is tested:
 * <p>
 * Positive tests:
 * <ul>
 *   <li>that a grocery item can be created with valid values.
 *   <li>that the accessor-method returning name returns the correct name.
 *   <li>that the accessor-method returning category returns the correct category.
 *   <li>that the accessor-method returning amount returns the correct amount.
 *   <li>that the accessor-method returning unit returns the correct unit.
 *   <li>that the accessor-method returning expiration date returns the correct expiration date.
 *   <li>that the accessor-method returning price returns the correct price.
 *   <li>that the accessor-method returning currency returns the correct currency.
 * </ul>
 * <p>
 * Negative tests:
 * <ul>
 *   <li>that an exception is thrown when creating a grocery item with a name that is empty
 *   or {@code null}
 *   <li>that an exception is thrown when creating a grocery item with a category that is empty
 *   or {@code null}
 *   <li>that an exception is thrown when creating a grocery item with an amount that is negative
 *   or equal to 0.
 *   <li>that an exception is thrown when creating a grocery item with a unit that is empty
 *   or {@code null}
 *   <li>that an exception is thrown when creating a grocery item with an expiration date that
 *   is {@code null}.
 *   <li>that an exception is thrown when creating a grocery item with a price that is negative
 *   or equal to 0.
 *   <li>that an exception is thrown when creating a grocery item with a currency that is empty
 *   or {@code null}
 * </ul>
 */
class GroceryTest {

  // ------------------------------ Positive tests ------------------------------

  /**
   * Test creating a grocery item instance with valid values.
   *
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


  // ------------------------------ Negative tests ------------------------------

  /**
   * Test creating a grocery item instance with parameter name set to null.
   */
  @Test
  void testCreateGroceryItemWithNullName() {
    Date expirationDate = new Date();
    assertThrows(IllegalArgumentException.class, () -> {
      new Grocery(null, "Dairy", 1, "liters", expirationDate, 20,
          "NOK");
    });
  }

  /**
   * Test creating a grocery item instance with parameter name set to an empty string.
   *
   */
  @Test
  void testCreateGroceryItemWithEmptyName() {
    Date expirationDate = new Date();
    assertThrows(IllegalArgumentException.class, () -> {
      new Grocery("", "Dairy", 1, "liters", expirationDate, 20,
          "NOK");
    });
  }

}