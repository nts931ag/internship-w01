package com.elca.app.exercise.model;

import com.elca.app.exercise.state.EmptyState;
import com.elca.app.exercise.state.ImportedState;
import com.elca.app.exercise.state.State;
import com.elca.app.exercise.thread.WatchDirThread;
import com.elca.app.exercise.utils.MyUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    private State state;
    private Path path;
    private String delimeter = ",";
    private ListCompany listCompany;
    private CsvMiner csvMiner;

    public Program(){
        this.state = new EmptyState(this);
        csvMiner = CsvMiner.getInstance(path, delimeter);
    }

    public void start() throws IOException {
        var sc = new Scanner(System.in);
        var choice= -99;
        boolean stop = false;
        while(this.getState() != null){
            switch (this.getState()){
                case EmptyState es -> {
                    MyUtils.logger.info("Your program has not data. Please import data: ");
                    MyUtils.logger.info("1. Import\t 2. Exit");
                    choice = sc.nextInt();
                    if(choice == 1){
//                        state.onImport();
                        sc.nextLine();
                        MyUtils.logger.info("Enter path to filename (ex: C:\\User\\app\\myFile.csv): ");
                        String pathFile = sc.nextLine();
                        if(state.onImport(pathFile)==true){
                            new WatchDirThread(this,false).start();

                        };
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
