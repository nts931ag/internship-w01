package com.elca.app.exercise;

import com.elca.app.exercise.model.Program;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Demo {
    public static void main(String[] args){
        Program program = new Program();
        program.start();

//        final Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Desktop");
//        System.out.println(path);
//        try (final WatchService watchService = FileSystems.getDefault().newWatchService()) {
//            final WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
//            while (true) {
//                final WatchKey wk = watchService.take();
//                for (WatchEvent<?> event : wk.pollEvents()) {
//                    //we only register "ENTRY_MODIFY" so the context is always a Path.
//                    final Path changed = (Path) event.context();
//                    System.out.println(changed);
//                    if (changed.endsWith("myFile.txt")) {
//                        System.out.println("My file has changed");
//                    }
//                }
//                // reset the key
//                boolean valid = wk.reset();
//                if (!valid) {
//                    System.out.println("Key has been unregisterede");
//                }
//            }
//        }  catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }
}
