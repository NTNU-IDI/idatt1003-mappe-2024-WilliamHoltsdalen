package edu.ntnu.idi.idatt.models;

/**
 * An interface representing a food item.
 * <p>
 * Provides methods for getting the name, category, and unit of a food item.
 * The interface is used to ensure that all food items have these three attributes.
 *
 * @author WilliamHoltsdalen
 * @since V0.2
 */
public interface FoodItem {
  /**
   * Gets the name of the food item.
   *
   * @return the name of the food item as a {@code String}.
   */
  String getName();

  /**
   * Gets the category of the food item.
   * <p>
   * Categories might include, for example, "Fruit", "Vegetable", or "Dairy".
   *
   * @return the category of the food item as a {@code String}.
   */
  String getCategory();

  /**
   * Gets the unit of measurement for the food item.
   * <p>
   * Units might include, for example, "kg", "liters", or "pieces".
   *
   * @return the unit of the food item as a {@code String}.
   */
  String getUnit();
}