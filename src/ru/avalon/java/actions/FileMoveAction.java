package ru.avalon.java.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Действие, которое перемещает файлы в пределах дискового
 * пространства.
 */
public class FileMoveAction implements Action {
    File source, dest;
    
    public FileMoveAction(String source, String dest) {
        this.source = new File(source);
        this.dest = new File(dest);
    }
    
    public void start() {
        
        new Thread(this).start();
        System.out.println("Запускаем поток перемещения");
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
            source.delete();
            os.close();
            System.out.println("Перемещение " + source.getAbsolutePath() 
                    + " в " + dest.getAbsolutePath() + " завершено");
        } catch (IOException ex) {
            System.out.println("Ошибка перемещения файла " + ex.getMessage());
        }
    }

    
    @Override
    public void close() throws Exception {
        source = null;
        dest = null;
    }

}
