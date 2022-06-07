package com.elca.app.exercise.state;

import com.elca.app.exercise.model.Program;

public abstract class State {
    protected Program program;

    public State(Program program){
        this.program = program;
    }


    public abstract String onManipulate();
    public abstract String onExit();
    public abstract String onImport();
}
