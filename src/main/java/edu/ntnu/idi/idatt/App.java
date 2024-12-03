package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.views.TextUserInterface;

/**
 * The main class of the application. This class works as a wrapper class, containing the main
 * method that initializes and starts the text user interface.
 *
 * @see TextUserInterface
 *
 * @author WilliamHoltsdalen
 * @since 0.1
 */
public class App {

  /**
   * The main method of the application. It initializes and starts the text user interface.
   *
   * @see TextUserInterface
   * @param args the command line arguments passed to the application
   *
   */
  public static void main(String[] args) {
    TextUserInterface tui = new TextUserInterface();
    tui.init();
    tui.start();
  }
}