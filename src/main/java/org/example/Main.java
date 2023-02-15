package org.example;

import java.util.ArrayList;
import java.util.Random;
import org.example.AppMenu.MenuOptions;

public class Main {

  enum AppStates {MAIN_MENU, ADD_TASK, START_TASK, LIST_TASKS, LIST_RESULTS}
  static AppStates appState= AppStates.MAIN_MENU;
  static String selection = "";
  static Integer numberToCalculate = 0;
  static ArrayList<MyThread> threadsCollection = new ArrayList<>();
  static int getRandomIntInRange(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }
  public static void main(String[] args) {

    do {
      switch (appState) {
        case MAIN_MENU -> {
          System.out.println(AppMenu.mainMenu());
          selection = AppMenu.getUserInput();
          if (selection.equals(MenuOptions.addNewTask)){
            appState = AppStates.ADD_TASK;
          }
          if (selection.equals(MenuOptions.quitProgram)){
            System.exit(0);
          }
        }
        case ADD_TASK -> {
          System.out.println(AppMenu.addTaskMenu());
          numberToCalculate = Integer.valueOf(AppMenu.getUserInput());
          threadsCollection.add(new MyThread("", numberToCalculate));
          appState = AppStates.MAIN_MENU;
        }
      }

    } while (true);

//  threadsCollection.add(new MyThread("Thread #"+ threadsCollection.size(), getRandomIntInRange(5,50)));
//  threadsCollection.get(0).start();
  }
}
