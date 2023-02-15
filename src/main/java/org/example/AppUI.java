package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class AppUI {

  public static String listTasks(ArrayList<String> list, String taskState) {
    StringBuilder result = new StringBuilder("\n "+taskState+" tasks list\n");

    for (String task : list) {
        result.append(task).append("\n");
      }

      return result.toString();
  }

  static class MenuMessages {
    public final static String chooseValidOption = "Sorry, you have to choose one valid option";
    public static final String pressEnterToContinue = "Press <ENTER> to continue";
    public static final String selectTask = "Select a task index from the list";
  }
  static class MenuOptions {

    public static final int showAllTasks = 1;
    public static final int addNewTask = 2;
    public static final int startTask = 3;
    public static final int showCompletedTasks = 4;
    public static final int quitProgram = 0;
  }

  public static String getUserInput() {
    Scanner userInput = new Scanner(System.in);
    return userInput.nextLine();
  }
  public static String mainMenu() {
    return new StringBuilder()
      .append("\n")
      .append("1 - Show tasks list\n")
      .append("2 - Add a new task\n")
      .append("3 - Start a task\n")
      .append("4 - See results of completed tasks\n")
      .append("0 - Quit program\n")
      .toString();
  }

  public static String addTaskMenu() {
    return new StringBuilder()
      .append("\n")
      .append("Enter the number to calculate\n")
      .toString();
  }

  public static String startTaskMenu() {
    return new StringBuilder()
      .append("\n")
      .append("Enter the task number to start\n")
      .toString();
  }

}
