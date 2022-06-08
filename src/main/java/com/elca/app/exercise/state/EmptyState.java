package com.elca.app.exercise.state;

import com.elca.app.exercise.model.Company;
import com.elca.app.exercise.model.CsvMiner;
import com.elca.app.exercise.model.ListCompany;
import com.elca.app.exercise.model.Program;

import java.util.List;
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
        CsvMiner csvMiner = this.program.getCsvMiner();
        System.out.print("Enter your file name: ");
        String fileName = sc.nextLine();
        List<Company> listCompany = csvMiner.readCompaniesFromFile(fileName);
        if(listCompany != null){
            this.program.setListCompany(new ListCompany(listCompany));
            csvMiner.setPath(csvMiner.getPath().resolve(fileName));
            this.program.setState(new ImportedState(this.program));
            return "data has been imported";
        }
        return "Import fail!!!";
    }

}
