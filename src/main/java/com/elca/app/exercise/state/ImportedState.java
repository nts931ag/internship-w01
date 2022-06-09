package com.elca.app.exercise.state;

import com.elca.app.exercise.model.Program;
import com.elca.app.exercise.thread.WatchDirThread;
import com.elca.app.exercise.utils.MyUtils;

import java.io.IOException;
import java.util.Scanner;

public class ImportedState extends State{

    public ImportedState(Program program){
        super(program);
    }

    @Override
    public String onManipulate() {
        var sc = new Scanner(System.in);
        int choice;
        do {

            MyUtils.logger.info("----------Exercise 1----------");
            MyUtils.logger.info("1. Output to the console the total capital of headquarters located in \"CH\"");
            MyUtils.logger.info("2. Output to the console the name of companies that the country is in “CH”. The list is sorted descending by capital.");
            MyUtils.logger.info("3. Use your program to re-import the following zip file (companies_big_data.zip). " +
                    "\n\t\t\tUnzip the file with Windows before importing, your program only needs to handle csv file for now.");
            MyUtils.logger.info("4. Print all list company has been imported.");
            MyUtils.logger.info("0. Exit the program.");
            MyUtils.logger.info("Enter your option: ");
            choice = sc.nextInt();
            sc.nextLine();
//            WatchDirThread watchDirThread = null;
            switch (choice){
                case 0 -> {
                    System.out.println(this.onExit());
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
        }while (choice != 0 && choice != 3);
        return null;
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
