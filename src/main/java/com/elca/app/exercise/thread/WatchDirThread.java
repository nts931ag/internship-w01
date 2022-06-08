package com.elca.app.exercise.thread;

import com.elca.app.exercise.model.ListCompany;
import com.elca.app.exercise.state.EmptyState;
import com.elca.app.exercise.model.Program;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.*;

public class WatchDirThread extends Thread{
    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private final boolean recursive;
    private String fileName;
    private boolean trace = false;
    private Program program;

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    private void register(Path dir) throws IOException{
        WatchKey key = dir.register(watcher, ENTRY_DELETE, ENTRY_MODIFY);
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
                System.out.format("register: %s\n", dir);
            } else {
                if (!dir.equals(prev)) {
                    System.out.format("update: %s -> %s\n", prev, dir);
                }
            }
        }
        keys.put(key, dir);
    }

    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public WatchDirThread(Program program, boolean recursive) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        this.recursive = recursive;
        this.program = program;

        var dir = this.program.getPath().getParent();
        this.fileName = this.program.getPath().getFileName().toString();
        System.out.println(this.fileName);
        if (recursive) {
            System.out.format("Scanning %s ...\n", dir);
            registerAll(dir);
            System.out.println("Done.");
        } else {
            register(dir);
        }

        // enable trace after initial registration
        this.trace = true;
    }

    public void processEvents() {
        for (;;) {

            // wait for key to be signalled
            WatchKey key;

            try {
//                key = watcher.take();
                key = watcher.poll(25, TimeUnit.MILLISECONDS);
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                if(this.program.getState() instanceof EmptyState ){
                    System.out.println("End thread is watching file: " + this.fileName);
                    break;
                }
                continue;
            }


            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);

                if(child.getFileName().toString().equals(fileName)){
//                    System.out.println(event.kind().toString() + "1");
                    switch (event.kind().toString()){
                        case "ENTRY_MODIFY" -> {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            this.program.setListCompany(
                                    new ListCompany(this.program.getCsvMiner().readCompaniesFromFile(this.program.getPath().toFile()))
                            );

                            break;
                        }
                        case "ENTRY_DELETE" -> {
//                            System.out.println("File has been deleted!!!");
//                            this.program.setState(new EmptyState(this.program));
                            this.program.setState(null);
                            break;
                        }
                    }
                }

                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }

    }


    @Override
    public void run() {
        super.run();
        System.out.println("\nMonitoring...");
        this.processEvents();
    }
}
