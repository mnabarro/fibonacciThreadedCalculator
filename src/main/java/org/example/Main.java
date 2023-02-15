package org.example;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import org.example.AppUI.MenuMessages;
import org.example.AppUI.MenuOptions;

public class Main {

  enum AppStates {MAIN_MENU, ADD_TASK, START_TASK, LIST_ALL_TASKS, LIST_COMPLETED_TASKS}

  enum TaskStates {ALL, NEW, TERMINATED}

  static AppStates appState = AppStates.MAIN_MENU;
  static Integer selection = 0;
  static Integer numberToCalculate = 0;
  static ArrayList<Optional<MyThread>> threadsCollection = new ArrayList<>();

  static int getRandomIntInRange(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

  public static ArrayList<String> getTaskList(TaskStates state) {
    ArrayList<String> result = new ArrayList<>();
    for (Optional<MyThread> task : threadsCollection) {
      if (task.get().getState().toString().equals(state.name()) || state.equals(TaskStates.ALL)) {
        result.add("Name :" + task.get().getName() + " - id :" + task.get().getId() + " - State :" + task.get().getState());
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

          if (selection.equals(MenuOptions.addNewTask)) {
            appState = AppStates.ADD_TASK;
          }
          if (selection.equals(MenuOptions.startTask)) {
            appState = AppStates.START_TASK;
          }
          if (selection.equals(MenuOptions.showAllTasks)) {
            appState = AppStates.LIST_ALL_TASKS;
          }
          if (selection.equals(MenuOptions.showCompletedTasks)) {
            appState = AppStates.LIST_COMPLETED_TASKS;
          }
          if (selection.equals(MenuOptions.quitProgram)) {
            System.exit(0);
          }
        }
        case ADD_TASK -> {
          System.out.println(AppUI.addTaskMenu());
          numberToCalculate = Integer.valueOf(AppUI.getUserInput());
          threadsCollection.add(Optional.of(new MyThread("Task #" + threadsCollection.size(), numberToCalculate)));
          appState = AppStates.MAIN_MENU;
        }
        case START_TASK -> {
          System.out.println(AppUI.listTasks(getTaskList(TaskStates.NEW), "Not yet started"));
          System.out.println(MenuMessages.pressEnterToContinue);
          selection = Integer.valueOf(AppUI.getUserInput());

          threadsCollection.get(selection).get().start();
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
}
