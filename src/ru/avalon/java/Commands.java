package ru.avalon.java;

import java.util.Arrays;

/**
 * Обрабатываемые приложением команды.
 */
public final class Commands {
    public enum Command {
        move,
        copy,
        exit
    };

    
    public final Command comm;
    public String[] param;

    public Commands(String input) throws IllegalCommand {
        String[] parts = input.split(" +");
        switch (parts[0]) {
            case "copy":
                comm = Command.copy;
                break;
            case "move":
                comm = Command.move;
                break;
            case "exit":
                comm = Command.exit;
                break;
            default:
                throw new IllegalCommand("Не удалось распознать команду " + parts[0]);
        }
        param = Arrays.copyOfRange(parts, 1, parts.length);
        System.out.println("Создана комманда " + toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(comm.toString() + " \nПараметры:");
        for(String s: param) {sb.append(" ").append(s);}
        return sb.toString();
    }
    
}
