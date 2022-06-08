package com.elca.app.exercise.model;

import com.elca.app.exercise.template.DataMiner;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvMiner extends DataMiner {

    private static CsvMiner instance;

    private CsvMiner(){
        super();
    }
    private CsvMiner(Path path, String delimeter){
        super(path, delimeter);
    }

    public static CsvMiner getInstance(Path path, String delimeter){
        if(instance == null){
            instance = new CsvMiner(path, delimeter);
        }
        return instance;
    }

    @Override
    public List<Company> handleData(BufferedReader br) {
        return br.lines().parallel().skip(1).map(mapToItem).collect(Collectors.toList());
    }

    private Function<String, Company> mapToItem = (line) -> {
        String[] companyAttr = line.split(this.delimeter);// a CSV has comma separated lines
        if(companyAttr.length == 5){
            return new Company(
                    Integer.parseInt(companyAttr[0])
                    , companyAttr[1]
                    , companyAttr[2]
                    , Integer.parseInt(companyAttr[3])
                    , companyAttr[4]
                    , null
            );
        }else{
            return new Company(
                    Integer.parseInt(companyAttr[0])
                    , companyAttr[1]
                    , companyAttr[2]
                    , Integer.parseInt(companyAttr[3])
                    , companyAttr[4]
                    , companyAttr[5].equals("1")? true:false
            );
        }
    };

    public static CsvMiner getInstance() {
        return instance;
    }

    public static void setInstance(CsvMiner instance) {
        CsvMiner.instance = instance;
    }
}
