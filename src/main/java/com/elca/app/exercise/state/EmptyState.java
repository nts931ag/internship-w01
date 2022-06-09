package com.elca.app.exercise.state;

import com.elca.app.exercise.model.Company;
import com.elca.app.exercise.model.CsvMiner;
import com.elca.app.exercise.model.ListCompany;
import com.elca.app.exercise.model.Program;

import java.nio.file.Paths;
import java.util.List;

public class EmptyState extends State{

    public EmptyState(Program program){
        super(program);
    }

    @Override
    public String onManipulate(int choice) {
        return "Data has not been imported yet";
    }

    @Override
    public String onExit() {
        this.program.setState(null);
        return "Exit";
    }

    @Override
    public boolean onImport(String filePath) {
        var csvMiner = this.program.getCsvMiner();
        this.program.setPath(Paths.get(filePath));
        var listCompany = csvMiner.readCompaniesFromFile(this.program.getPath().toFile());

        if(listCompany != null){
            this.program.setListCompany(new ListCompany(listCompany));
            this.program.setState(new ImportedState(this.program));
            return true;
        }

        return false;
    }

}
