package org.example;

import static java.lang.Thread.State.NEW;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Optional;
import org.example.AppUI.MenuMessages;
import org.example.AppUI.MenuOptions;

public class Main {
  enum AppState {MAIN_MENU, ADD_TASK, START_TASK, LIST_ALL_TASKS, LIST_COMPLETED_TASKS}
  enum TasksToSelect {ALL, NEW, TERMINATED}

  static AppState appState = AppState.MAIN_MENU;
  static Integer numberToCalculate = 0;
  static Integer selection = 0;
  static ArrayList<Optional<MyThread>> threadsCollection = new ArrayList<>();
  static String userInput = "";

  public static void main(String[] args) {
    while (true) {
      switch (appState) {
        case MAIN_MENU -> showMainMenu();
        case ADD_TASK -> addTask();
        case START_TASK -> startTask();
        case LIST_ALL_TASKS -> listAllTasks();
        case LIST_COMPLETED_TASKS -> listCompletedTasks();
      }
    }
  }
  private static void addTask() {
    System.out.println(MenuMessages.enterNumber);
    numberToCalculate = Integer.valueOf(AppUI.getUserInput());
    threadsCollection.add(Optional.of(new MyThread("Task #" + threadsCollection.size(), numberToCalculate)));
    appState = AppState.MAIN_MENU;
  }

  private static void listAllTasks() {
    System.out.println(AppUI.listTasks(getTaskList(TasksToSelect.ALL), "All"));
    System.out.println(MenuMessages.pressEnterToContinue);
    AppUI.getUserInput();
    appState = AppState.MAIN_MENU;
  }

  private static void listCompletedTasks() {
    System.out.println(AppUI.listTasks(getTaskList(TasksToSelect.TERMINATED), "Finished"));
    System.out.println(MenuMessages.pressEnterToContinue);
    AppUI.getUserInput();
    appState = AppState.MAIN_MENU;
  }

  private static void showMainMenu() {
    try {
      System.out.println(AppUI.mainMenu());
      selection = Integer.valueOf(AppUI.getUserInput());
      switch (selection) {
        case MenuOptions.addNewTask -> appState = AppState.ADD_TASK;
        case MenuOptions.startTask -> appState = AppState.START_TASK;
        case MenuOptions.showAllTasks -> appState = AppState.LIST_ALL_TASKS;
        case MenuOptions.showCompletedTasks -> appState = AppState.LIST_COMPLETED_TASKS;
        case MenuOptions.quitProgram -> System.exit(0);
        default -> System.out.println(MenuMessages.chooseValidOption);
      }
    } catch (Exception e) {
      System.out.println(MenuMessages.chooseValidOption);
    }
  }

  private static void startTask() {
    System.out.println(AppUI.listTasks(getTaskList(TasksToSelect.NEW), "Not yet started"));
    System.out.println(MenuMessages.selectTask);

    userInput = AppUI.getUserInput();
    if (userInput.isEmpty()) {
      appState = AppState.MAIN_MENU;
      return;
    }

    selection = Integer.valueOf(userInput);

    if (threadsCollection.get(selection).isPresent() && threadsCollection.get(selection).get().getState() == NEW) {
      threadsCollection.get(selection).get().start();
    } else {
      System.out.println(MenuMessages.chooseValidOption);
    }
    appState = AppState.MAIN_MENU;
  }

  private static ArrayList<String> getTaskList(TasksToSelect state) {
    ArrayList<String> result = new ArrayList<>();
    for (Optional<MyThread> task : threadsCollection) {
      if (task.get().getState().toString().equals(state.name()) || state.equals(TasksToSelect.ALL)) {
        addTaskInfoToList(result, task);
      }
    }
    return result;
  }

  private static void addTaskInfoToList(ArrayList<String> result, Optional<MyThread> task) {
    String line;
    line = "Name :" + task.get().getName() + " - id :" + task.get().getId() + " - State :" + task.get().getState();
    if (task.get().getState() == State.TERMINATED) {
      line = line + " - Result :" + task.get().getResult();
    }
    result.add(line);
  }
}
