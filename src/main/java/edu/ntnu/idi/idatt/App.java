package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.views.TextUserInterface;

public class App {
    public static void main(String[] args) {

        // Testing the TextUserInterface class
        TextUserInterface tui = new TextUserInterface();
        tui.init();
        tui.start();
    }
}