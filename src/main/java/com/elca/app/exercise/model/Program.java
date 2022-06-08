package com.elca.app.exercise.model;

import com.elca.app.exercise.state.EmptyState;
import com.elca.app.exercise.state.ImportedState;
import com.elca.app.exercise.state.State;
import com.elca.app.exercise.thread.WatchDirThread;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    private State state;
    private boolean isImported = false;
    private Path path = Paths.get("C:\\Users\\NGUS\\intellij-workspace\\internship-w01\\src");
    private String delimeter = ",";
    private ListCompany listCompany;
    private CsvMiner csvMiner;

    public Program(){
        this.state = new EmptyState(this);
        csvMiner = CsvMiner.getInstance(path, delimeter);
    }

    public void start(){
        var sc = new Scanner(System.in);
        var choice= -99;
        boolean stop = false;
        while(this.getState() != null){
            switch (this.getState()){
                case EmptyState es -> {
                    System.out.print("Your program has not data. Please import data: ");
                    System.out.println("1. Import\t 2. Exit");
                    choice = sc.nextInt();
                    if(choice == 1){
//                        state.onImport();
                        sc.nextLine();
                        System.out.print("Enter path to filename (ex: C:\\User\\app\\myFile.csv): ");
                        String pathFile = sc.nextLine();
                        state.onImport(pathFile);

                        try {
                            new WatchDirThread(this,false).start();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }else{
                        System.out.println(state.onExit());
                    }
                    break;
                }
                case ImportedState is ->{
                    this.state.onManipulate();
                    break;
                }
                case default -> {
                    System.out.println("default");
                    break;
                }
            }
        }
        sc.close();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getDelimeter() {
        return delimeter;
    }

    public void setDelimeter(String delimeter) {
        this.delimeter = delimeter;
    }

    public ListCompany getListCompany() {
        return listCompany;
    }

    public void setListCompany(ListCompany listCompany) {
        this.listCompany = listCompany;
    }

    public CsvMiner getCsvMiner() {
        return csvMiner;
    }

    public void setCsvMiner(CsvMiner csvMiner) {
        this.csvMiner = csvMiner;
    }
}
