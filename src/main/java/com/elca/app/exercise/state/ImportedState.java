package com.elca.app.exercise.state;

import com.elca.app.exercise.model.Program;
import com.elca.app.exercise.thread.WatchDirThread;
import com.elca.app.exercise.utils.MyUtils;

import java.io.IOException;
import java.util.Scanner;

public class ImportedState extends State {

    public ImportedState(Program program) {
        super(program);
    }

    @Override
    public String onManipulate(int choice) {
        switch (choice) {
            case 0 -> {
                MyUtils.logger.info(this::onExit);
                break;
            }
            case 1 -> {
                MyUtils.logger.info("Total capital of headquarters located in \"CH\": " + this.program.getListCompany().calTotalCapitalOfHeadQuarter("CH"));
                break;
            }
            case 2 -> {
                MyUtils.logger.info("List company is sorted descending by capital that locate at country \"CH\": ");
                this.program.getListCompany().getListNameOfCompanyAtCountry("CH").stream().forEach(
                        c -> MyUtils.logger.info(c)
                );
                break;
            }
            case 3 -> {
                this.program.setState(new EmptyState(this.program));
                break;
            }
            case 4 -> {
                MyUtils.logger.info("List company: ");
                this.program.getListCompany().printAllCompany();
                break;
            }
            default -> {
                MyUtils.logger.info("Wrong syntax! Please, enter syntax exactly");
                break;
            }
        }
        return "The action is done";
    }

    @Override
    public String onExit() {
        this.program.setState(null);
        return "Exit";
    }

    @Override
    public boolean onImport(String filePath) {
        return false;
    }

}
