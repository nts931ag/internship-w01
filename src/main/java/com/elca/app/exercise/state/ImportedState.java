package com.elca.app.exercise.state;

import com.elca.app.exercise.model.Program;
import com.elca.app.exercise.thread.WatchDirThread;
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
            System.out.println("----------Exercise 1----------");
            System.out.println("1. Output to the console the total capital of headquarters located in \"CH\".");
            System.out.println("2. Output to the console the name of companies that the country is in \"CH\". The list is " +
                    "sorted descending by capital.");
            System.out.println("3. Use your program to re-import the following zip file (companies_big_data.zip). " +
                    "Unzip the file with Windows before importing, your program only needs to handle csv file for now");
            System.out.println("4. Print all list company has been imported.");
            System.out.println("0. Exit the program.");
            System.out.println();

            System.out.print("Enter your option: ");
            choice = sc.nextInt();
            sc.nextLine();
//            WatchDirThread watchDirThread = null;
            switch (choice){
                case 0 -> {
                    System.out.println(this.onExit());
                    break;
                }
                case 1 -> {
                    System.out.println("Total capital of headquarters located in \"CH\": "
                            + this.program.getListCompany().calTotalCapitalOfHeadQuarter("CH"));
                    break;
                }
                case 2 -> {
                    System.out.println("List company locate at country \"CH\": ");
                    this.program.getListCompany().getListNameOfCompanyAtCountry("CH").stream().forEach(
                            System.out::println
                    );
                    break;
                }
                case 3 -> {

                    this.program.getCsvMiner().setPath(
                            this.program.getPath()
                    );
                    this.program.setState(new EmptyState(this.program));
                    break;
                }
                case 4 -> {
                    System.out.println("List company: ");
                    this.program.getListCompany().printAllCompany();
                    break;
                }
                default -> {
                    System.out.println("wrong syntax, Enter exactly one option!!!");
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
    public String onImport(String filePath) {
        return "data already imported";
    }

}
