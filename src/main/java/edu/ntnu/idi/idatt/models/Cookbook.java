package edu.ntnu.idi.idatt.models;

import java.util.Comparator;
import java.util.List;

public class Cookbook {
  private static final String NULL_RECIPE_ERROR = "Recipe cannot be null";
  private static final String RECIPE_NOT_FOUND_ERROR = "Recipe was not found";
  private static final String NULL_OR_BLANK_NAME = "Name cannot be null or blank";
  private static final String RECIPE_ALREADY_EXISTS_ERROR = "Recipe already exists";

  private final List<Recipe> recipes;

  /**
   * Constructs a new empty cook book.
   */
  public Cookbook() {
    this.recipes = new java.util.ArrayList<>();
  }

  /**
   * Returns a list of all recipes in the cook book.
   *
   * @return a list of all recipes in the cook book
   */
  public List<Recipe> getRecipes() {
    return recipes;
  }

  /**
   * Returns the recipe with the provided name. If no recipe with the provided name is found, or
   * the name is null or blank, an IllegalArgumentException is thrown.
   *
   * @param name the name of the recipe
   * @return the recipe with the provided name
   * @throws IllegalArgumentException if no recipe with the provided name is found, or if the name
   *                                 is null or blank
   */
  public Recipe getRecipe(String name) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_NAME);
    }
    return recipes.stream()
      .filter(recipe -> recipe.getName().equals(name))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException(RECIPE_NOT_FOUND_ERROR));
  }

  /**
   * Adds a recipe to the cook book. After adding the recipe, the list of recipes is sorted
   * alphabetically by name.
   *
   * @param recipe the Recipe object to add
   * @throws IllegalArgumentException if the recipe object is null
   */
  public void addRecipe(Recipe recipe) throws IllegalArgumentException {
    if (recipe == null) {
      throw new IllegalArgumentException(NULL_RECIPE_ERROR);
    }
    if (recipes.contains(recipe)) {
      throw new IllegalArgumentException(RECIPE_ALREADY_EXISTS_ERROR);
    }
    recipes.add(recipe);
    sortRecipes();
  }

  /**
   * Removes a recipe from the cook book. If the recipe object is null, or the recipe does not exist
   * in the cook book, an IllegalArgumentException is thrown.
   *
   * @param recipe the Recipe object to remove from the cook book
   * @throws IllegalArgumentException if the recipe object is null or if the recipe does not exist
   *                                  in the cook book.
   */
  public void removeRecipe(Recipe recipe) throws IllegalArgumentException {
    if (recipe == null) {
      throw new IllegalArgumentException(NULL_RECIPE_ERROR);
    }
    if (!recipes.contains(recipe)) {
      throw new IllegalArgumentException(RECIPE_NOT_FOUND_ERROR);
    }
    recipes.remove(recipe);
  }

  /**
   * Sorts the recipes in the cook book by name, in alphabetical order.
   */
  public void sortRecipes() {
    recipes.sort(Comparator.comparing(Recipe::getName));
  }

  /**
   * Removes all recipes from the cook book.
   */
  public void removeAllRecipes() {
    recipes.clear();
  }
}