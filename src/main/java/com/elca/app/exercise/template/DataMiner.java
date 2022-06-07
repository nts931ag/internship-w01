package com.elca.app.exercise.template;


import com.elca.app.exercise.model.Company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class DataMiner {
    protected Path path;
    protected String delimeter;

    public List<Company> readCompaniesFromFile(String fileName){

        List<Company> lstCompany = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path.toString()+"\\"+fileName));
            lstCompany = handleData(br);
            br.close();
        }
        catch(IOException ioe) {
            System.out.println("File is not existed");
        }
        return lstCompany;
    }

    public abstract List<Company> handleData(BufferedReader br);

    public DataMiner() {
    }

    public DataMiner(Path path, String delimeter) {
        this.path = path;
        this.delimeter = delimeter;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getDelimeter() {
        return delimeter;
    }

    public void setDelimeter(String delimeter) {
        this.delimeter = delimeter;
    }
}
