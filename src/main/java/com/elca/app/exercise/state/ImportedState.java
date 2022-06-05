package com.elca.app.exercise.state;

import com.elca.app.exercise.CsvMiner;
import com.elca.app.exercise.ListCompany;
import com.elca.app.exercise.WatchDir;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ImportedState extends State{

    ImportedState(Program program){
        super(program);
    }

    @Override
    public String onManipulate() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------Exercise 1----------");
            System.out.println("1. Output to the console the total capital of headquarters located in “CH”.");
            System.out.println("2. Output to the console the name of companies that the country is in “CH”. The list is " +
                    "sorted descending by capital.");
            System.out.println("3. Modify your program to monitor a predefined folder “import” for changes. If your " +
                    "program is able to process the file, reimport the file and print out the results in feature #2 and #3.");
            System.out.println("4. Use your program to re-import the following zip file (companies_big_data.zip). " +
                    "Unzip the file with Windows before importing, your program only needs to handle csv file for now");
            System.out.println("0. Exit the program.");
            System.out.println();

            System.out.print("Enter your option: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 0: break;
                case 1: {
                    System.out.print("Enter name file .csv: ");
                    String fileName = sc.nextLine();
                    CsvMiner csvMiner = new CsvMiner(Paths.get("D:\\Elca-workspace\\internship-w01\\src"), ",");
//                    ListCompany listCompany = ListCompany.getInstance(csvMiner.readCompaniesFromFile());
//                    System.out.println(listCompany.calTotalCapitalOfHeadQuarter("CH"));
                    break;
                }
                case 2: {
                    System.out.print("Enter name file .csv: ");
                    String fileName = sc.nextLine();
                    CsvMiner csvMiner = new CsvMiner(Paths.get("D:\\Elca-workspace\\internship-w01\\src"), ",");
//                    ListCompany listCompany = ListCompany.getInstance(csvMiner.readCompaniesFromFile());
//                    listCompany.getListNameOfCompanyAtCountry("CH").stream().forEach(System.out::println);
                    break;
                }
                case 3: {
                    Path path = Paths.get("D:\\Elca-workspace\\internship-w01\\src");
                    System.out.print("Enter file name for monitor for changes: ");
                    String nameFile = sc.nextLine();
                    try {
                        WatchDir watchDir = new WatchDir(path, nameFile, false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                case 4: {
                    System.out.print("Enter name file .csv: ");
                    String fileName = sc.nextLine();
                    CsvMiner csvMiner = new CsvMiner(Paths.get("D:\\Elca-workspace\\internship-w01\\src"),",");
//                    ListCompany listCompany = ListCompany.getInstance(csvMiner.readCompaniesFromFile());
//                    listCompany.printAllCompany();
                    break;
                }
                default:{
                    System.out.println("default");
                    break;
                }
            }
        }while (choice != 0);
        this.program.setState(null);
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

    @Override
    public String onReimport() {
        return "data has been reimported";
    }
}
