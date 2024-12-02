package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for the FoodStorage class.
 * <p>
 * Performs the following tests:
 * <ul>
 * <li>Positive tests:
 * <ul>
 * <li>that a food storage can be created, and that the list of groceries is empty.
 * <li>that the accessor method {@code getAllGroceries} returns the list of groceries, even if the list is empty.
 * <li>that the accessor method {@code getAllGroceriesAlphabetically} returns the list of groceries, sorted alphabetically by name.
 * <li>that the accessor method {@code getAllGroceriesByExpirationDate} returns the list of groceries, sorted by expiration date.
 * <li>that the accessor method {@code getGroceryByName} returns the grocery with the provided name.
 * <li>that the accessor method {@code getGroceriesByCategory} returns the list of groceries that belong to the specified category.
 * <li>that the accessor method {@code getGroceriesExpiringBeforeDate} returns the list of groceries that expire before the given date.
 * <li>that the accessor method {@code getGroceriesExpiringOnDate} returns the list of groceries that expire on the given date.
 * <li>that the method {@code addGrocery} adds a grocery to the food storage.
 * <li>that the method {@code removeGrocery} removes a grocery from the food storage.
 * <li>that the method {@code removeAllGroceries} removes all groceries from the food storage.
 * </ul>
 * <li>Negative tests:
 * <ul>
 * <li>that the method {@code getGroceryByName} throws an {@code IllegalArgumentException} when the name of the grocery is null or blank.
 * <li>that the method {@code getGroceryByName} throws an {@code IllegalArgumentException} when no grocery with the provided name is found.
 * <li>that the method {@code getGroceriesByCategory} throws an {@code IllegalArgumentException} when the category is null or blank.
 * <li>that the method {@code getGroceriesExpiringBeforeDate} throws an {@code IllegalArgumentException} when the given date is null.
 * <li>that the method {@code getGroceriesExpiringOnDate} throws an {@code IllegalArgumentException} when the given date is null.
 * <li>that the method {@code addGrocery} throws an {@code IllegalArgumentException} when the grocery is null.
 * <li>that the method {@code addGrocery} throws an {@code IllegalArgumentException} when the grocery already exists in the food storage.
 * <li>that the method {@code removeGrocery} throws an {@code IllegalArgumentException} when the grocery is null.
 * <li>that the method {@code removeGrocery} throws an {@code IllegalArgumentException} when the grocery does not exist in the food storage.
 * </ul>
 * </ul>
 */
@DisplayName("Test cases for the FoodStorage class")
class FoodStorageTest {
  // ------------------------------ Positive tests ------------------------------

  /**
   * Test creating a food storage, and ensuring that the list of groceries is empty.
   */
  @Test
  @DisplayName("Test creating a food storage, and ensure that the list of groceries is empty")
  void testCreateFoodStorage() {
    FoodStorage foodStorage = new FoodStorage();
    assertEquals(List.of(), foodStorage.getAllGroceries());
  }

  /**
   * Test the accessor method {@code getAllGroceries}
   */
  @Test
  @DisplayName("Test accessor method getAllGroceries")
  void testGetAllGroceriesOneGrocery() {
    FoodStorage foodStorage = new FoodStorage();

    Grocery grocery = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now()));
    foodStorage.addGrocery(grocery);

    assertEquals(List.of(grocery), foodStorage.getAllGroceries());
  }

  /**
   * Test the accessor method {@code getAllGroceriesAlphabetically}
   */
  @Test
  @DisplayName("Test accessor method getAllGroceriesAlphabetically")
  void testGetAllGroceriesAlphabetically() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now()));
    Grocery grocery2 = new Grocery("Apple", "Fruit", "pieces",
        new GroceryBatch(3, 2, LocalDate.now()));
    Grocery grocery3 = new Grocery("Banana", "Fruit", "pieces",
        new GroceryBatch(10, 1, LocalDate.now()));
    Grocery grocery4 = new Grocery("Grapes", "Fruit", "pieces",
        new GroceryBatch(20, 0.5, LocalDate.now()));
    Grocery grocery5 = new Grocery("Salt", "Spices", "grams",
        new GroceryBatch(500, 0.1, LocalDate.now()));

    foodStorage.addGrocery(grocery1);
    foodStorage.addGrocery(grocery2);
    foodStorage.addGrocery(grocery3);
    foodStorage.addGrocery(grocery4);
    foodStorage.addGrocery(grocery5);

    assertEquals(List.of(grocery2, grocery3, grocery4, grocery1, grocery5),
        foodStorage.getAllGroceriesAlphabetically());
  }

  /**
   * Test the accessor method {@code getAllGroceriesByExpirationDate}
   */
  @Test
  @DisplayName("Test accessor method getAllGroceriesByExpirationDate")
  void testGetAllGroceriesByExpirationDate() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now().plusDays(1)));
    Grocery grocery2 = new Grocery("Apple", "Fruit", "pieces",
        new GroceryBatch(3, 2, LocalDate.now().minusDays(1)));
    Grocery grocery3 = new Grocery("Banana", "Fruit", "pieces",
        new GroceryBatch(10, 1, LocalDate.now().minusDays(2)));
    Grocery grocery4 = new Grocery("Grapes", "Fruit", "pieces",
        new GroceryBatch(20, 0.5, LocalDate.now().plusDays(2)));
    Grocery grocery5 = new Grocery("Salt", "Spices", "grams",
        new GroceryBatch(500, 0.1, LocalDate.now()));

    foodStorage.addGrocery(grocery1);
    foodStorage.addGrocery(grocery2);
    foodStorage.addGrocery(grocery3);
    foodStorage.addGrocery(grocery4);
    foodStorage.addGrocery(grocery5);

    assertEquals(List.of(grocery3, grocery2, grocery5, grocery1, grocery4),
        foodStorage.getAllGroceriesByExpirationDate());
  }

  /**
   * Test the accessor method {@code getGroceryByName}
   */
  @Test
  @DisplayName("Test accessor method getGroceryByName")
  void testGetGroceryByName() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now()));

    foodStorage.addGrocery(grocery1);

    assertEquals(grocery1, foodStorage.getGroceryByName("Milk"));
  }

  /**
   * Test the accessor method {@code getGroceriesByCategory}
   */
  @Test
  @DisplayName("Test accessor method getGroceriesByCategory")
  void testGetGroceriesByCategory() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now()));
    Grocery grocery2 = new Grocery("Apple", "Fruit", "pieces",
        new GroceryBatch(3, 2, LocalDate.now()));
    Grocery grocery3 = new Grocery("Banana", "Fruit", "pieces",
        new GroceryBatch(10, 1, LocalDate.now()));

    foodStorage.addGrocery(grocery1);
    foodStorage.addGrocery(grocery2);
    foodStorage.addGrocery(grocery3);

    assertEquals(List.of(grocery2, grocery3), foodStorage.getGroceriesByCategory("Fruit"));
  }

  /**
   * Test the accessor method {@code getGroceriesExpiringBeforeDate}
   */
  @Test
  @DisplayName("Test accessor method getGroceriesExpiringBeforeDate")
  void testGetGroceriesExpiringBeforeDate() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now().plusDays(1)));
    Grocery grocery2 = new Grocery("Apple", "Fruit", "pieces",
        new GroceryBatch(3, 2, LocalDate.now().minusDays(1)));
    Grocery grocery3 = new Grocery("Banana", "Fruit", "pieces",
        new GroceryBatch(10, 1, LocalDate.now().minusDays(2)));

    foodStorage.addGrocery(grocery1);
    foodStorage.addGrocery(grocery2);
    foodStorage.addGrocery(grocery3);

    assertEquals(List.of(grocery2, grocery3),
        foodStorage.getGroceriesExpiringBeforeDate(LocalDate.now()));
  }

  /**
   * Test the accessor method {@code getGroceriesExpiringOnDate}
   */
  @Test
  @DisplayName("Test accessor method getGroceriesExpiringOnDate")
  void testGetGroceriesExpiringOnDate() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now().plusDays(1)));
    Grocery grocery2 = new Grocery("Apple", "Fruit", "pieces",
        new GroceryBatch(3, 2, LocalDate.now().minusDays(2)));
    Grocery grocery3 = new Grocery("Banana", "Fruit", "pieces",
        new GroceryBatch(10, 1, LocalDate.now().minusDays(5)));

    foodStorage.addGrocery(grocery1);
    foodStorage.addGrocery(grocery2);
    foodStorage.addGrocery(grocery3);

    assertEquals(List.of(grocery1),
        foodStorage.getGroceriesExpiringOnDate(LocalDate.now().plusDays(1)));
  }

  /**
   * Test the method {@code addGrocery}
   */
  @Test
  @DisplayName("Test method addGrocery")
  void testAddGrocery() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now()));

    foodStorage.addGrocery(grocery1);

    assertEquals(List.of(grocery1), foodStorage.getAllGroceries());
  }

  /**
   * Test the method {@code removeGrocery}
   */
  @Test
  @DisplayName("Test method removeGrocery")
  void testRemoveGrocery() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now()));
    Grocery grocery2 = new Grocery("Apple", "Fruit", "pieces",
        new GroceryBatch(3, 2, LocalDate.now()));

    foodStorage.addGrocery(grocery1);
    foodStorage.addGrocery(grocery2);
    foodStorage.removeGrocery(grocery1);

    assertEquals(List.of(grocery2), foodStorage.getAllGroceries());
  }

  /**
   * Test the method {@code removeAllGroceries}
   */
  @Test
  @DisplayName("Test method removeAllGroceries")
  void testRemoveAllGroceries() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now()));
    Grocery grocery2 = new Grocery("Apple", "Fruit", "pieces",
        new GroceryBatch(3, 2, LocalDate.now()));

    foodStorage.addGrocery(grocery1);
    foodStorage.addGrocery(grocery2);
    foodStorage.removeAllGroceries();

    assertEquals(List.of(), foodStorage.getAllGroceries());
  }

  // ------------------------------ Negative tests ------------------------------

  /**
   * Test that the accessor method {@code getGroceryByName} throws an
   * {@code IllegalArgumentException} when the name of the grocery is null or blank.
   */
  @Test
  @DisplayName("Test accessor method getGroceryByName throws IllegalArgumentException when the name of the grocery is null or blank")
  void testGetGroceryByNameNullOrBlankThrows() {
    FoodStorage foodStorage = new FoodStorage();

    assertThrows(IllegalArgumentException.class, () -> foodStorage.getGroceryByName(null));
    assertThrows(IllegalArgumentException.class, () -> foodStorage.getGroceryByName(""));
  }

  /**
   * Test that the accessor method {@code getGroceryByName} throws an
   * {@code IllegalArgumentException} when no grocery with the provided name is found.
   */
  @Test
  @DisplayName("Test accessor method getGroceryByName throws IllegalArgumentException when no grocery with the provided name is found")
  void testGetGroceryByNameNotFoundThrows() {
    FoodStorage foodStorage = new FoodStorage();

    assertThrows(IllegalArgumentException.class, () -> foodStorage.getGroceryByName("Apple"));
  }

  /**
   * Test that the accessor method {@code getGroceriesByCategory} throws an
   * {@code IllegalArgumentException} when the category is null or blank.
   */
  @Test
  @DisplayName("Test accessor method getGroceriesByCategory throws IllegalArgumentException when the category is null or blank")
  void testGetGroceriesByCategoryNullOrBlankThrows() {
    FoodStorage foodStorage = new FoodStorage();

    assertThrows(IllegalArgumentException.class, () -> foodStorage.getGroceriesByCategory(null));
    assertThrows(IllegalArgumentException.class, () -> foodStorage.getGroceriesByCategory(""));
  }

  /**
   * Test that the accessor method {@code getGroceriesExpiringBeforeDate} throws an
   * {@code IllegalArgumentException} when the given date is null.
   */
  @Test
  @DisplayName("Test accessor method getGroceriesExpiringBeforeDate throws IllegalArgumentException when the given date is null")
  void testGetGroceriesExpiringBeforeDateNullThrows() {
    FoodStorage foodStorage = new FoodStorage();

    assertThrows(IllegalArgumentException.class, () -> foodStorage.getGroceriesExpiringBeforeDate(null));
  }

  /**
   * Test that the accessor method {@code getGroceriesExpiringOnDate} throws an
   * {@code IllegalArgumentException} when the given date is null.
   */
  @Test
  @DisplayName("Test accessor method getGroceriesExpiringOnDate throws IllegalArgumentException when the given date is null")
  void testGetGroceriesExpiringOnDateNullThrows() {
    FoodStorage foodStorage = new FoodStorage();

    assertThrows(IllegalArgumentException.class, () -> foodStorage.getGroceriesExpiringOnDate(null));
  }

  /**
   * Test that the method {@code addGrocery} throws an {@code IllegalArgumentException} when the grocery is null.
   */
  @Test
  @DisplayName("Test method addGrocery throws IllegalArgumentException when the grocery is null")
  void testAddGroceryNullThrows() {
    FoodStorage foodStorage = new FoodStorage();

    assertThrows(IllegalArgumentException.class, () -> foodStorage.addGrocery(null));
  }

  /**
   * Test that the method {@code addGrocery} throws an {@code IllegalArgumentException} when the grocery already exists in the food storage.
   */
  @Test
  @DisplayName("Test method addGrocery throws IllegalArgumentException when the grocery already exists in the food storage")
  void testAddGroceryAlreadyExistsThrows() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now()));

    foodStorage.addGrocery(grocery1);

    assertThrows(IllegalArgumentException.class, () -> foodStorage.addGrocery(grocery1));
  }

  /**
   * Test that the method {@code removeGrocery} throws an {@code IllegalArgumentException} when the grocery is null.
   */
  @Test
  @DisplayName("Test method removeGrocery throws IllegalArgumentException when the grocery is null")
  void testRemoveGroceryNullThrows() {
    FoodStorage foodStorage = new FoodStorage();

    assertThrows(IllegalArgumentException.class, () -> foodStorage.removeGrocery(null));
  }

  /**
   * Test that the method {@code removeGrocery} throws an {@code IllegalArgumentException} when the grocery does not exist in the food storage.
   */
  @Test
  @DisplayName("Test method removeGrocery throws IllegalArgumentException when the grocery does not exist in the food storage")
  void testRemoveGroceryNotFoundThrows() {
    FoodStorage foodStorage = new FoodStorage();
    Grocery grocery1 = new Grocery("Milk", "Dairy", "liters",
        new GroceryBatch(1, 5, LocalDate.now()));

    assertThrows(IllegalArgumentException.class, () -> foodStorage.removeGrocery(grocery1));
  }
}