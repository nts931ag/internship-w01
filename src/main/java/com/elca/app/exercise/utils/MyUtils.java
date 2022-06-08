package com.elca.app.exercise.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class MyUtils {
    public static HashMap<String, String> getPathAndFileName(String path){
        Path absolutePath = Paths.get(path);

        File file1 = absolutePath.toFile();

        System.out.println(absolutePath.getParent());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getParent());
        System.out.println(file1.isFile());
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyUtils.getPathAndFileName("C:\\Users\\NGUS\\Desktop\\temp.txt");
    }
}
