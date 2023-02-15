package org.example;

import java.util.ArrayList;
import java.util.Random;
import org.example.AppMenu.MenuOptions;

public class Main {

  static String selection = "";
  static ArrayList<MyThread> threadsCollection = new ArrayList<>();
  static int getRandomIntInRange(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }
  public static void main(String[] args) {

    System.out.println(AppMenu.mainMenu());
    do {
      selection = AppMenu.getUserInput();
      if (selection.equals(MenuOptions.quitProgram)) {
        System.exit(0);
      } else if (selection.equals(MenuOptions.addNewTask)) {
        AppMenu.addTaskMenu();
      }


    } while (true);

//  threadsCollection.add(new MyThread("Thread #"+ threadsCollection.size(), getRandomIntInRange(5,50)));
//  threadsCollection.get(0).start();
  }
}
