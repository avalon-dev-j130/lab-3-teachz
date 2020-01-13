package ru.avalon.java.actions;

import java.io.*;

/**
 * Действие, которое копирует файлы в пределах дискового пространства.
 */
public class FileCopyAction implements Action {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    File source, dest;

    public FileCopyAction(String source, String dest) {
        this.source = new File(source);
        this.dest = new File(dest);
    }
    
    public void start() {
        
        new Thread(this).start();
        System.out.println("Запускаем поток копирпования");
    }
    
    @Override
    public void run() {
        try (InputStream is = new FileInputStream(source);
                OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
            System.out.print( ANSI_RED +
                    "\nКопирование " + source.getAbsolutePath() 
                    + " в " + dest.getAbsolutePath() + " завершено"
                    + ANSI_RESET + "\n>");
        } catch (IOException ex) {
            System.out.println("Ошибка копирования файла " + ex.getMessage());
        }
    }

 
    @Override
    public void close() throws Exception {
        source = null;
        dest = null;
    }
}
