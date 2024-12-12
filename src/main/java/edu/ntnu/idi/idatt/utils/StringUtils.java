package edu.ntnu.idi.idatt.utils;

/**
 * A utility class for manipulating strings.
 *
 * @author WilliamHoltsdalen
 * @since V1.3
 */
public final class StringUtils {

  /** Private constructor to prevent instantiation, as this is a utility class. */
  private StringUtils() {}

  /**
   * Capitalizes the first letter of a string.
   *
   * @param string the string to capitalize
   * @return the capitalized string, or null if the string is null or blank
   */
  public static String capitalize(String string) {
    if (string == null || string.isBlank()) {
      return null;
    }
    return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
  }
}
