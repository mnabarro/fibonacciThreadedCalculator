package org.example;

import static java.lang.Thread.State.NEW;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Optional;
import org.example.AppUI.MenuMessages;
import org.example.AppUI.MenuOptions;

public class Main {

  static AppStates appState = AppStates.MAIN_MENU;
  static Integer numberToCalculate = 0;
  static Integer selection = 0;
  static ArrayList<Optional<MyThread>> threadsCollection = new ArrayList<>();
  static String userInput = "";

  public static ArrayList<String> getTaskList(TaskStates state) {
    String line;
    ArrayList<String> result = new ArrayList<>();
    for (Optional<MyThread> task : threadsCollection) {
      if (task.get().getState().toString().equals(state.name()) || state.equals(TaskStates.ALL)) {
        line = "Name :" + task.get().getName() + " - id :" + task.get().getId() + " - State :" + task.get().getState();
        if (task.get().getState() == State.TERMINATED) {
          line = line + " - Result :" + task.get().getResult();
        }
        result.add(line);
      }

    }
    return result;
  }

  public static void main(String[] args) {

    while (true) {
      switch (appState) {
        case MAIN_MENU -> {
          System.out.println(AppUI.mainMenu());
          selection = Integer.valueOf(AppUI.getUserInput());
          switch (selection) {
            case MenuOptions.addNewTask -> appState = AppStates.ADD_TASK;
            case MenuOptions.startTask -> appState = AppStates.START_TASK;
            case MenuOptions.showAllTasks -> appState = AppStates.LIST_ALL_TASKS;
            case MenuOptions.showCompletedTasks -> appState = AppStates.LIST_COMPLETED_TASKS;
            case MenuOptions.quitProgram -> System.exit(0);
            default -> System.out.println(MenuMessages.chooseValidOption);
          }
        }
        case ADD_TASK -> {
          System.out.println(MenuMessages.enterNumber);
          numberToCalculate = Integer.valueOf(AppUI.getUserInput());
          threadsCollection.add(Optional.of(new MyThread("Task #" + threadsCollection.size(), numberToCalculate)));
          appState = AppStates.MAIN_MENU;
        }
        case START_TASK -> {
          System.out.println(AppUI.listTasks(getTaskList(TaskStates.NEW), "Not yet started"));
          System.out.println(MenuMessages.selectTask);

          userInput = AppUI.getUserInput();
          if (userInput.isEmpty()) {
            appState = AppStates.MAIN_MENU;
            break;
          }

          selection = Integer.valueOf(userInput);

          if (threadsCollection.get(selection).isPresent() && threadsCollection.get(selection).get().getState() == NEW) {
            threadsCollection.get(selection).get().start();
          } else {
            System.out.println(MenuMessages.chooseValidOption);
          }
          appState = AppStates.MAIN_MENU;
        }
        case LIST_ALL_TASKS -> {
          System.out.println(AppUI.listTasks(getTaskList(TaskStates.ALL), "All"));
          System.out.println(MenuMessages.pressEnterToContinue);
          AppUI.getUserInput();
          appState = AppStates.MAIN_MENU;
        }
        case LIST_COMPLETED_TASKS -> {
          System.out.println(AppUI.listTasks(getTaskList(TaskStates.TERMINATED), "Finished"));
          System.out.println(MenuMessages.pressEnterToContinue);
          AppUI.getUserInput();
          appState = AppStates.MAIN_MENU;
        }
      }
    }
  }

  enum AppStates {MAIN_MENU, ADD_TASK, START_TASK, LIST_ALL_TASKS, LIST_COMPLETED_TASKS}

  enum TaskStates {ALL, NEW, TERMINATED}
}
