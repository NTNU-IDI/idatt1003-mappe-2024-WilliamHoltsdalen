package edu.ntnu.idi.idatt.models;

import java.util.Comparator;
import java.util.List;

public class CookBook {
  private static final String NULL_RECIPE_ERROR = "Recipe cannot be null";
  private static final String NULL_RECIPES_ERROR = "Recipes cannot be null";
  private static final String RECIPE_NOT_FOUND_ERROR = "Recipe was not found";
  private static final String NULL_OR_BLANK_NAME = "Name cannot be null or blank";

  private final List<Recipe> recipes;

  /**
   * Constructs a new cook book with the provided recipes.
   *
   * @param recipes the recipes to add to the cook book
   * @throws IllegalArgumentException if the provided list of recipes is null
   */
  public CookBook(List<Recipe> recipes) throws IllegalArgumentException {
    if (recipes == null) {
      throw new IllegalArgumentException(NULL_RECIPES_ERROR);
    }
    this.recipes = recipes;
  }

  /**
   * Constructs a new cook book with no recipes.
   */
  public CookBook() {
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
   * Returns the recipe with the provided name.
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
   * Returns the number of recipes in the cook book.
   *
   * @return an integer representing the number of recipes in the cook book
   */
  public int getNumberOfRecipes() {
    return recipes.size();
  }

  /**
   * Adds a recipe to the cook book.
   *
   * @param recipe the Recipe object to add
   * @throws IllegalArgumentException if the recipe object is null
   */
  public void addRecipe(Recipe recipe) throws IllegalArgumentException {
    if (recipe == null) {
      throw new IllegalArgumentException(NULL_RECIPE_ERROR);
    }
    recipes.add(recipe);
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
   * Updates a recipe in the cook book. If a recipe with the provided name does not exist in the
   * cook book, an IllegalArgumentException is thrown.
   *
   * @param name the String name of the recipe to update
   * @param recipe the recipe object to replace the existing recipe with
   * @throws IllegalArgumentException if no recipe with the provided name is found
   */
  public void updateRecipe(String name, Recipe recipe) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_NAME);
    }
    if (recipe == null) {
      throw new IllegalArgumentException(NULL_RECIPE_ERROR);
    }

    for (int i = 0; i < recipes.size(); i++) {
      if (recipes.get(i).getName().equals(name)) {
        recipes.set(i, recipe);
        return;
      }
    }
    throw new IllegalArgumentException(RECIPE_NOT_FOUND_ERROR);
  }

  /**
   * Sorts the recipes in the cook book by name, in alphabetical order.
   */
  public void sortRecipes() {
    recipes.sort(Comparator.comparing(Recipe::getName));
  }

}

