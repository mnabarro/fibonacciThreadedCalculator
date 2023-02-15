package org.example;

import java.util.ArrayList;
import java.util.Random;
import org.example.AppUI.MenuMessages;
import org.example.AppUI.MenuOptions;

public class Main {

  enum AppStates {MAIN_MENU, ADD_TASK, START_TASK, LIST_ALL_TASKS, LIST_COMPLETED_TASKS}
  enum TaskStates {ALL, NEW, TERMINATED}
  static AppStates appState= AppStates.MAIN_MENU;
  static Integer selection = 0;
  static Integer numberToCalculate = 0;
  static ArrayList<MyThread> threadsCollection = new ArrayList<>();
  static int getRandomIntInRange(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

  public static ArrayList<String> getTaskList (TaskStates state) {
    ArrayList<String> result = new ArrayList<>();
    for (MyThread task : threadsCollection) {
      if (task.getState().toString().equals(state.name()) || state.equals(TaskStates.ALL)) {
        result.add("Name :" + task.getName() +" - id :" + task.getId() +  " - State :" + task.getState());
      }
    }
    return result;
  }
  public static void main(String[] args) {

    do {
      switch (appState) {
        case MAIN_MENU -> {
          System.out.println(AppUI.mainMenu());
          selection = Integer.valueOf(AppUI.getUserInput());

          if (selection.equals(MenuOptions.addNewTask)){
            appState = AppStates.ADD_TASK;
          }
          if (selection.equals(MenuOptions.showTaskList)){
            appState = AppStates.LIST_ALL_TASKS;
          }
          if (selection.equals(MenuOptions.quitProgram)){
            System.exit(0);
          }
        }
        case ADD_TASK -> {
          System.out.println(AppUI.addTaskMenu());
          numberToCalculate = Integer.valueOf(AppUI.getUserInput());
          threadsCollection.add(new MyThread("Task #" + threadsCollection.size(), numberToCalculate));
          appState = AppStates.MAIN_MENU;
        }

        case LIST_ALL_TASKS -> {
          System.out.println(AppUI.listTasks(getTaskList(TaskStates.ALL), "all"));
          System.out.println(MenuMessages.pressEnterToContinue);
          AppUI.getUserInput();
          appState = AppStates.MAIN_MENU;
        }

        case LIST_COMPLETED_TASKS -> {
          System.out.println(AppUI.listTasks(getTaskList(TaskStates.TERMINATED), "finished"));
          System.out.println(MenuMessages.pressEnterToContinue);
          AppUI.getUserInput();
          appState = AppStates.MAIN_MENU;
        }
      }

    } while (true);

//  threadsCollection.add(new MyThread("Thread #"+ threadsCollection.size(), getRandomIntInRange(5,50)));
//  threadsCollection.get(0).start();
  }
}
