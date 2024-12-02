package edu.ntnu.idi.idatt.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  private Map<String, Ingredient> ingredients;
  private int servings;

  public Recipe(String name, String description, String instructions, int servings) {
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

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getInstructions() {
    return instructions;
  }

  public List<Ingredient> getIngredients() {
    return ingredients.values().stream().toList();
  }

  public Ingredient getIngredient(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_NAME);
    }
    if (!ingredients.containsKey(name)) {
      throw new IllegalArgumentException(INGREDIENT_NOT_FOUND_ERROR);
    }
    return ingredients.get(name);
  }

  public int getServings() {
    return servings;
  }

  public void setName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_NAME);
    }
    this.name = name;
  }

  public void setDescription(String description) {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_DESCRIPTION);
    }
    this.description = description;
  }

  public void setInstructions(String instructions) {
    if (instructions == null || instructions.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_INSTRUCTIONS);
    }
    this.instructions = instructions;
  }

  public void setServings(int servings) {
    if (servings <= 0) {
      throw new IllegalArgumentException(NON_POSITIVE_SERVINGS_ERROR);
    }
    this.servings = servings;
  }

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

  public void removeIngredient(Ingredient ingredient) throws IllegalArgumentException {
    if (ingredient == null) {
      throw new IllegalArgumentException(NULL_INGREDIENT_ERROR);
    }
    if (!ingredients.containsKey(ingredient.getName())) {
      throw new IllegalArgumentException(INGREDIENT_NOT_FOUND_ERROR);
    }

    ingredients.remove(ingredient.getName());
  }

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
