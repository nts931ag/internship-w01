package com.elca.app.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCompany {

//    private static ListCompany instance;
    private List<Company> lstCompany;

//    public static ListCompany getInstance(List<Company> lstCompany){
//        if(instance == null){
//            instance = new ListCompany(lstCompany);
//        }
//        return instance;
//    }

    public int calTotalCapitalOfHeadQuarter(String country){
        return lstCompany.stream()
                .filter(e -> e.getIsHeadQuarter() != null && e.getIsHeadQuarter() == true && e.getCountry().equals(country))
                .map(e -> e.getCapital())
                .reduce(0, Integer::sum);
    }

    public List<String> getListNameOfCompanyAtCountry(String country){
        return  lstCompany.stream()
                .filter(e->e.getCountry().equals(country))
                .map(e -> e.getName())
                .collect(Collectors.toList());
    }

    public void printAllCompany(){
        lstCompany.stream().forEach(System.out::println);
    }
}
