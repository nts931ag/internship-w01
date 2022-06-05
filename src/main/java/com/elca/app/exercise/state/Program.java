package com.elca.app.exercise.state;

import com.elca.app.exercise.CsvMiner;
import com.elca.app.exercise.ListCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

@Data
@AllArgsConstructor
public class Program {
    private State state;
    private boolean isImported = false;
    private final Path path = Paths.get("D:\\Elca-workspace\\internship-w01\\src");
    private ListCompany listCompany;
    private CsvMiner csvMiner;

    Program(){
        this.state = new EmptyState(this);

    }

    public void start(){
        Scanner sc = new Scanner(System.in);
        int choice;
        while(true){
            if(this.getState() instanceof EmptyState){

                    System.out.print("Your program has not data. Please import data: ");
                    System.out.println("1. Import\t 2. Exit");
                    choice = sc.nextInt();
                    if(choice == 1){
                        state.onImport();
//                        System.out.print("Enter name of file .csv: ");
//                        sc.nextLine();
//                        String fileName = sc.nextLine();
//                        CsvMiner csvMiner = new CsvMiner("D:\\Elca-workspace\\internship-w01\\src\\" + fileName + ".csv", ",");
//                        ListCompany listCompany = new ListCompany(csvMiner.readCompaniesFromFile());
                    }else{
                        System.out.println(state.onExit());
                    }

            }else if(this.getState() instanceof ImportedState){
                this.state.onManipulate();

            }else{
                break;
            }
        }
    }

}
