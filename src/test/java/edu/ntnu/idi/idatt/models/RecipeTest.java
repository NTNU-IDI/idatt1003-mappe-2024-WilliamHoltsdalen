package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Recipe class.
 * <p>
 * Performs the following tests:
 * <ul>
 * <li>Positive tests:
 * <ul>
 * <li>that a recipe can be created with valid values.
 * <li>that the accessor methods for all fields return the correct values.
 * <li>that the method {@code setName} sets the correct name.
 * <li>that the method {@code setDescription} sets the correct description.
 * <li>that the method {@code setInstructions} sets the correct instructions.
 * <li>that the method {@code setServings} sets the correct number of servings.
 * <li>that the method {@code addIngredient} adds an ingredient to the recipe.
 * <li>that the method {@code addIngredient} adds the correct amount of an ingredient to the recipe,
 *     if the ingredient already exists.
 * <li>that the method {@code removeIngredient} removes an ingredient from the recipe.
 * <li>that the method {@code toString} returns the correct string representation of the recipe.
 * <li>that the method {@code toString} returns the correct string representation of the recipe when
 *     the ingredients are empty.
 * </ul>
 * <li>Negative tests:
 * <ul>
 * <li>that an exception is thrown when creating a recipe with:
 * <ul>
 * <li>{@code name} that equals {@code null} or an empty string.
 * <li>{@code description} that equals {@code null} or an empty string.
 * <li>{@code instructions} that equals {@code null} or an empty string.
 * <li>{@code servings} that is less than or equal to zero.
 * </ul>
 * <li>that an exception is thrown when trying to get the ingredient with the name {@code null} or
 *     an empty string.
 * <li>that an exception is thrown when trying to get an ingredient that does not exist.
 * <li>that an exception is thrown when setting the name of a recipe to {@code null} or an empty
 *     string.
 * <li>that an exception is thrown when setting the description of a recipe to {@code null} or an
 *     empty string.
 * <li>that an exception is thrown when setting the instructions of a recipe to {@code null} or an
 *     empty string.
 * <li>that an exception is thrown when setting the number of servings of a recipe to less than or
 *     equal to zero.
 * <li>that an exception is thrown when adding an ingredient to a recipe that is {@code null}.
 * <li>that an exception is thrown when removing an ingredient from a recipe that is {@code null}.
 * <li>that an exception is thrown when removing an ingredient that does not exist from a recipe.
 * </ul>
 * </ul>
 *
 */
@DisplayName("Test cases for the Recipe class")
class RecipeTest {
  // ------------------------------ Positive tests ------------------------------

  /**
   * Test creating a recipe with valid parameters, and ensure accessor methods for all fields return
   * the correct values.
   */
  @Test
  @DisplayName("Test creating a recipe with valid parameters, and ensure accessor methods for all "
      + "fields return the correct values.")
  void testCreateRecipe() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    assertEquals("Steak with Potatoes", recipe.getName());
    assertEquals("A delicious dish that has Steak and Potatoes.", recipe.getDescription());
    assertEquals("These are instructions", recipe.getInstructions());
    assertEquals(3, recipe.getServings());
  }

  /**
   * Test that the method {@code setName} sets the correct name.
   */
  @Test
  @DisplayName("Test that the method setName sets the correct name.")
  void testSetName() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    recipe.setName("Chicken with Rice");

    assertEquals("Chicken with Rice", recipe.getName());
  }

  /**
   * Test that the method {@code setDescription} sets the correct description.
   */
  @Test
  @DisplayName("Test that the method setDescription sets the correct description.")
  void testSetDescription() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    recipe.setDescription("A delicious dish that has Chicken and Rice.");

    assertEquals("A delicious dish that has Chicken and Rice.", recipe.getDescription());
  }

  /**
   * Test that the method {@code setInstructions} sets the correct instructions.
   */
  @Test
  @DisplayName("Test that the method setInstructions sets the correct instructions.")
  void testSetInstructions() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    recipe.setInstructions("These are new instructions");

    assertEquals("These are new instructions", recipe.getInstructions());
  }

  /**
   * Test that the method {@code setServings} sets the correct number of servings.
   */
  @Test
  @DisplayName("Test that the method setServings sets the correct number of servings.")
  void testSetServings() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    recipe.setServings(5);

    assertEquals(5, recipe.getServings());
  }

  /**
   * Test that the method {@code addIngredient} adds an ingredient to the recipe.
   */
  @Test
  @DisplayName("Test that the method addIngredient adds an ingredient to the recipe.")
  void testAddIngredient() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);
    Ingredient ingredient = new Ingredient("Potatoes", "Vegetables", "pieces", 2);

    recipe.addIngredient(ingredient);

    assertEquals(2, recipe.getIngredient("Potatoes").getAmount());
  }

  /**
   * Test that the method {@code addIngredient} adds the correct amount of an ingredient to the
   * recipe, if the ingredient already exists.
   */
  @Test
  @DisplayName("Test that the method addIngredient adds the correct amount of an ingredient to "
      + "the recipe, if the ingredient already exists.")
  void testAddIngredientExistingIngredient() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);
    Ingredient ingredient = new Ingredient("Potatoes", "Vegetables", "pieces", 2);

    recipe.addIngredient(ingredient);
    recipe.addIngredient(ingredient);

    assertEquals(4, recipe.getIngredient("Potatoes").getAmount());
  }

  /**
   * Test that the method {@code removeIngredient} removes an ingredient from the recipe.
   */
  @Test
  @DisplayName("Test that the method removeIngredient removes an ingredient from the recipe.")
  void testRemoveIngredient() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);
    Ingredient ingredient = new Ingredient("Potatoes", "Vegetables", "pieces", 2);

    recipe.addIngredient(ingredient);
    recipe.removeIngredient(ingredient);

    assertEquals(List.of(), recipe.getIngredients());
  }

  /**
   * Test that the method {@code toString} returns the correct string representation of the
   * recipe.
   */
  @Test
  @DisplayName("Test that the method toString returns the correct string representation of the "
      + "recipe.")
  void testToString() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);
    Ingredient ingredient = new Ingredient("Potatoes", "Vegetables", "pieces", 2);

    recipe.addIngredient(ingredient);

    assertEquals("""
        Name: Steak with Potatoes
        Description: A delicious dish that has Steak and Potatoes.
        Servings: 3
        
        Instructions:
        These are instructions
        
        Ingredients:
        Potatoes (Vegetables): 2.00 pieces
        
        """, recipe.toString());
  }

  /**
   * Test that the method {@code toString} returns the correct string representation of the recipe
   * when the ingredients are empty.
   */
  @Test
  @DisplayName("Test that the method toString returns the correct string representation of the "
      + "recipe when the ingredients are empty.")
  void testToStringEmptyIngredients() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);
    assertEquals("""
        Name: Steak with Potatoes
        Description: A delicious dish that has Steak and Potatoes.
        Servings: 3
        
        Instructions:
        These are instructions
        
        Ingredients:
        
        """, recipe.toString());
  }
  // ------------------------------ Negative tests ------------------------------

  /**
   * Test creating a recipe with the parameter {@code name} set to {@code null} or an empty string.
   * Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  @DisplayName("Test creating a recipe with the parameter name set to null or an empty string. "
      + "Check for thrown IllegalArgumentException in the cases specified.")
  void nullOrEmptyName() {
    assertThrows(IllegalArgumentException.class, () -> new Recipe(
        null, "A delicious dish that has Steak and Potatoes.", "These are instructions", 3));
    assertThrows(IllegalArgumentException.class, () -> new Recipe(
        "", "A delicious dish that has Steak and Potatoes.", "These are instructions", 3));
  }

  /**
   * Test creating a recipe with the parameter {@code description} set to {@code null} or an empty
   * string. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  @DisplayName("Test creating a recipe with the parameter description set to null or an empty "
      + "string. Check for thrown IllegalArgumentException in the cases specified.")
  void nullOrEmptyDescription() {
    assertThrows(IllegalArgumentException.class, () -> new Recipe(
        "Steak with Potatoes", null, "These are instructions", 3));
    assertThrows(IllegalArgumentException.class, () -> new Recipe(
        "Steak with Potatoes", "", "These are instructions", 3));
  }

  /**
   * Test creating a recipe with the parameter {@code instructions} set to {@code null} or an empty
   * string. Check for thrown {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  @DisplayName("Test creating a recipe with the parameter instructions set to null or an empty "
      + "string. Check for thrown IllegalArgumentException in the cases specified.")
  void nullOrEmptyInstructions() {
    assertThrows(IllegalArgumentException.class, () -> new Recipe(
        "Steak with Potatoes", "A delicious dish that has Steak and Potatoes.", null, 3));
    assertThrows(IllegalArgumentException.class, () -> new Recipe(
        "Steak with Potatoes", "A delicious dish that has Steak and Potatoes.", "", 3));
  }

  /**
   * Test creating a recipe with the parameter {@code servings} set to less than or equal to zero.
   * Check for thrown {@code IllegalArgumentException} in the case specified.
   */
  @Test
  @DisplayName("Test creating a recipe with the parameter servings set to less than or equal to "
      + "zero. Check for thrown IllegalArgumentException in the case specified.")
  void lessThanOrEqualToZeroServings() {
    assertThrows(IllegalArgumentException.class, () -> new Recipe(
        "Steak with Potatoes", "A delicious dish that has Steak and Potatoes.",
        "These are instructions", 0));
  }

  /**
   * Test getting the ingredient with the name {@code null} or an empty string. Check for thrown
   * {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  @DisplayName("Test getting the ingredient with the name null or an empty string. Check for thrown "
      + "IllegalArgumentException in the cases specified.")
  void nullOrEmptyNameGetIngredient() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    assertThrows(IllegalArgumentException.class, () -> recipe.getIngredient(null));
    assertThrows(IllegalArgumentException.class, () -> recipe.getIngredient(""));
  }

  /**
   * Test getting an ingredient that does not exist. Check for thrown {@code IllegalArgumentException}
   * in the case specified.
   */
  @Test
  @DisplayName("Test getting an ingredient that does not exist. Check for thrown "
      + "IllegalArgumentException in the case specified.")
  void ingredientNotFoundGetIngredient() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    assertThrows(IllegalArgumentException.class, () -> recipe.getIngredient("Potatoes"));
  }

  /**
   * Test setting the name of a recipe to {@code null} or an empty string. Check for thrown
   * {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  @DisplayName("Test setting the name of a recipe to null or an empty string. Check for thrown "
      + "IllegalArgumentException in the cases specified.")
  void nullOrEmptyNameSetName() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    assertThrows(IllegalArgumentException.class, () -> recipe.setName(null));
    assertThrows(IllegalArgumentException.class, () -> recipe.setName(""));
  }

  /**
   * Test setting the description of a recipe to {@code null} or an empty string. Check for thrown
   * {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  @DisplayName("Test setting the description of a recipe to null or an empty string. Check for "
      + "thrown IllegalArgumentException in the cases specified.")
  void nullOrEmptyDescriptionSetDescription() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    assertThrows(IllegalArgumentException.class, () -> recipe.setDescription(null));
    assertThrows(IllegalArgumentException.class, () -> recipe.setDescription(""));
  }

  /**
   * Test setting the instructions of a recipe to {@code null} or an empty string. Check for thrown
   * {@code IllegalArgumentException} in the cases specified.
   */
  @Test
  @DisplayName("Test setting the instructions of a recipe to null or an empty string. Check for "
      + "thrown IllegalArgumentException in the cases specified.")
  void nullOrEmptyInstructionsSetInstructions() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    assertThrows(IllegalArgumentException.class, () -> recipe.setInstructions(null));
    assertThrows(IllegalArgumentException.class, () -> recipe.setInstructions(""));
  }

  /**
   * Test setting the number of servings of a recipe to less than or equal to zero. Check for thrown
   * {@code IllegalArgumentException} in the case specified.
   */
  @Test
  @DisplayName("Test setting the number of servings of a recipe to less than or equal to zero. "
      + "Check for thrown IllegalArgumentException in the case specified.")
  void lessThanOrEqualToZeroServingsSetServings() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    assertThrows(IllegalArgumentException.class, () -> recipe.setServings(0));
  }

  /**
   * Test adding an ingredient to a recipe that is {@code null}. Check for thrown
   * {@code IllegalArgumentException} in the case specified.
   */
  @Test
  @DisplayName("Test adding an ingredient to a recipe that is null. Check for thrown "
      + "IllegalArgumentException in the case specified.")
  void nullIngredientAddIngredient() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    assertThrows(IllegalArgumentException.class, () -> recipe.addIngredient(null));
  }

  /**
   * Test removing an ingredient from a recipe that is {@code null}. Check for thrown
   * {@code IllegalArgumentException} in the case specified.
   */
  @Test
  @DisplayName("Test removing an ingredient from a recipe that is null. Check for thrown "
      + "IllegalArgumentException in the case specified.")
  void nullIngredientRemoveIngredient() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);

    assertThrows(IllegalArgumentException.class, () -> recipe.removeIngredient(null));
  }

  /**
   * Test removing an ingredient that does not exist from a recipe. Check for thrown
   * {@code IllegalArgumentException} in the case specified.
   */
  @Test
  @DisplayName("Test removing an ingredient that does not exist from a recipe. Check for thrown "
      + "IllegalArgumentException in the case specified.")
  void ingredientNotFoundRemoveIngredient() {
    Recipe recipe = new Recipe("Steak with Potatoes", "A delicious dish that has "
        + "Steak and Potatoes.", "These are instructions", 3);
    Ingredient ingredient = new Ingredient("Potatoes", "Vegetables", "pieces", 2);

    assertThrows(IllegalArgumentException.class, () -> recipe.removeIngredient(ingredient));
  }
}