package edu.ntnu.idi.idatt.models;

public class Ingredient implements FoodItem {
  private static final String NULL_OR_BLANK_NAME_ERROR = "Name cannot be null or blank";
  private static final String NULL_OR_BLANK_CATEGORY_ERROR = "Category cannot be null or blank";
  private static final String NULL_OR_BLANK_UNIT_ERROR = "Unit cannot be null or blank";
  private static final String NON_POSITIVE_AMOUNT_ERROR = "Amount cannot be negative or zero";

  private final String name;
  private final String category;
  private final String unit;
  private final double amount;

  public Ingredient(String name, String category, String unit, double amount) {
    // Guard clauses
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

    this.name = name;
    this.category = category;
    this.unit = unit;
    this.amount = amount;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getCategory() {
    return category;
  }

  @Override
  public String getUnit() {
    return unit;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException(NON_POSITIVE_AMOUNT_ERROR);
    }
  }

}
