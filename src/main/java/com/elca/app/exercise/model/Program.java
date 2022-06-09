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
                        MyUtils.logger.info(state.onExit());
                    }
                    break;
                }
                case ImportedState is ->{
                    MyUtils.logger.info("----------Exercise 1----------");
                    MyUtils.logger.info("1. Output to the console the total capital of headquarters located in \"CH\"");
                    MyUtils.logger.info("2. Output to the console the name of companies that the country is in “CH”. The list is sorted descending by capital.");
                    MyUtils.logger.info("3. Use your program to re-import the following zip file (companies_big_data.zip). " +
                            "\n\t\t\tUnzip the file with Windows before importing, your program only needs to handle csv file for now.");
                    MyUtils.logger.info("4. Print all list company has been imported.");
                    MyUtils.logger.info("0. Exit the program.");
                    MyUtils.logger.info("Enter your option: ");
                    choice = sc.nextInt();
                    this.state.onManipulate(choice);
                    break;
                }
                case default -> {
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
