package com.elca.app.exercise.model;

import com.elca.app.exercise.state.EmptyState;
import com.elca.app.exercise.state.ImportedState;
import com.elca.app.exercise.state.State;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    private State state;
    private boolean isImported = false;
    private final Path path = Paths.get("D:\\Elca-workspace\\internship-w01\\src");
    private String delimeter = ",";
    private ListCompany listCompany;
    private CsvMiner csvMiner;

    public Program(){
        this.state = new EmptyState(this);
        csvMiner = CsvMiner.getInstance(path, delimeter);
    }

    public void start(){
        Scanner sc = new Scanner(System.in);
        int choice;
        while(true){

            switch (this.getState()){
                case EmptyState es -> {
                    System.out.print("Your program has not data. Please import data: ");
                    System.out.println("1. Import\t 2. Exit");
                    choice = sc.nextInt();
                    if(choice == 1){
                        state.onImport();
                    }else{
                        System.out.println(state.onExit());
                    }
                    break;
                }
                case ImportedState is ->{
                    this.state.onManipulate();
                    break;
                }
                default -> {
                    break;

                }
            }
        }
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
