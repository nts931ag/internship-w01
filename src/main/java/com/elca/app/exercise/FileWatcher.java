package com.elca.app.exercise;

import com.elca.app.exercise.model.Program;

import java.io.File;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileWatcher extends Thread {
    private final File file;
    private Program program;
    private AtomicBoolean stop = new AtomicBoolean(false);

    public FileWatcher( File file) {
        this.file = file;
        System.out.println(file.getName());
    }

    public boolean isStopped() { return stop.get(); }
    public void stopThread() { stop.set(true); }

    public void doOnChange() {
        // Do whatever action you want here
        System.out.println("modifying");
    }

    @Override
    public void run() {
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            Path path = file.toPath().getParent();
            path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
            while (!isStopped()) {
                WatchKey key;
                try { key = watcher.poll(25, TimeUnit.MILLISECONDS);}
                catch (InterruptedException e) { return; }
                if (key == null) { Thread.yield(); continue; }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path filename = ev.context();
                    System.out.println(kind);
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        Thread.yield();
                        System.out.println("heeh");
                        continue;
                    } else if (kind == java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY
                            && filename.toString().equals(file.getName())) {
                        doOnChange();
                    } else if( kind == StandardWatchEventKinds.ENTRY_DELETE){
                        System.out.println("hahaha");
                    }
                    boolean valid = key.reset();
                    if (!valid) { break; }
                }
                Thread.yield();
            }
        } catch (Throwable e) {
            // Log or rethrow the error
        }
    }

    public static void main(String[] args){
        new FileWatcher(new File("C:\\Users\\NGUS\\Desktop\\myFile.txt")).start();
    }
}
