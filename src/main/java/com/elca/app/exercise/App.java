package com.elca.app.exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------Exercise 1----------");
            System.out.println("1. Import the file. Please note that the application can be extended in the future to" +
                    " support more file extensions (e.g: XML, XLSX).");
            System.out.println("2. Output to the console the total capital of headquarters located in “CH”.");
            System.out.println("3. Output to the console the name of companies that the country is in “CH”. The list is " +
                    "sorted descending by capital.");
            System.out.println("4. Modify your program to monitor a predefined folder “import” for changes. If your " +
                    "program is able to process the file, reimport the file and print out the results in feature #2 and #3.");
            System.out.println("5. Use your program to re-import the following zip file (companies_big_data.zip). " +
                    "Unzip the file with Windows before importing, your program only needs to handle csv file for now");
            System.out.println("0. Exit the program.");
            System.out.println();

            System.out.print("Enter your option: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 0: break;
                case 1: {
//                    System.out.print("Enter name file .csv: ");
//                    String fileName = sc.nextLine();
//                    CsvMiner csvMiner = new CsvMiner("D:\\Elca-workspace\\internship-w01\\src\\" + fileName + ".csv", ",");
//                    ListCompany listCompany = ListCompany.getInstance(csvMiner.readCompaniesFromFile());
//                    break;
                }
                case 2: {
//                    System.out.print("Enter name file .csv: ");
//                    String fileName = sc.nextLine();
//                    CsvMiner csvMiner = new CsvMiner("D:\\Elca-workspace\\internship-w01\\src\\" + fileName + ".csv", ",");
//                    ListCompany listCompany = ListCompany.getInstance(csvMiner.readCompaniesFromFile());
//                    System.out.println(listCompany.calTotalCapitalOfHeadQuarter("CH"));
//                    break;
                }
                case 3: {
//                    System.out.print("Enter name file .csv: ");
//                    String fileName = sc.nextLine();
//                    CsvMiner csvMiner = new CsvMiner("D:\\Elca-workspace\\internship-w01\\src\\" + fileName + ".csv", ",");
//                    ListCompany listCompany = ListCompany.getInstance(csvMiner.readCompaniesFromFile());
//                    listCompany.getListNameOfCompanyAtCountry("CH").stream().forEach(System.out::println);
//                    break;
                }
                case 4: {
                    Path path = Paths.get("D:\\Elca-workspace\\internship-w01\\src");
                    System.out.print("Enter file name for monitor for changes: ");
                    String nameFile = sc.nextLine();
                    WatchDir watchDir = new WatchDir(path, nameFile, false);

                    break;
                }
                case 5: {
                    System.out.println("5");
                    break;
                }
                default:{
                    System.out.println("default");
                    break;
                }
            }
        }while (choice != 0);

//        CsvMiner csvMiner = new CsvMiner("D:\\Elca-workspace\\internship-w01\\src\\companies_big_data.csv", ",");
//        ListCompany listCompany = new ListCompany(csvMiner.readCompaniesFromFile());
//        listCompany.printAllCompany();
    }
}
