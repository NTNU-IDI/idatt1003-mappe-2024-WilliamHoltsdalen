package edu.ntnu.idi.idatt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Cookbook class.
 * <p>
 * Performs the following tests:
 * <ul>
 * <li>Positive tests:
 * <ul>
 * <li>that the cookbook can be created with no recipes, and that the list of recipes is empty.
 * <li>that the accessor method {@code getRecipes} returns the list of recipes, even if the list is
 *      empty.
 * <li>that the accessor method {@code getRecipe} returns the recipe with the provided name.
 * <li>that the method {@code addRecipe} adds a recipe to the cookbook, and that the list of recipes
 *      is sorted alphabetically by name after adding the recipe.
 * <li>that the method {@code removeRecipe} removes a recipe from the cookbook.
 * <li>that the method {@code sortRecipes} sorts the recipes in the cookbook in ascending alphabetical order, by name.
 * <li>that the method {@code removeAllRecipes} removes all recipes from the cookbook.
 * </ul>
 * <li>Negative tests:
 * <ul>
 * <li>that the method {@code getRecipe} throws an {@code IllegalArgumentException} when the name of
 *      the recipe is null or blank.
 * <li>that the method {@code getRecipe} throws an {@code IllegalArgumentException} when no recipe
 *      with the provided name is found.
 * <li>that the method {@code addRecipe} throws an {@code IllegalArgumentException} when the recipe
 *      is null.
 * <li>that the method {@code addRecipe} throws an {@code IllegalArgumentException} when the recipe
 *      already exists in the cookbook.
 * <li>that the method {@code removeRecipe} throws an {@code IllegalArgumentException} when the recipe
 *      is null.
 * <li>that the method {@code removeRecipe} throws an {@code IllegalArgumentException} when the recipe
 *      does not exist in the cookbook.
 * </ul>
 * </ul>
 */
@DisplayName("Test cases for the Cookbook class")
class CookbookTest {
  // ------------------------------ Positive tests ------------------------------

  /**
   * Test creating a cookbook with no recipes, and ensure the list of recipes is empty.
   */
  @Test
  @DisplayName("Test creating a cookbook and, ensure the list of recipes is empty")
  void testCreateCookbook() {
    Cookbook cookbook = new Cookbook();

    assert cookbook.getRecipes().isEmpty();
  }

  /**
   * Test accessing the list of recipes, even if the list is empty.
   */
  @Test
  @DisplayName("Test accessing the list of recipes")
  void testGetRecipes() {
    Cookbook cookbook = new Cookbook();
    Recipe recipe = new Recipe("Test recipe", "Test description", "Test instructions", 1);

    cookbook.addRecipe(recipe);

    assertEquals(List.of(recipe), cookbook.getRecipes());
  }

  /**
   * Test accessing a recipe with the provided name.
   */
  @Test
  @DisplayName("Test accessing a recipe with the provided name")
  void testGetRecipeWithName() {
    Cookbook cookbook = new Cookbook();
    Recipe recipe = new Recipe("Test recipe", "Test description", "Test instructions", 1);

    cookbook.addRecipe(recipe);

    assertEquals(recipe, cookbook.getRecipe("Test recipe"));
  }

  /**
   * Test adding a recipe to the cookbook, and ensure the list of recipes is sorted alphabetically by name after adding the recipe.
   */
  @Test
  @DisplayName("Test adding a recipe to the cookbook, and ensure the list of recipes is sorted alphabetically by name after adding the recipe")
  void testAddRecipe() {
    Cookbook cookbook = new Cookbook();
    Recipe recipe1 = new Recipe("Test recipe 5", "Test description", "Test instructions", 1);
    Recipe recipe2 = new Recipe("Test recipe 1", "Test description 2", "Test instructions 2", 1);

    cookbook.addRecipe(recipe1);
    assertEquals(List.of(recipe1), cookbook.getRecipes());

    cookbook.addRecipe(recipe2);
    assertEquals(List.of(recipe2, recipe1), cookbook.getRecipes());
  }

  /**
   * Test removing a recipe from the cookbook.
   */
  @Test
  @DisplayName("Test removing a recipe from the cookbook")
  void testRemoveRecipe() {
    Cookbook cookbook = new Cookbook();
    Recipe recipe1 = new Recipe("Test recipe 5", "Test description", "Test instructions", 1);
    Recipe recipe2 = new Recipe("Test recipe 2", "Test description 2", "Test instructions 2", 1);

    cookbook.addRecipe(recipe1);
    cookbook.addRecipe(recipe2);
    assertEquals(List.of(recipe2, recipe1), cookbook.getRecipes());

    cookbook.removeRecipe(recipe2);
    assertEquals(List.of(recipe1), cookbook.getRecipes());
  }

  /**
   * Test sorting the recipes in the cookbook in ascending alphabetical order, by name.
   */
  @Test
  @DisplayName("Test sorting the recipes in the cookbook in ascending alphabetical order, by name")
  void testSortRecipes() {
    Cookbook cookbook = new Cookbook();
    Recipe recipe1 = new Recipe("Test recipe 5", "Test description", "Test instructions", 1);
    Recipe recipe2 = new Recipe("Test recipe 2", "Test description 2", "Test instructions 2", 1);
    Recipe recipe3 = new Recipe("Test recipe 3", "Test description 3", "Test instructions 3", 1);

    cookbook.addRecipe(recipe1);
    cookbook.addRecipe(recipe2);
    cookbook.addRecipe(recipe3);
    cookbook.sortRecipes();

    assertEquals(List.of(recipe2, recipe3, recipe1), cookbook.getRecipes());
  }

  /**
   * Test removing all recipes from the cookbook.
   */
  @Test
  @DisplayName("Test removing all recipes from the cookbook")
  void testRemoveAllRecipes() {
    Cookbook cookbook = new Cookbook();
    Recipe recipe1 = new Recipe("Test recipe 5", "Test description", "Test instructions", 1);
    Recipe recipe2 = new Recipe("Test recipe 2", "Test description 2", "Test instructions 2", 1);

    cookbook.addRecipe(recipe1);
    cookbook.addRecipe(recipe2);
    assertEquals(List.of(recipe2, recipe1), cookbook.getRecipes());

    cookbook.removeAllRecipes();
    assertEquals(List.of(), cookbook.getRecipes());
  }

  // ------------------------------ Negative tests ------------------------------

  /**
   * Test that the method {@code getRecipe} throws an {@code IllegalArgumentException} when the name of
   * the recipe is null or blank.
   */
  @Test
  @DisplayName("Test that the method getRecipe throws an IllegalArgumentException when the name of the recipe is null or blank")
  void testGetRecipeWithNullOrBlankName() {
    Cookbook cookbook = new Cookbook();
    Recipe recipe = new Recipe("Test recipe", "Test description", "Test instructions", 1);
    cookbook.addRecipe(recipe);

    assertThrows(IllegalArgumentException.class, () -> cookbook.getRecipe(null));
    assertThrows(IllegalArgumentException.class, () -> cookbook.getRecipe(""));
  }

  /**
   * Test that the method {@code getRecipe} throws an {@code IllegalArgumentException} when no recipe
   * with the provided name is found.
   */
  @Test
  @DisplayName("Test that the method getRecipe throws an IllegalArgumentException when no recipe with the provided name is found")
  void testGetRecipeWithNoRecipeFound() {
    Cookbook cookbook = new Cookbook();
    Recipe recipe = new Recipe("Test recipe", "Test description", "Test instructions", 1);

    cookbook.addRecipe(recipe);

    assertThrows(IllegalArgumentException.class, () -> cookbook.getRecipe("Test recipe 2"));
  }

  /**
   * Test that the method {@code addRecipe} throws an {@code IllegalArgumentException} when the recipe
   * is null.
   */
  @Test
  @DisplayName("Test that the method addRecipe throws an IllegalArgumentException when the recipe is null")
  void testAddRecipeWithNullRecipe() {
    Cookbook cookbook = new Cookbook();

    assertThrows(IllegalArgumentException.class, () -> cookbook.addRecipe(null));
  }

  /**
   * Test that the method {@code addRecipe} throws an {@code IllegalArgumentException} when the recipe
   * already exists in the cookbook.
   */
  @Test
  @DisplayName("Test that the method addRecipe throws an IllegalArgumentException when the recipe already exists in the cookbook")
  void testAddRecipeWithRecipeAlreadyExists() {
    Cookbook cookbook = new Cookbook();
    Recipe recipe = new Recipe("Test recipe", "Test description", "Test instructions", 1);

    cookbook.addRecipe(recipe);
    assertThrows(IllegalArgumentException.class, () -> cookbook.addRecipe(recipe));
  }

  /**
   * Test that the method {@code removeRecipe} throws an {@code IllegalArgumentException} when the recipe
   * is null.
   */
  @Test
  @DisplayName("Test that the method removeRecipe throws an IllegalArgumentException when the recipe is null")
  void testRemoveRecipeWithNullRecipe() {
    Cookbook cookbook = new Cookbook();

    assertThrows(IllegalArgumentException.class, () -> cookbook.removeRecipe(null));
  }

  /**
   * Test that the method {@code removeRecipe} throws an {@code IllegalArgumentException} when the recipe
   * does not exist in the cookbook.
   */
  @Test
  @DisplayName("Test that the method removeRecipe throws an IllegalArgumentException when the recipe does not exist in the cookbook")
  void testRemoveRecipeWithNoRecipeFound() {
    Cookbook cookbook = new Cookbook();
    Recipe recipe = new Recipe("Test recipe", "Test description", "Test instructions", 1);

    assertThrows(IllegalArgumentException.class, () -> cookbook.removeRecipe(recipe));
  }
}