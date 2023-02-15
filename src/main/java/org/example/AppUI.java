package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class AppUI {

  public static String getUserInput() {
    Scanner userInput = new Scanner(System.in);
    return userInput.nextLine();
  }

  public static String listTasks(ArrayList<String> list, String taskState) {
    StringBuilder result = new StringBuilder("\n " + taskState + " tasks list\n");

    for (String task : list) {
      result.append(task).append("\n");
    }

    return result.toString();
  }

  public static String mainMenu() {
    return new StringBuilder()
      .append("1 - Show tasks list\n")
      .append("2 - Add a new task\n")
      .append("3 - Start a task\n")
      .append("4 - See results of completed tasks\n")
      .append("0 - Quit program\n")
      .toString();
  }

  static class MenuMessages {

    public final static String chooseValidOption = "Sorry, you have to choose one valid option";
    public static final String enterNumber = "Enter a number to calculate the Fibonacci sequence :";
    public static final String pressEnterToContinue = "Press <ENTER> to continue";
    public static final String selectTask = "Select a task number from the list or just <ENTER> to go back to main menu :";
  }

  static class MenuOptions {

    public static final int showAllTasks = 1;
    public static final int addNewTask = 2;
    public static final int startTask = 3;
    public static final int showCompletedTasks = 4;
    public static final int quitProgram = 0;
  }
}
