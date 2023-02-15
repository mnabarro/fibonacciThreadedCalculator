package org.example;

import java.util.Scanner;

public class AppMenu {

  class MenuMessages {
    public final static String chooseValidOption = "Sorry, you have to choose one valid option";
    public static final String pressEnterToContinue = "Press <ENTER> to continue";
  }
  class MenuOptions {

    public static final String showTaskList = "1";
    public static final String addNewTask = "2";
    public static final String startTask = "3";
    public static final String seeResults = "4";
    public static final String quitProgram = "Q";
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
      .append("Q - Quit program\n")
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
