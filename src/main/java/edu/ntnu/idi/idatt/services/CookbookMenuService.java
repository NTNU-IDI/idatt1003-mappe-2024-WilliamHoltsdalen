package edu.ntnu.idi.idatt.services;

import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.InterfaceUtils;
import java.util.HashSet;
import java.util.stream.Collectors;
import edu.ntnu.idi.idatt.models.Ingredient;

import java.util.List;

public class CookbookMenuService {
  final Cookbook cookbook;

  public CookbookMenuService(Cookbook cookbook) {
    this.cookbook = cookbook;
  }

  public void caseFindRecipeByName() {
    System.out.println("Enter the name of the recipe you want to find: ");
    final String name = InterfaceUtils.stringInput();
    try {
      final Recipe recipe = cookbook.getRecipe(name);
      System.out.println("Recipe found:");
      System.out.println(recipe);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  public void caseSearchRecipesByIngredients() {
    System.out.println("Enter the ingredients you want to search for (comma-separated): ");
    final String ingredients = InterfaceUtils.stringInput();
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
    System.out.println("Recipes found:");
    recipes.forEach(System.out::println);
  }

  public void caseAddNewRecipe() {
    try {
      System.out.println("Enter the name of the recipe: ");
      final String name = InterfaceUtils.stringInput();
      System.out.println("Enter the description of the recipe: ");
      final String description = InterfaceUtils.stringInput();
      System.out.println("Enter the instructions for the recipe: ");
      final String instructions = InterfaceUtils.stringInput();
      System.out.println("Enter the number of servings: ");
      final int servings = InterfaceUtils.integerInput();

      Recipe recipe = new Recipe(name, description, instructions, servings);

      System.out.println("Enter the ingredients for the recipe. ");
      boolean finished = false;
      for (int i = 1; !finished; i++) {
        System.out.printf("Enter the name of ingredient #%d: ", i);
        final String ingredientName = InterfaceUtils.stringInput();

        System.out.printf("Enter the category of ingredient #%d: ", i);
        final String ingredientCategory = InterfaceUtils.stringInput();

        System.out.printf("Enter the unit of ingredient #%d: ", i);
        final String ingredientUnit = InterfaceUtils.stringInput();

        System.out.printf("Enter the amount of ingredient #%d: ", i);
        final double ingredientAmount = InterfaceUtils.doubleInput();

        Ingredient ingredient = new Ingredient(ingredientName, ingredientCategory, ingredientUnit,
            ingredientAmount);
        recipe.addIngredient(ingredient);

        System.out.println("Do you want to add another ingredient? (yes/no)");
        final String answer = InterfaceUtils.stringInput();
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

  public void caseEditRecipe() {
    System.out.println("Enter the name of the recipe you want to edit: ");
    final String name = InterfaceUtils.stringInput();
    try {
      final Recipe recipe = cookbook.getRecipe(name);
      System.out.println("Recipe found:");
      System.out.println(recipe);

      InterfaceUtils.promptRecipeEditMenu();
      final int editChoice = InterfaceUtils.integerInput();

      switch (editChoice) {
        case 1:
          System.out.println("Enter the new name: ");
          final String newName = InterfaceUtils.stringInput();
          recipe.setName(newName);
          break;
        case 2:
          System.out.println("Enter the new description: ");
          final String newDescription = InterfaceUtils.stringInput();
          recipe.setDescription(newDescription);
          break;
        case 3:
          System.out.println("Enter the new instructions: ");
          final String newInstructions = InterfaceUtils.stringInput();
          recipe.setInstructions(newInstructions);
          break;
        case 4:
          System.out.println("Enter the name of the ingredient you want to add: ");
          final String ingredientName = InterfaceUtils.stringInput();
          System.out.println("Enter the category of the ingredient you want to add: ");
          final String ingredientCategory = InterfaceUtils.stringInput();
          System.out.println("Enter the unit of the ingredient you want to add: ");
          final String ingredientUnit = InterfaceUtils.stringInput();
          System.out.println("Enter the amount of the ingredient you want to add: ");
          final double ingredientAmount = InterfaceUtils.doubleInput();
          recipe.addIngredient(new Ingredient(ingredientName, ingredientCategory, ingredientUnit,  ingredientAmount));
          break;
        case 5:
          System.out.println("Enter the name of the ingredient you want to remove: ");
          final String ingredientToRemove = InterfaceUtils.stringInput();
          cookbook.getRecipe(name).removeIngredient(cookbook.getRecipe(name).getIngredient(ingredientToRemove));
          break;
        case 6:
          System.out.println("Enter the new amount of servings: ");
          final int newServings = InterfaceUtils.integerInput();
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

  public void caseRemoveRecipe() {
    System.out.println("Enter the name of the recipe you want to remove: ");
    final String name = InterfaceUtils.stringInput();
    try {
      cookbook.removeRecipe(cookbook.getRecipe(name));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return;
    }
    System.out.println("Recipe removed successfully");
  }

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