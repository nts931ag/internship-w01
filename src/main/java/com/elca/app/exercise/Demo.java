package com.elca.app.exercise;


import com.elca.app.exercise.model.Program;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException {
        Program program = new Program();
        program.start();

    }
}
