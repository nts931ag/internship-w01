package com.elca.app.exercise.state;

import com.elca.app.exercise.CsvMiner;

public abstract class State {
    Program program;

    State(Program program){
        this.program = program;
    }


    public abstract String onManipulate();
    public abstract String onExit();
    public abstract String onImport();
}
