package edu.ntnu.idi.idatt.utils;

/**
 * A utility class for validating various inputs.
 *
 * @author WilliamHoltsdalen
 * @since V1.3
 */
public final class ValidationUtils {

  /** Private constructor to prevent instantiation, as this is a utility class. */
  private ValidationUtils() {}

  /**
   * Checks if an integer is valid, meaning it is not a negative number.
   *
   * @param integer the integer to check
   * @return true if the integer is valid, false otherwise
   */
  public static boolean isValidPositiveInteger(int integer) {
    return integer >= 0;
  }

  /**
   * Checks if a double is valid, meaning it not a negative number.
   *
   * @param doubleValue the double to check
   * @return true if the double is valid, false otherwise
   */
  public static boolean isValidPositiveDouble(double doubleValue) {
    return doubleValue >= 0;
  }

  /**
   * Checks if a string is valid, meaning it is not null and not empty.
   *
   * @param string the string to check
   * @return true if the string is valid, false otherwise
   */
  public static boolean isValidString(String string) {
    return string != null && !string.isBlank();
  }
}