package com.elca.app.exercise.template;


import com.elca.app.exercise.model.Company;
import com.elca.app.exercise.utils.MyUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class DataMiner {
    protected Path path;
    protected String delimeter;

    public DataMiner() {
    }

    public DataMiner(Path path, String delimeter) {
        this.path = path;
        this.delimeter = delimeter;
    }

    public List<Company> readCompaniesFromFile(File file){

        List<Company> lstCompany = null;

        try(BufferedReader br = new BufferedReader(new FileReader(file));){
            lstCompany = handleData(br);

        }catch (NoSuchFileException nfe){
            MyUtils.logger.error(nfe.getMessage());
        }
        catch (IOException ioe){
            MyUtils.logger.error(ioe.getMessage());
            return null;
        }
        return lstCompany;
    }

    public abstract List<Company> handleData(BufferedReader br);

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
