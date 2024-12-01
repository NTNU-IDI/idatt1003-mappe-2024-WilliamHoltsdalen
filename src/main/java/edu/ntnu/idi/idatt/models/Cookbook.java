package edu.ntnu.idi.idatt.models;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Cookbook {
  private static final String NULL_RECIPE_ERROR = "Recipe cannot be null";
  private static final String NULL_RECIPES_ERROR = "Recipes cannot be null";
  private static final String RECIPE_NOT_FOUND_ERROR = "Recipe was not found";
  private static final String NULL_OR_BLANK_NAME = "Name cannot be null or blank";

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
   * Returns a list of all recipes in the cook book, sorted alphabetically by name.
   *
   * @return a list of all recipes in the cook book, sorted alphabetically by name
   */
  public List<Recipe> getSortedRecipes() {
    List<Recipe> sortedRecipes = new java.util.ArrayList<>(recipes);
    sortedRecipes.sort(Comparator.comparing(Recipe::getName));
    return sortedRecipes;
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
   * Searches for recipes that contain the given keyword in their name.
   *
   * @param keyword the keyword to search for (case-insensitive).
   * @return a list of matching recipes.
   */
  public List<Recipe> searchRecipesByKeyword(String keyword) {
    if (keyword == null || keyword.isBlank()) {
      throw new IllegalArgumentException("Keyword cannot be null or blank");
    }

    String lowerCaseKeyword = keyword.toLowerCase();

    // Filter recipes where the name contains the keyword (case-insensitive)
    return recipes.stream()
        .filter(recipe -> recipe.getName().toLowerCase().contains(lowerCaseKeyword))
        .collect(Collectors.toList());
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

  /**
   * Removes all recipes from the cook book.
   */
  public void removeAllRecipes() {
    recipes.clear();
  }
}

