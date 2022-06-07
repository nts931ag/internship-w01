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
        try(Scanner sc = new Scanner(System.in);){
            int choice;
            do {
                System.out.println("----------Exercise 1----------");
                System.out.println("1. Output to the console the total capital of headquarters located in \"CH\".");
                System.out.println("2. Output to the console the name of companies that the country is in \"CH\". The list is " +
                        "sorted descending by capital.");
                System.out.println("3. Modify your program to monitor a predefined folder \"import\" for changes. If your " +
                        "program is able to process the file, reimport the file and print out the results in feature #2 and #3.");
                System.out.println("4. Use your program to re-import the following zip file (companies_big_data.zip). " +
                        "Unzip the file with Windows before importing, your program only needs to handle csv file for now");
                System.out.println("5. Print all list company has been imported.");
                System.out.println("0. Exit the program.");
                System.out.println();

                System.out.print("Enter your option: ");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 0: {
                        System.out.println(this.onExit());
                        break;
                    }
                    case 1: {
                        System.out.println("Total capital of headquarters located in \"CH\": "
                                + this.program.getListCompany().calTotalCapitalOfHeadQuarter("CH"));
                        break;
                    }
                    case 2: {
                        System.out.println("List company locate at country \"CH\": ");
                        this.program.getListCompany().getListNameOfCompanyAtCountry("CH").stream().forEach(
                                System.out::println
                        );
                        break;
                    }
                    case 3: {

                        try {
                            WatchDirThread watchDirThread = new WatchDirThread(this.program, "companies1.csv", false);
                            watchDirThread.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 4: {
                        this.program.setState(new EmptyState(this.program));
                        break;
                    }
                    case 5: {
                        System.out.println("List company: ");
//                    this.program.getListCompany().printAllCompany();
                        for(int i = 0; i< this.program.getListCompany().getLstCompany().size(); ++i){
                            System.out.println(i + " " +this.program.getListCompany().getLstCompany().get(i));
                        }
                        break;
                    }
                    default:{
                        System.out.println("wrong syntax, Enter exactly one option!!!");
                        break;
                    }
                }
            }while (choice != 0 && choice != 4);
        }
        return null;
    }

    @Override
    public String onExit() {
        this.program.setState(null);
        return "Exit";
    }

    @Override
    public String onImport() {
        return "data already imported";
    }

}
