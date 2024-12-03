package edu.ntnu.idi.idatt.services;

import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.views.TextUserInterface;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.InterfaceUtil;
import java.util.HashSet;
import java.util.stream.Collectors;
import edu.ntnu.idi.idatt.models.Ingredient;

import java.util.List;

/**
 * A service class allowing for a user interface, like {@link TextUserInterface}, to interact with
 * a {@link Cookbook} object.
 * <p>
 * Provides the following functionality:
 * <ul>
 * <li>Finding a recipe by name
 * <li>Searching for recipes by ingredients
 * <li>Adding a new recipe
 * <li>Editing a recipe
 * <li>Removing a recipe
 * <li>Showing all recipes
 * </ul>
 *
 * @see TextUserInterface
 * @see Cookbook
 *
 * @author WilliamHoltsdalen
 * @since 0.2
 */
public class CookbookMenuService {
  final Cookbook cookbook;

  /**
   * Constructs a new cookbook menu service with the provided cookbook.
   *
   * @param cookbook the cookbook object to use
   *
   * @throws IllegalArgumentException if the provided cookbook is null.
   */
  public CookbookMenuService(Cookbook cookbook) {
    if (cookbook == null) {
      throw new IllegalArgumentException("Cookbook cannot be null");
    }
    this.cookbook = cookbook;
  }

  /**
   * Finds a recipe by name in the cookbook, and prints it to the console.
   * <p>
   * The method prompts the user to enter the name of the recipe to find. If the recipe is found,
   * the method prints the recipe to the console. If the recipe is not found, the method prints an
   * error message.
   */
  public void caseFindRecipeByName() {
    System.out.println("Enter the name of the recipe you want to find: ");
    final String name = InterfaceUtil.stringInput();
    try {
      final Recipe recipe = cookbook.getRecipe(name);
      System.out.println("Recipe found:");
      System.out.println(recipe);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Searches for recipes by ingredients in the cookbook, and prints them to the console.
   * <p>
   * The method prompts the user to enter the ingredients to search for (comma-separated). If no
   * recipes are found with the provided ingredients, the method prints an error message. Otherwise,
   * the method prints the recipes to the console.
   */
  public void caseSearchRecipesByIngredients() {
    System.out.println("Enter the ingredients you want to search for (comma-separated): ");
    final String ingredients = InterfaceUtil.stringInput();
    List<String> ingredientList = List.of(ingredients.replace(" ", "").split(","));
    List<Recipe> recipes = cookbook.getRecipes().stream()
      .filter(recipe -> recipe.getIngredients().stream()
        .map(Ingredient::getName)
        .collect(Collectors.toSet())
        .containsAll(new HashSet<>(ingredientList)))
      .toList();

    if (recipes.isEmpty()) {
      System.out.println("No recipes found");
      return;
    }
    System.out.println("\nRecipes found:");
    recipes.forEach(System.out::println);
  }

  /**
   * Adds a new recipe to the cookbook.
   * <p>
   * The method prompts the user to enter the name, description, instructions, and number of
   * servings of the recipe. After creating a recipe with these parameters, the user is asked to enter
   * the ingredients for the recipe, allowing them to add multiple ingredients. If the user enters
   * "no" when asked if they want to add another ingredient, the method finishes adding the ingredients to the
   * newly created recipe. If a recipe with the same name already exists in the cookbook, the method
   * prints an error message.
   */
  public void caseAddNewRecipe() {
    try {
      System.out.println("Enter the name of the recipe: ");
      final String name = InterfaceUtil.stringInput();
      if (cookbook.getRecipes().stream().anyMatch(recipe -> recipe.getName().equals(name))) {
        throw new IllegalArgumentException("Recipe already exists in cookbook.");
      }

      System.out.println("Enter the description of the recipe: ");
      final String description = InterfaceUtil.stringInput();
      System.out.println("Enter the instructions for the recipe: ");
      final String instructions = InterfaceUtil.stringInput();
      System.out.println("Enter the number of servings: ");
      final int servings = InterfaceUtil.integerInput();

      Recipe recipe = new Recipe(name, description, instructions, servings);

      System.out.println("Enter the ingredients for the recipe. ");
      boolean finished = false;
      for (int i = 1; !finished; i++) {
        System.out.printf("Enter the name of ingredient #%d: ", i);
        final String ingredientName = InterfaceUtil.stringInput();

        System.out.printf("Enter the category of ingredient #%d: ", i);
        final String ingredientCategory = InterfaceUtil.stringInput();

        System.out.printf("Enter the unit of ingredient #%d: ", i);
        final String ingredientUnit = InterfaceUtil.stringInput();

        System.out.printf("Enter the amount of ingredient #%d: ", i);
        final double ingredientAmount = InterfaceUtil.doubleInput();

        Ingredient ingredient = new Ingredient(ingredientName, ingredientCategory, ingredientUnit,
            ingredientAmount);
        recipe.addIngredient(ingredient);

        System.out.println("Do you want to add another ingredient? (yes/no)");
        final String answer = InterfaceUtil.stringInput();
        if (answer.equals("no")) {
          finished = true;
        }
      }

      cookbook.addRecipe(recipe);
      System.out.println("Recipe added successfully");
    } catch (IllegalArgumentException e) {
      System.out.println("Failed to add recipe: ");
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * Edits a recipe in the cookbook.
   * <p>
   * The method prompts the user to enter the name of the recipe to edit. If the recipe is not found,
   * the method prints an error message. If the recipe is found, the method prompts the user to
   * choose an option to edit. The user can edit the name, description, instructions, and number of
   * servings of the recipe. The user cna also choose to add or remove an ingredient, as well as
   * edit the amount of an ingredient.
   */
  public void caseEditRecipe() {
    System.out.println("Enter the name of the recipe you want to edit: ");
    final String recipeName = InterfaceUtil.stringInput();
    try {
      final Recipe recipe = cookbook.getRecipe(recipeName);
      System.out.println("Recipe found:");
      System.out.println(recipe);

      InterfaceUtil.promptRecipeEditMenu();
      final int editChoice = InterfaceUtil.integerInput();

      switch (editChoice) {
        case 1:
          System.out.println("Enter the new name: ");
          final String newName = InterfaceUtil.stringInput();
          recipe.setName(newName);
          break;
        case 2:
          System.out.println("Enter the new description: ");
          final String newDescription = InterfaceUtil.stringInput();
          recipe.setDescription(newDescription);
          break;
        case 3:
          System.out.println("Enter the new instructions: ");
          final String newInstructions = InterfaceUtil.stringInput();
          recipe.setInstructions(newInstructions);
          break;
        case 4:
          System.out.println("Enter the name of the ingredient you want to add: ");
          final String ingredientName = InterfaceUtil.stringInput();
          System.out.println("Enter the category of the ingredient you want to add: ");
          final String ingredientCategory = InterfaceUtil.stringInput();
          System.out.println("Enter the unit of the ingredient you want to add: ");
          final String ingredientUnit = InterfaceUtil.stringInput();
          System.out.println("Enter the amount of the ingredient you want to add: ");
          final double ingredientAmount = InterfaceUtil.doubleInput();
          recipe.addIngredient(new Ingredient(ingredientName, ingredientCategory, ingredientUnit,  ingredientAmount));
          break;
        case 5:
          System.out.println("Enter the name of the ingredient to edit the amount of: ");
          final String ingredientToEditName = InterfaceUtil.stringInput();
          Ingredient ingredient = cookbook.getRecipe(recipeName).getIngredient(ingredientToEditName);
          System.out.printf("Enter the new amount for the ingredient (currently %.2f): %n", ingredient.getAmount());
          final double newAmount = InterfaceUtil.doubleInput();
          try {
            ingredient.setAmount(newAmount);
          } catch (IllegalArgumentException e) {
            System.out.println("Could not edit the amount.");
            System.out.println("Error: " + e.getMessage());
            return;
          }
          break;
        case 6:
          System.out.println("Enter the name of the ingredient you want to remove: ");
          final String ingredientToRemove = InterfaceUtil.stringInput();
          cookbook.getRecipe(recipeName).removeIngredient(cookbook.getRecipe(recipeName).getIngredient(ingredientToRemove));
          break;
        case 7:
          System.out.println("Enter the new amount of servings: ");
          final int newServings = InterfaceUtil.integerInput();
          recipe.setServings(newServings);
          break;
        default:
          System.out.println("Invalid choice");
          break;
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return;
    }
    System.out.println("Recipe edited successfully");
  }

  /**
   * Removes a recipe from the cookbook.
   * <p>
   * The method prompts the user to enter the name of the recipe to remove. If the recipe is not
   * found, the method prints an error message. Otherwise, the method removes the recipe from the
   * cookbook.
   */
  public void caseRemoveRecipe() {
    System.out.println("Enter the name of the recipe you want to remove: ");
    final String name = InterfaceUtil.stringInput();
    try {
      cookbook.removeRecipe(cookbook.getRecipe(name));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return;
    }
    System.out.println("Recipe removed successfully");
  }

  /**
   * Shows all recipes in the cookbook, and prints them to the console.
   * <p>
   * The method prints all recipes in the cookbook to the console. If there are no recipes in the
   * cookbook, the method prints an error message.
   */
  public void caseShowAllRecipes() {
    if (cookbook.getRecipes().isEmpty()) {
      System.out.println("There are no recipes in the cookbook.");
      return;
    }
    System.out.println("All recipes:");
    for (Recipe recipe : cookbook.getRecipes()) {
      System.out.printf("""
          -------------------------------------------
          %s
          """, recipe);
    }
  }
}