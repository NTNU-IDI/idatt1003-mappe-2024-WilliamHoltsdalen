package edu.ntnu.idi.idatt.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a recipe. A recipe consists of a name, description, instructions,
 * ingredients and amount of servings.
 * <p>
 * Provides methods for getting the name, description, instructions, ingredients, and amount of
 * servings of a recipe object. Also provides methods for adding and removing ingredients, and for setting
 * the name, description, instructions, and amount of servings of a recipe.
 *
 * @see Ingredient
 *
 * @author WilliamHoltsdalen
 * @since 0.2
 */
public class Recipe {
  private static final String NULL_INGREDIENT_ERROR = "Ingredient cannot be null";
  private static final String INGREDIENT_NOT_FOUND_ERROR = "Ingredient was not found";
  private static final String NULL_OR_BLANK_NAME = "Name cannot be null or blank";
  private static final String NULL_OR_BLANK_DESCRIPTION = "Description cannot be null or blank";
  private static final String NULL_OR_BLANK_INSTRUCTIONS = "Instructions cannot be null or blank";
  private static final String NON_POSITIVE_SERVINGS_ERROR = "Servings must be a positive number";

  private String name;
  private String description;
  private String instructions;
  private final Map<String, Ingredient> ingredients;
  private int servings;

  /**
   * Constructs a new recipe with the provided name, description, instructions, and number of
   * servings.
   * <p>
   * The method validates the provided parameters and initializes the recipe with the provided
   * values if they are all valid. If any of the provided parameters are null or an empty string,
   * the method throws an {@code IllegalArgumentException}.
   * <p>
   * The method also initializes an empty map to store the ingredients of the recipe.
   *
   * @param name the name of the recipe
   * @param description the description of the recipe
   * @param instructions the instructions for the recipe
   * @param servings the number of servings of the recipe
   *
   * @throws IllegalArgumentException if any of the provided parameters are null or an empty string.
   */
  public Recipe(String name, String description, String instructions, int servings) throws IllegalArgumentException {
    // Guard clauses
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_NAME);
    }
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_DESCRIPTION);
    }
    if (instructions == null || instructions.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_INSTRUCTIONS);
    }
    if (servings <= 0) {
      throw new IllegalArgumentException(NON_POSITIVE_SERVINGS_ERROR);
    }

    this.name = name;
    this.description = description;
    this.instructions = instructions;
    this.servings = servings;
    this.ingredients = new HashMap<>();
  }

  /**
   * Returns the name of the recipe.
   *
   * @return the name of the recipe
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the description of the recipe.
   *
   * @return the description of the recipe
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the instructions for the recipe.
   *
   * @return the instructions for the recipe
   */
  public String getInstructions() {
    return instructions;
  }

  /**
   * Returns a list of all ingredients in the recipe.
   *
   * @return a list of all ingredients in the recipe
   */
  public List<Ingredient> getIngredients() {
    return ingredients.values().stream().toList();
  }

  /**
   * Returns an ingredient with the provided name.
   * <p>
   * The method checks if the ingredient with the provided name exists in the recipe. If the ingredient
   * does not exist, the method throws an {@code IllegalArgumentException}. It also checks if the
   * name is null or an empty string, and throws an {@code IllegalArgumentException} if it is.
   *
   * @param name the name of the ingredient to get
   * @return the ingredient with the provided name
   *
   * @throws IllegalArgumentException if the name is null or an empty string, or if the ingredient
   *         does not exist in the recipe.
   */
  public Ingredient getIngredient(String name) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_NAME);
    }
    if (!ingredients.containsKey(name)) {
      throw new IllegalArgumentException(INGREDIENT_NOT_FOUND_ERROR);
    }
    return ingredients.get(name);
  }

  /**
   * Returns the number of servings of the recipe.
   *
   * @return the number of servings of the recipe
   */
  public int getServings() {
    return servings;
  }

  /**
   * Sets the name of the recipe.
   * <p>
   * The method checks if the name is null or an empty string, and throws an {@code IllegalArgumentException}
   * if it is. Otherwise, it sets the name of the recipe to the provided value.
   *
   * @param name the new name of the recipe
   *
   * @throws IllegalArgumentException if the name is null or an empty string.
   */
  public void setName(String name) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_NAME);
    }
    this.name = name;
  }

  /**
   * Sets the description of the recipe.
   * <p>
   * The method checks if the description is null or an empty string, and throws an {@code IllegalArgumentException}
   * if it is. Otherwise, it sets the description of the recipe to the provided value.
   *
   * @param description the new description of the recipe
   *
   * @throws IllegalArgumentException if the description is null or an empty string.
   */
  public void setDescription(String description) throws IllegalArgumentException {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_DESCRIPTION);
    }
    this.description = description;
  }

  /**
   * Sets the instructions for the recipe.
   * <p>
   * The method checks if the instructions are null or an empty string, and throws an {@code IllegalArgumentException}
   * if it is. Otherwise, it sets the instructions for the recipe to the provided value.
   *
   * @param instructions the new instructions for the recipe
   *
   * @throws IllegalArgumentException if the instructions are null or an empty string.
   */
  public void setInstructions(String instructions) throws IllegalArgumentException {
    if (instructions == null || instructions.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_INSTRUCTIONS);
    }
    this.instructions = instructions;
  }

  /**
   * Sets the number of servings of the recipe.
   * <p>
   * The method checks if the number of servings is less than or equal to zero, and throws an {@code IllegalArgumentException}
   * if it is. Otherwise, it sets the number of servings of the recipe to the provided value.
   *
   * @param servings the new number of servings of the recipe
   *
   * @throws IllegalArgumentException if the number of servings is less than or equal to zero.
   */
  public void setServings(int servings) throws IllegalArgumentException {
    if (servings <= 0) {
      throw new IllegalArgumentException(NON_POSITIVE_SERVINGS_ERROR);
    }
    this.servings = servings;
  }

  /**
   * Adds an ingredient to the recipe.
   * <p>
   * The method checks if the provided ingredient is null, and throws an
   * {@code IllegalArgumentException} if it is. If the ingredient already exists in the recipe, the
   * method updates the amount of the ingredient by adding the new amount to the existing amount.
   * If the ingredient does not exist in the recipe, the method adds the ingredient to the recipe.
   *
   * @param ingredient the ingredient to add
   *
   * @throws IllegalArgumentException if the provided ingredient is null.
   */
  public void addIngredient(Ingredient ingredient) throws IllegalArgumentException {
    if (ingredient == null ) {
      throw new IllegalArgumentException(NULL_INGREDIENT_ERROR);
    }

    if (ingredients.containsKey(ingredient.getName())) {
      ingredients.get(ingredient.getName()).setAmount(
          ingredients.get(ingredient.getName()).getAmount() + ingredient.getAmount());
    }

    ingredients.put(ingredient.getName(), ingredient);
  }

  /**
   * Removes an ingredient from the recipe.
   * <p>
   * The method checks if the ingredient is null, and throws an {@code IllegalArgumentException} if
   * it is. If the ingredient does not exist in the recipe, the method also throws an
   * {@code IllegalArgumentException}. Otherwise, the method removes the ingredient from the recipe.
   *
   * @param ingredient the ingredient to remove
   *
   * @throws IllegalArgumentException if the ingredient is null, or if the ingredient does not exist
   *         in the recipe.
   */
  public void removeIngredient(Ingredient ingredient) throws IllegalArgumentException {
    if (ingredient == null) {
      throw new IllegalArgumentException(NULL_INGREDIENT_ERROR);
    }
    if (!ingredients.containsKey(ingredient.getName())) {
      throw new IllegalArgumentException(INGREDIENT_NOT_FOUND_ERROR);
    }

    ingredients.remove(ingredient.getName());
  }

  /**
   * Overrides the default {@code toString} method, and returns a user-friendly string
   * representation of the recipe.
   *
   * @return a string representation of the recipe. Including name, description, instructions, and
   *         servings. Also includes a list of ingredients, with the details of each ingredient.
   */
  @Override
  public String toString() {
    String ingredientsString = ingredients.values().stream()
        .map(Ingredient::toString)
        .reduce("", (acc, ingredient) -> acc + ingredient + "\n");

    return String.format("""
            Name: %s
            Description: %s
            Servings: %d
            
            Instructions: 
            %s
            
            Ingredients: 
            %s
            """, name, description, servings, instructions, ingredientsString);
  }
}