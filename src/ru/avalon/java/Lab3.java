package ru.avalon.java;

import ru.avalon.java.console.ConsoleUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.avalon.java.actions.*;


public class Lab3 extends ConsoleUI<Commands> {
    /**
     * Точка входа в приложение.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("Запуск main");
//        try {
//            new Commands("copy file1 file2 ");
//        } catch (IllegalCommand ex) {
//            System.out.println("Исключение IllegalCommand возвращено");
//        }
//        
//        new FileCopyAction("README.md","README3.md").start();
//        new FileMoveAction("README2.md","README5.md").start();
        System.out.println("Запуск Lab3:");
        new Lab3().run();
    }
    
    Lab3() {
       // super(Commands.class);
    }

    @Override
    protected void onCommand(Commands command) {
        switch (command.comm) {
            case copy:
                new FileCopyAction(command.param[0],command.param[1]).start();
                System.out.println("Метод onCommand отработал для copy");
                break;
            case move:
                new FileMoveAction(command.param[0],command.param[1]).start();
                break;
            case exit:
                close();
                break;
             default:
                System.out.println("нераспознанная команда");
        }
    }
    
}
