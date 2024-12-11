package edu.ntnu.idi.idatt.models;

/**
 * A class representing an ingredient.
 *
 * <p>
 * Provides methods for getting the name, category, unit, and amount of an ingredient.
 * Also provides a method for setting the amount of an ingredient.
 *
 * <p>
 * The class implements the {@link FoodItem} interface, which provides methods for getting the name,
 * category, and unit of a food item.
 *
 * @see FoodItem
 *
 * @author WilliamHoltsdalen
 * @since 0.2
 */
public class Ingredient implements FoodItem {
  private static final String NULL_OR_BLANK_NAME_ERROR = "Name cannot be null or blank";
  private static final String NULL_OR_BLANK_CATEGORY_ERROR = "Category cannot be null or blank";
  private static final String NULL_OR_BLANK_UNIT_ERROR = "Unit cannot be null or blank";
  private static final String NON_POSITIVE_AMOUNT_ERROR = "Amount cannot be negative or zero";

  private String name;
  private String category;
  private String unit;
  private double amount;

  /**
   * Constructs a new ingredient with the provided name, category, unit, and amount.
   *
   * <p>
   * The method validates the provided parameters and initializes the ingredient with the provided
   * values if they are all valid. If any of the provided String parameters are null or an empty
   * string, or if the amount is negative or zero, the method throws an
   * {@code IllegalArgumentException}.
   *
   * @param name the name of the ingredient
   * @param category the category of the ingredient
   * @param unit the unit of the ingredient
   * @param amount the amount of the ingredient
   * @throws IllegalArgumentException if any of the provided String parameters are null or an empty
   *         string, or if the amount is negative or zero.
   */
  public Ingredient(String name, String category, String unit, double amount)
      throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_NAME_ERROR);
    }
    if (category == null || category.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_CATEGORY_ERROR);
    }
    if (unit == null || unit.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_UNIT_ERROR);
    }
    if (amount <= 0) {
      throw new IllegalArgumentException(NON_POSITIVE_AMOUNT_ERROR);
    }

    setName(name);
    setCategory(category);
    setUnit(unit);
    setAmount(amount);
  }

  /**
   * Returns the name of the ingredient.
   *
   * @return the name of the ingredient
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns the category of the ingredient.
   *
   * @return the category of the ingredient
   */
  @Override
  public String getCategory() {
    return category;
  }

  /**
   * Returns the unit of the ingredient.
   *
   * @return the unit of the ingredient
   */
  @Override
  public String getUnit() {
    return unit;
  }

  /**
   * Returns the amount of the ingredient.
   *
   * @return the amount of the ingredient
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Private method for setting the name of the ingredient.
   *
   * @param name the name of the ingredient
   * @throws IllegalArgumentException if the name is null or an empty string.
   */
  private void setName(String name) throws IllegalArgumentException {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_NAME_ERROR);
    }
    this.name = name;
  }

  /**
   * Private method for setting the category of the ingredient.
   *
   * @param category the category of the ingredient
   * @throws IllegalArgumentException if the category is null or an empty string.
   */
  private void setCategory(String category) throws IllegalArgumentException {
    if (category == null || category.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_CATEGORY_ERROR);
    }
    this.category = category;
  }

  /**
   * Private method for setting the unit of the ingredient.
   *
   * @param unit the unit of the ingredient
   * @throws IllegalArgumentException if the unit is null or an empty string.
   */
  private void setUnit(String unit) throws IllegalArgumentException {
    if (unit == null || unit.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_UNIT_ERROR);
    }
    this.unit = unit;
  }

  /**
   * Sets the amount of the ingredient.
   *
   * <p>
   * If the provided amount is less than or equal to zero, the method throws an
   * {@code IllegalArgumentException}. Otherwise, it sets the amount of the ingredient to the
   * provided value.
   *
   * @param amount the new amount of the ingredient
   * @throws IllegalArgumentException if the amount is less than or equal to zero.
   */
  public void setAmount(double amount) throws IllegalArgumentException {
    if (amount <= 0) {
      throw new IllegalArgumentException(NON_POSITIVE_AMOUNT_ERROR);
    }
    this.amount = amount;
  }

  /**
   * Overrides the default {@code toString} method, and returns a user-friendly string
   * representation of the ingredient, in the format: "Name (Category): Amount Unit".
   *
   * @return a string representation of the ingredient. Including name, category, amount, and unit.
   */
  @Override
  public String toString() {
    return String.format("%s (%s): %.2f %s", name, category, amount, unit);
  }
}