package com.elca.app.exercise.state;

import com.elca.app.exercise.Company;
import com.elca.app.exercise.CsvMiner;
import com.elca.app.exercise.ListCompany;

import java.util.List;

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
        CsvMiner csvMiner = this.program.getCsvMiner();
        program.setState(new ImportedState(this.program));
        return "data has been imported";
    }

    @Override
    public String onReimport() {
        return "data has been reimported";
    }
}
