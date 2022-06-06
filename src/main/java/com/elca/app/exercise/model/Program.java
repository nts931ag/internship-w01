package com.elca.app.exercise.model;

import com.elca.app.exercise.state.EmptyState;
import com.elca.app.exercise.state.ImportedState;
import com.elca.app.exercise.state.State;
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
    private final Path path = Paths.get("C:\\Users\\NGUS\\intellij-workspace\\internship-w01\\src");
    private String delimeter = ",";
    private ListCompany listCompany;
    private CsvMiner csvMiner;

    public Program(){
        this.state = new EmptyState(this);
        csvMiner = CsvMiner.getInstance(path, delimeter);
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
                    }else{
                        System.out.println(state.onExit());
                    }

            }else if(this.getState() instanceof ImportedState){
                this.state.onManipulate();
            }else{
                break;
            }
        }

        sc.close();
    }

}
