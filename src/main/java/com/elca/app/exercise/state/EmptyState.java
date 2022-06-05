package com.elca.app.exercise.state;

import com.elca.app.exercise.Company;
import com.elca.app.exercise.CsvMiner;
import com.elca.app.exercise.ListCompany;

import java.util.List;
import java.util.Scanner;

public class EmptyState extends State{

    EmptyState(Program program){
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
        CsvMiner csvMiner = this.program.getCsvMiner();
        System.out.print("Enter your file name: ");
        this.program.setListCompany(new ListCompany(csvMiner.readCompaniesFromFile(sc.nextLine() + ".csv")));
        this.program.setState(new ImportedState(this.program));
        return "data has been imported";
    }

}
