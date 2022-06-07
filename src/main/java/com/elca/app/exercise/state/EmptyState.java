package com.elca.app.exercise.state;

import com.elca.app.exercise.model.CsvMiner;
import com.elca.app.exercise.model.ListCompany;
import com.elca.app.exercise.model.Program;

import java.util.Scanner;

public class EmptyState extends State{

    public EmptyState(Program program){
        super(program);
    }

    @Override
    public String onManipulate() {
        return "Data has not been imported yet";
    }

    @Override
    public String onExit() {
        this.program.setState(null);
        return "Exit";
    }

    @Override
    public String onImport() {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = InputUtils.scanner;
        CsvMiner csvMiner = this.program.getCsvMiner();
        System.out.print("Enter your file name: ");
        this.program.setListCompany(new ListCompany(csvMiner.readCompaniesFromFile(sc.nextLine() + ".csv")));
        this.program.setState(new ImportedState(this.program));
//        sc.close();
        return "data has been imported";
    }

}
